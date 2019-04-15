package net.scapeemulator.asm.bundler;

import net.scapeemulator.asm.bundler.trans.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.NoSuchAlgorithmException;

public final class ClientBundler {

	private static final Logger logger = LoggerFactory.getLogger(ClientBundler.class);

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		ClientBundler bundler = new ClientBundler();
		bundler.bundle();
	}

	public void bundle() throws IOException, NoSuchAlgorithmException {
		/* unpack source files */
		logger.info("Starting client bundler...");
		Application client = Application.unpackJar(new File("data/runescape.jar"));
		Application glClient = Application.unpack200(new File("data/runescape_gl.pack200"));
		Application loader = Application.unpackJar(new File("data/loader.jar"));
		Application glLoader = Application.unpackJar(new File("data/loader_gl.jar"));
		Application jaggl = Application.unpackJar(new File("data/jaggl.jar"));

		/* update client RSA keys */
		logger.info("Transforming RSA keys...");
		Transformer transformer = new RsaTransformer();
		client.transform(transformer);
		glClient.transform(transformer);

		/* remove hostname protection */
		logger.info("Transforming hostname protection...");
		transformer = new HostnameTransformer();
		client.transform(transformer);
		glClient.transform(transformer);

		/* write everything but the loaders back out */
		logger.info("Bundling clients and libraries...");
		File clientOut = new File("../game/data/www/runescape.pack200");
		File glClientOut = new File("../game/data/www/runescape_gl.pack200");
		File jagglOut = new File("../game/data/www/jaggl.pack200");
		client.packJar(new File("../game/data/www/runescape.jar")); // unsigned client output
		client.pack200(clientOut);
		glClient.pack200(glClientOut);
		jaggl.pack200(jagglOut);

		/* compute SHA1 checksums */
		logger.info("Computing SHA1 checksums and file sizes...");
		Resource[] resources = new Resource[] {
			Resource.create(new File("data/game_unpacker.dat")),
			Resource.create(clientOut),
			Resource.NULL,
            Resource.create(new File("data/windows-x86/jagmisc.dll")),
            Resource.create(new File("data/windows-universal/jagmiscms.dll")),
            Resource.create(new File("data/windows-amd64/jagmisc64.dll"))
		};
		Resource[] glResources = new Resource[] {
			Resource.create(new File("data/game_unpacker.dat")),
			Resource.create(glClientOut),
			Resource.NULL,
			Resource.create(jagglOut),
			Resource.NULL,
			Resource.create(new File("data/windows-x86/jaggl.dll")),
			Resource.create(new File("data/linux-universal/libjaggl.so")),
			Resource.create(new File("data/linux-universal/libjaggl_dri.so")),
			Resource.create(new File("data/mac-ppc/libjaggl.jnilib")),
			Resource.create(new File("data/mac-universal/libjaggl.jnilib")),
			Resource.create(new File("data/windows-amd64/jaggl.dll")),
			Resource.create(new File("data/windows-x86/jagmisc.dll")),
            Resource.create(new File("data/windows-universal/jagmiscms.dll")),
			Resource.create(new File("data/windows-amd64/jagmisc64.dll")),
		};

		/* update SHA1 checksums in the loader */
		logger.info("Transforming SHA1 checksums and file sizes...");
		transformer = new FileTransformer(resources);
		loader.transform(transformer);
		transformer = new FileTransformer(glResources);
		glLoader.transform(transformer);

		/* transform cache path */
		logger.info("Transforming cache path...");
		transformer = new CachePathTransformer();
		loader.transform(transformer);
		glLoader.transform(transformer);

		/* write the loaders back out */
		logger.info("Bundling loaders...");
		loader.packJar(new File("../game/data/www/loader.jar"));
		glLoader.packJar(new File("../game/data/www/loader_gl.jar"));

		/* copy natives/unpacker */
		logger.info("Copying native libraries and game unpacker...");
		copy("data/game_unpacker.dat", "../game/data/www/unpackclass.pack");
		copy("data/windows-x86/jaggl.dll", "../game/data/www/jaggl_0_0.lib");
        copy("data/windows-x86/jagmisc.dll", "../game/data/www/jagmisc_0.lib");

		copy("data/windows-amd64/jaggl.dll", "../game/data/www/jaggl_4_0.lib");
        copy("data/windows-amd64/jagmisc64.dll", "../game/data/www/jagmisc_2.lib");

        copy("data/windows-universal/jagmiscms.dll", "../game/data/www/jagmisc_1.lib");

		copy("data/linux-universal/libjaggl.so", "../game/data/www/jaggl_1_0.lib");
        copy("data/linux-universal/libjaggl_dri.so", "../game/data/www/jaggl_1_1.lib");

		copy("data/mac-ppc/libjaggl.jnilib", "../game/data/www/jaggl_2_0.lib");

		copy("data/mac-universal/libjaggl.jnilib", "../game/data/www/jaggl_3_0.lib");

		/* sign loaders */
		logger.info("Signing loaders...");
		Runtime.getRuntime().exec("jarsigner -keystore ../util/data/scapeemu.keystore -storepass scapeemu ../game/data/www/loader.jar scapeemu");
		Runtime.getRuntime().exec("jarsigner -keystore ../util/data/scapeemu.keystore -storepass scapeemu ../game/data/www/loader_gl.jar scapeemu");

		/* all done! */
		logger.info("Client bundler completed successfully.");
	}

	private void copy(String srcPath, String destPath) throws IOException {
		Path src = new File(srcPath).toPath();
		Path dest = new File(destPath).toPath();
		Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
	}

}

package net.scapeemulator.login;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.internal.logging.InternalLoggerFactory;
import io.netty.util.internal.logging.Slf4JLoggerFactory;
import net.scapeemulator.login.net.LoginChannelInitializer;
import net.scapeemulator.util.NetworkConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

public final class LoginServer {

	private static final Logger logger = LoggerFactory.getLogger(LoginServer.class);

	public static void main(String[] args) {
		try {
			InternalLoggerFactory.setDefaultFactory(new Slf4JLoggerFactory());

			LoginServer server = new LoginServer();
			server.bind(new InetSocketAddress(NetworkConstants.LOGIN_PORT));
			server.start();
		} catch (Throwable t) {
			logger.error("Failed to start server.", t);
		}
	}

	private final ServerBootstrap bootstrap = new ServerBootstrap();

	public LoginServer() {
		logger.info("Starting ScapeEmulator login server...");
		bootstrap.group(new NioEventLoopGroup());
		bootstrap.channel(NioServerSocketChannel.class);
		bootstrap.childHandler(new LoginChannelInitializer(this));
	}

	public void bind(SocketAddress address) throws InterruptedException {
		logger.info("Binding to address: " + address + "...");
		bootstrap.localAddress(address).bind().sync();
	}

	public void start() {
		logger.info("Ready for connections.");
	}

}

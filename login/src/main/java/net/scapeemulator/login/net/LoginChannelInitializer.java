package net.scapeemulator.login.net;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import net.scapeemulator.login.LoginServer;
import net.scapeemulator.util.net.LoginFrameDecoder;
import net.scapeemulator.util.net.LoginFrameEncoder;

public final class LoginChannelInitializer extends ChannelInitializer<SocketChannel> {

	private final LoginServer server;

	public LoginChannelInitializer(LoginServer server) {
		this.server = server;
	}

	@Override
	public void initChannel(SocketChannel ch) throws Exception {
		ch.pipeline().addLast(
			new ReadTimeoutHandler(5),
			new LoginFrameDecoder(),
			new LoginFrameEncoder(),
			new LoginChannelHandler(server));
	}

}

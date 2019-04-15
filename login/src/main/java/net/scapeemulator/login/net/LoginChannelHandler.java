package net.scapeemulator.login.net;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundMessageHandlerAdapter;
import net.scapeemulator.login.LoginServer;
import net.scapeemulator.util.net.LoginFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LoginChannelHandler extends ChannelInboundMessageHandlerAdapter<LoginFrame> {

	private static final Logger logger = LoggerFactory.getLogger(LoginChannelHandler.class);

	private final LoginServer server;

	public LoginChannelHandler(LoginServer server) {
		this.server = server;
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		logger.info("Channel connected: " + ctx.channel().remoteAddress() + ".");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) {
		logger.info("Channel disconnected: " + ctx.channel().remoteAddress() + ".");
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, LoginFrame msg) {

	}

}

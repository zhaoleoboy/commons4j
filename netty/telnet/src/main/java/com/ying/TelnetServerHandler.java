package com.ying;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.util.StringUtils;

import java.net.InetAddress;
import java.util.Date;

public class TelnetServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.write("welcome to " + InetAddress.getLocalHost().getHostName() + "!\r\n");
        ctx.write("it is " + new Date() + "\r\n");
        ctx.flush();
        super.channelActive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String response = "";
        boolean close = false;
        String request = msg.toString();
        if (StringUtils.isEmpty(request)) {
            response = "please type something.\r\n";
        } else if ("bye".equalsIgnoreCase(request)) {
            response = "have a good day. \r\n";
            close = true;
        } else {
            response = "Did you say:" + request + "\r\n";
        }
        ChannelFuture future = ctx.writeAndFlush(response);
        if (close) {
            future.channel().close();
        }
        super.channelRead(ctx, msg);
    }

}

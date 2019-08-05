package com.ying;

import io.netty.channel.*;
import io.netty.util.concurrent.EventExecutorGroup;

public class ImServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("**********有一个新连接****************");
        super.channelActive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("你刚才是说：" + msg);
        if ("bye".equalsIgnoreCase(msg)) {
            ctx.channel().closeFuture();
            ChannelFuture future = ctx.write("bye");
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }
}

package com.ying;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * SimpleDateFormat 不是线程安全的！！！
     */
    private static final SimpleDateFormat DATE_FORMATE = new SimpleDateFormat("yyyy年MM月dd日");

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("当前线程：" + Thread.currentThread());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf buf = (ByteBuf) msg;
//        byte[] bytes = new byte[buf.readableBytes()];
//        buf.readBytes(bytes);
//        String query = new String(bytes, "UTF-8");
        String query = msg.toString();
        System.out.println("QUERY is " + query);
        String time = "BAD REQUEST";
        if ("QUERY TIME".equalsIgnoreCase(query)) {
            time = DATE_FORMATE.format(new Date());
        }
        time += System.getProperty("line.separator");

        // 在netty中所有的信息都是封装在缓冲区中的
        ByteBuf result = Unpooled.copiedBuffer(time.getBytes());
        ctx.writeAndFlush(result);

//        super.channelRead(ctx, msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        super.exceptionCaught(ctx, cause);
    }
}

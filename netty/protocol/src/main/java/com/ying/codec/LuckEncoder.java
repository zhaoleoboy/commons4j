package com.ying.codec;

import com.ying.model.LuckHeader;
import com.ying.model.LuckMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class LuckEncoder extends MessageToByteEncoder<LuckMessage> {
    @Override
    protected void encode(ChannelHandlerContext ctx, LuckMessage msg, ByteBuf out) throws Exception {
        LuckHeader header = msg.getHeader();
        // 这里写入的顺序就是协议的顺序.

        // 写入消息头
        out.writeInt(header.getVersion());
        out.writeInt(header.getLength());
        out.writeBytes(header.getSession().getBytes());

        // 写入消息体
        out.writeBytes(msg.getContent().getBytes());
    }
}

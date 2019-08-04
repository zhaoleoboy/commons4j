package com.ying.codec;

import com.ying.model.LuckHeader;
import com.ying.model.LuckMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.Charset;
import java.util.List;

public class LuckDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int version = in.readInt();
        int length = in.readInt();
        byte[] session = new byte[36];
        in.readBytes(session);
        LuckHeader luckHeader = new LuckHeader(version, length, new String(session));
        String content = new String(ByteBufUtil.getBytes(in), Charset.forName("UTF-8"));

        LuckMessage luckMessage = new LuckMessage(luckHeader, content);
        out.add(luckMessage);
    }
}

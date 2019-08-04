package com.ying;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@Slf4j
public class HttpServerHandler extends ChannelInboundHandlerAdapter {

    private static final String FAVICON_ICO = "/favicon.ico";
    private HttpHeaders headers;
    private HttpRequest request;
    private FullHttpResponse response;
    private FullHttpRequest fullRequest;
    private HttpPostRequestDecoder decoder;
    private static final HttpDataFactory factory = new DefaultHttpDataFactory(DefaultHttpDataFactory.MAXSIZE);

    //    private static final String FAVICON_ICO = "/favicon.ico";
    private static final String SUCCESS = "<b>success</b>";
    private static final String ERROR = "error";
    private static final String CONNECTION_KEEP_ALIVE = "keep-alive";
    private static final String CONNECTION_CLOSE = "close";


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpRequest) {
            try {
                request = (HttpRequest) msg;
                // TODO
                boolean keepAlive = HttpUtil.isKeepAlive(request);
                String uri = request.uri();
                HttpMethod method = request.method();
                System.out.println(String.format("URL is: %s, Method is: %s", uri, method));

                //去除浏览器"/favicon.ico"的干扰，chrome浏览器带的标签小图标。
                if (uri.equals(FAVICON_ICO)) {
                    return;
                }

                headers = request.headers();
                if (method.equals(HttpMethod.GET)) {
                    QueryStringDecoder queryDecoder = new QueryStringDecoder(uri, CharsetUtil.UTF_8);
                    Map<String, List<String>> uriAttributes = queryDecoder.parameters();
                    //此处仅打印请求参数（你可以根据业务需求自定义处理）
                    for (Map.Entry<String, List<String>> attr : uriAttributes.entrySet()) {
                        for (String attrVal : attr.getValue()) {
                            System.out.println(attr.getKey() + "=" + attrVal);
                        }
                    }
                } else if (method.equals(HttpMethod.POST)) {

                    //POST请求，由于你需要从消息体中获取数据，因此有必要把msg转换成FullHttpRequest
                    fullRequest = (FullHttpRequest) msg;

                    //根据不同的 Content_Type 处理 body 数据
//                    dealWithContentType();
                } else {
                    //其他类型在此不做处理，需要的话可自己扩展
                }
                if (uri.contains("bing")) {
                    writeResponse(ctx.channel(), HttpResponseStatus.FOUND, SUCCESS, false);
                } else {
                    writeResponse(ctx.channel(), HttpResponseStatus.OK, SUCCESS, false);
                }
            } catch (Exception e) {
                writeResponse(ctx.channel(), HttpResponseStatus.INTERNAL_SERVER_ERROR, ERROR, true);
            } finally {
                ReferenceCountUtil.release(msg);
            }

        } else {
            //discard request...
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    /**
     * 服务器的响应response
     *
     * @param channel
     * @param status
     * @param msg
     * @param keepAlive
     */
    private void writeResponse(Channel channel, HttpResponseStatus status, String msg, boolean keepAlive) {
        ByteBuf byteBuf = Unpooled.wrappedBuffer(msg.getBytes());
        response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status, byteBuf);
        // TODO response.content().writeBytes是干啥用的？
        // response.content().writeBytes("ssssssssssss".getBytes());
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html;charset=UTF-8");
        response.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
        if (response.status().equals(HttpResponseStatus.FOUND)) {
            redirect(response);
        }
        if (keepAlive) {
            response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        }
        // 在方法channelReadComplete进行flush操作
        channel.write(response);
    }

    /**
     * 重定向
     */
    private void redirect(HttpResponse response) {
        response.headers().set(HttpHeaderNames.LOCATION, "http://www.bing.com");
    }

    private void dealWithContent() {
        System.out.println("POSTTTTTTTTTTTTTT");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        super.exceptionCaught(ctx, cause);
    }

    private FullHttpResponse initResponse(String res) throws UnsupportedEncodingException {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
                Unpooled.wrappedBuffer(res.getBytes("UTF-8")));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
        response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        return response;
    }
}

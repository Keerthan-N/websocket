package com.example.websocket.config;

import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Slf4j
public class MyHandShakeInterceptor implements HandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response,
                                   WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {

        log.info("Handshake started for: {}", request.getURI().getPath());

        if (!(request instanceof ServletServerHttpRequest serverHttpRequest)) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return false;
        }

//   Allow handshake
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, @Nullable Exception exception) {
        log.info("Handshake ended for: {}", request.getURI().getPath());
        if(exception != null) {
            log.error("Handshake failed for: {}", request.getURI().getPath(), exception);
        }
    }
}

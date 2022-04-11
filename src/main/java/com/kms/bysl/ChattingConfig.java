package com.kms.bysl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.kms.bysl.handler.ChattingHandler;

@Configuration
@EnableWebSocket
public class ChattingConfig implements WebSocketConfigurer{
	@Autowired
	private ChattingHandler chattingHandler;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(chattingHandler, "/chatting")
        .setAllowedOrigins("http://localhost:8090")
        .withSockJS();
	}
	
	
}

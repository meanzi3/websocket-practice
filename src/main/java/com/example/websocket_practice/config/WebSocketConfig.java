package com.example.websocket_practice.config;

import com.example.websocket_practice.handler.ChatHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@RequiredArgsConstructor
@EnableWebSocket // 웹 소켓 활성화
public class WebSocketConfig implements WebSocketConfigurer {

  private final ChatHandler chatHandler;

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    // 핸들러 등록, endpoint 설정
    // 모든 도메인에서의 접근 허용
    // TODO : 보안상 실제 운영 환경에서는 수정 필요
    registry.addHandler(chatHandler, "ws/chat").setAllowedOrigins("*");
  }
}

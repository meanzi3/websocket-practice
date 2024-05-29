package com.example.websocket_practice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker // WebSocket 메시지 브로커를 활성화
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  /**
   * STOMP 프로토콜을 사용하는 WebSocket 엔드포인트를 등록
   * @param registry
   */
  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    // /ws-stomp 경로를 통해 클라이언트가 WebSocket에 연결
    // withSockJS() : SockJS를 활성화해 브라우저에서 WebSocket을 사용할 수 있도록
    registry.addEndpoint("/ws-stomp").setAllowedOriginPatterns("*").withSockJS();
  }

  /**
   * 메시지 브로커를 구성
   * @param registry
   */
  @Override
  public void configureMessageBroker(MessageBrokerRegistry registry) {
    // 클라이언트가 메시지를 보낼 때 사용할 경로
    registry.setApplicationDestinationPrefixes("/pub");
    // /chatroom 및 /user 를 구족한 클라이언트에게 메시지 전달
    registry.enableSimpleBroker("/sub");
  }
}

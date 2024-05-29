package com.example.websocket_practice.handler;

import com.example.websocket_practice.dto.ChatMessage;
import com.example.websocket_practice.dto.ChatRoom;
import com.example.websocket_practice.service.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@Slf4j
@RequiredArgsConstructor
public class ChatHandler extends TextWebSocketHandler {

//  // 현재 연결된 WebSocket session 을 저장하는 리스트. 모든 연결된 클라이언트를 관리하기 위해 사용
//  private static List<WebSocketSession> list = new ArrayList<>();

  private final ObjectMapper objectMapper;
  private final ChatService chatService;

  /**
   * client 에게 TextMessage 를 수신했을 때 호출 메소드
   * @param session : 메시지를 보낸 클라이언트의 WebSocket 세션
   * @param message : 수신한 텍스트 메시지
   */
  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    String payload = message.getPayload();
    log.info("payload: " + payload);
    // payload: 메시지의 실제 데이터(내용)

//    for(WebSocketSession sessions : list){
//      sessions.sendMessage(message);
//    } // 현재 연결된 모든 세션에게 수신한 메시지를 그대로 전송.

    ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);
    ChatRoom chatRoom = chatService.findRoomById(chatMessage.getRoomId());
    chatRoom.handleActions(session, chatMessage, chatService);
  }

  /**
   * client 접속 시 호출 메소드
   * @param session : 접속한 클라이언트의 WebSocket 세션
   */
  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    // list.add(session); // 새로운 세션을 리스트에 추가하여 관리
    log.info(session + "클라이언트 접속");
  }

  /**
   * client 접속 해제 시 호출 메소드
   * @param session : 접속 해제한 클라이언트의 WebSocket 세션
   * @param status : 연결 종료 상태
   */
  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception{
    log.info(session + "클라이언트 접속 해제");
    // list.remove(session); // 해당 세션을 리스트에서 제거
  }
}

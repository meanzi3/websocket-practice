package com.example.websocket_practice.controller;

import com.example.websocket_practice.dto.ChatMessage;
import com.example.websocket_practice.dto.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {

  private final SimpMessageSendingOperations messagingTemplate;

  @MessageMapping("/chat/message")
  public void message(ChatMessage message){
    if(MessageType.ENTER.equals(message.getMessageType()))
      message.setMessage(message.getSender() + "님이 입장하셨습니다");
    messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
  }
}

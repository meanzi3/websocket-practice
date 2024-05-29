package com.example.websocket_practice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatMessage {
  private MessageType messageType;
  private String roomId;
  private String sender;
  private String message;
}

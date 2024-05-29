package com.example.websocket_practice.controller;

import com.example.websocket_practice.dto.ChatRoom;
import com.example.websocket_practice.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {

  private final ChatService chatService;

  @PostMapping
  public ChatRoom createRoom(@RequestParam String name) {
    return chatService.createRoom(name);
  }

  @GetMapping
  public List<ChatRoom> findAllRoom() {
    return chatService.findAllRoom();
  }
}

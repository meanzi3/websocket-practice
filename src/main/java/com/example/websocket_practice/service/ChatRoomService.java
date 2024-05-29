package com.example.websocket_practice.service;

import com.example.websocket_practice.dto.ChatRoom;
import com.example.websocket_practice.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
  private final ChatRoomRepository chatRoomRepository;

  public List<ChatRoom> findAllRoom() {
    // 채팅방 생성순서 최근 순으로 반환
    return chatRoomRepository.findAll();
  }

  public ChatRoom findRoomById(Long id) {
    return chatRoomRepository.findById(id).orElse(null);
  }

  public ChatRoom createChatRoom(String name) {
    ChatRoom chatRoom = new ChatRoom(name);
    return chatRoomRepository.save(chatRoom);
  }
}

package com.example.websocket_practice.repository;

import com.example.websocket_practice.dto.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
  ChatRoom findByRoomId(String roomId);
}

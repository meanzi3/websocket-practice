package com.example.websocket_practice.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ChatRoom {

  @Id
  @GeneratedValue
  private Long id;

  private String roomId;
  private String name;

  public ChatRoom(String name){
    this.roomId = UUID.randomUUID().toString();
    this.name = name;
  }

}
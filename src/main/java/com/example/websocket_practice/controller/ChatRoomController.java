package com.example.websocket_practice.controller;

import com.example.websocket_practice.dto.ChatRoom;
import com.example.websocket_practice.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatRoomController {

  private final ChatRoomRepository chatRoomRepository;

  // 채팅 리스트 화면
  @GetMapping("/room")
  public String rooms(Model model){
    List<ChatRoom> chatRooms = chatRoomRepository.findAll();
    model.addAttribute("rooms", chatRooms);
    return "/chat/room";
  }

  // 채팅방 생성
  @PostMapping("/room")
  @ResponseBody
  public ChatRoom createRoom(@RequestParam String name){
    // 새로운 채팅방 생성
    ChatRoom chatRoom = new ChatRoom(name);
    // 채팅방 저장
    chatRoomRepository.save(chatRoom);
    // 생성된 채팅방 반환
    return chatRoom;
  }

  // 채팅방 입장 화면
  @GetMapping("/room/enter/{roomId}")
  public String roomDetail(Model model, @PathVariable String roomId){
    ChatRoom room = chatRoomRepository.findByRoomId(roomId);
    if(room != null) {
      model.addAttribute("room", room);
      return "/chat/roomdetail";
    } else {
      // 채팅방이 없는 경우 예외 처리
      return "redirect:/error";
    }
  }

  // 특정 채팅방 조회
  @GetMapping("/room/{roomId}")
  @ResponseBody
  public ChatRoom roomInfo(@PathVariable String roomId){
    return chatRoomRepository.findByRoomId(roomId);
  }

}

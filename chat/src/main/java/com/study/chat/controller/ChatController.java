package com.study.chat.controller;

import com.study.chat.dto.ChatMessageDto;
import com.study.chat.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@AllArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate template;
    private final RedisTemplate<String, Object> redisTemplate;
    private final ChatService chatService;

    @MessageMapping("/chat")
    public void sendMessage(ChatMessageDto message) {
        System.out.println("Received message: " + message.getContent());
        System.out.println("ChatRoomId: " + message.getChatRoomId()); // 채팅방 ID 확인 (추가)
        chatService.saveMessage(message);
        redisTemplate.convertAndSend("chat." + message.getChatRoomId(), message);
    }
}



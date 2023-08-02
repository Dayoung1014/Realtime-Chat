package com.study.chat.controller;

import com.study.chat.dto.ChatMessageDto;
import com.study.chat.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@AllArgsConstructor
public class ChatApiController {
    private final ChatService chatService;

    @GetMapping("/{chatRoomId}")
    public List<ChatMessageDto> getChatMessages(@PathVariable String chatRoomId) {
        return chatService.getMessages(chatRoomId);
    }
}
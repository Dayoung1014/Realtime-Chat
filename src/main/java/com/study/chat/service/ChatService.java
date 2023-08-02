package com.study.chat.service;
import com.study.chat.dto.ChatMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public ChatService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveMessage(ChatMessageDto message) {
        String listKey = "chat:" + message.getChatRoomId();
        redisTemplate.opsForList().rightPush(listKey, message);
    }

    public List<ChatMessageDto> getMessages(String chatRoomId) {
        String listKey = "chat:" + chatRoomId;
        List<ChatMessageDto> messages = new ArrayList<>();
        List<Object> messageList = redisTemplate.opsForList().range(listKey, 0, -1);

        for (Object messageObj : messageList) {
            ChatMessageDto message = (ChatMessageDto) messageObj;
            messages.add(message);
        }

        return messages;
    }
}


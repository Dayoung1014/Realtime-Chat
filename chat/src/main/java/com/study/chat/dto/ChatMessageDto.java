package com.study.chat.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.io.Serializable;
@Getter
@Builder
public class ChatMessageDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String chatRoomId; // 채팅방 ID
    private String content;
    private String sender;
    private LocalDateTime sentTime;

}

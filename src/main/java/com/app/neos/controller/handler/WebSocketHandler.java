package com.app.neos.controller.handler;

import com.app.neos.domain.chatting.ChattingContentDTO;
import com.app.neos.domain.chatting.ChattingDTO;
import com.app.neos.domain.chatting.ChattingRoomDTO;
import com.app.neos.entity.chatting.ChattingContent;
import com.app.neos.entity.chatting.ChattingRoom;
import com.app.neos.repository.chatting.ChatContentCustomRepository;
import com.app.neos.repository.chatting.ChattingContentRepository;
import com.app.neos.repository.chatting.ChattingCustomRepositoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final ChatContentCustomRepository chatContentCustomRepository;
    private final ChattingCustomRepositoryImpl chattingCustomRepository;
    private final ChattingRoomDTO chattingRoomDTO;


    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        log.info("메세지 전송 = {} : {}", session, message.getPayload());
        String msg = message.getPayload();
        log.info("======");
        log.info(msg);
        log.info("=====");
        ChattingContentDTO chattingContentDTO = objectMapper.readValue(msg, ChattingContentDTO.class);
        log.info(chattingContentDTO.toString());
//        ChattingRoomDTO chattingRoomDTO;

//        ChattingDTO chattingDTO = chattingCustomRepository.findByChatting(chattingContentDTO.getChatting().getChattingId());
//        Long chattingId = chattingCustomRepository.findChattingAndUserId(chattingContentDTO.getReceiverId(),chattingContentDTO.getChattingId());
//        chattingContentDTO.setReceiverId(chattingId);
//        chattingContentDTO.getMyId()값 null
//        ChattingRoomDTO chattingRoomDTO = (ChattingRoomDTO) chatContentCustomRepository.findById(chattingContentDTO.getMyId(), chattingContentDTO.getReceiverId());
//        log.info(chattingRoomDTO.toString());

        log.info(String.valueOf(chattingContentDTO.getReceiverId()));
        log.info(String.valueOf(chattingContentDTO.getMyId()));
        log.info(String.valueOf(session.getAttributes().get("receiver")));
        chattingRoomDTO.handleMessage(session, chattingContentDTO, objectMapper);
//        log.info(chattingRoomDTO.toString());

    }
}
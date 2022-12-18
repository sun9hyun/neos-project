package com.app.neos.repository.chatting;

import com.app.neos.domain.chatting.ChattingContentDTO;
import com.app.neos.domain.chatting.ChattingRoomDTO;


import java.util.List;

public interface ChatContentCustomRepository {
    public List<ChattingRoomDTO> findAllContent(Long loginUser);
//
    public List<ChattingContentDTO> findById(Long chattingRoomId);
//
    public Long findByIdOne(Long chattingRoomId);
//
//    public List<ChattingContentDTO> findLastList(Long receiver);
//
//    public List<ChattingRoomDTO> findByReceiverId(Long receiverId);
//
//    public void updateChattingId(Long loginUser, Long receiverIds,Long chattingId);
}

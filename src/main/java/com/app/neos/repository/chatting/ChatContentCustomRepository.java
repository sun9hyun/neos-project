package com.app.neos.repository.chatting;

import com.app.neos.domain.chatting.ChattingContentDTO;
import com.app.neos.domain.chatting.ChattingRoomDTO;
import com.app.neos.entity.chatting.Chatting;
import com.app.neos.entity.user.User;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ChatContentCustomRepository {
    public List<ChattingRoomDTO> findAllContent(Long loginUser);

    public List<ChattingContentDTO> findById(Long receiverId, Long myId);

    public ChattingContentDTO findByIdOne(Long receiver);

    public List<ChattingContentDTO> findLastList(Long receiver);

    public List<ChattingRoomDTO> findByReceiverId(Long receiverId);
}

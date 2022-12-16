package com.app.neos.repository.chatting;

import com.app.neos.domain.chatting.ChattingDTO;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.Map;

@Repository
public interface ChattingCustomRepository {


////    채팅방에서 채팅방(세션에) 맞는 아이디 찾기 (1개)
//    public ChattingDTO findByChatting(Long chattingId);
//    채팅방에서 상대방아이디 찾는 쿼리 작성 (상대방아이디 채팅방 아이디 값이랑 같고)
    public Long findChattingAndUserId(Long chattingId);

}

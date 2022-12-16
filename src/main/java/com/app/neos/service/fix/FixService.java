package com.app.neos.service.fix;

import com.app.neos.domain.chatting.ChattingContentDTO;
import com.app.neos.domain.chatting.ChattingDTO;
import com.app.neos.domain.chatting.ChattingRoomDTO;
import com.app.neos.domain.community.CommunityDTO;
import com.app.neos.entity.chatting.Chatting;
import com.app.neos.entity.chatting.ChattingContent;
import com.app.neos.entity.community.Community;
import com.app.neos.entity.user.User;
import com.app.neos.repository.chatting.*;
import com.app.neos.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FixService {
    private final ChattingRepository chattingRepository;
    private final ChattingCustomRepository chattingCustomRepository;
    private final ChatRoomCustomRepository chatRoomCustomRepository;
    private final ChattingContentRepository chattingContentRepository;
    private final ChatContentCustomRepository chatContentCustomRepository;
    private final ChattingRoomRepository chattingRoomRepository;
    private final UserRepository userRepository;
    private final ChattingContentDTO chattingContentDTO;
    private Map<String, ChattingDTO> chatRoomMap;

    @PostConstruct
    private void init(){
        chatRoomMap = new LinkedHashMap<>();
    }



   //    채팅방 목록 조회
    public List<ChattingRoomDTO> findContent(Long loginUser) {
        return chatContentCustomRepository.findAllContent(loginUser);
    }

    //    상대방 아이디에 따른 채팅방 내용 조회
    public List<ChattingContentDTO> findByIdTest(Long receiverId,Long myId){
        return chatContentCustomRepository.findById(receiverId, myId);
    }

    //    상대방 아이디 1개 조회하기
    public ChattingContentDTO findByIdOneTest(Long receiver) {
        return chatContentCustomRepository.findByIdOne(receiver);
    }

    //    마지막 채팅 조회
    public List<ChattingContentDTO> findLastTest(Long receiver) {
        return chatContentCustomRepository.findLastList(receiver);
    }

    //    채팅방 저장
    public void saveChatting(ChattingContentDTO chattingContentDTO) {
        ChattingContent chattingContent = chattingContentDTO.toEntity();

        Long myId = chattingContentDTO.getMyId();
        Long receiverId = chattingContentDTO.getReceiverId();
        Long chattingId = chattingContentDTO.getChattingId();

        User my = userRepository.findById(myId).get();
        User receiver = userRepository.findById(receiverId).get();
        Chatting chatting = chattingRepository.findById(chattingId).get();

        chattingContent.changeMy(my);
        chattingContent.changeReceiver(receiver);
        chattingContent.changeChatting(chatting);

        chattingContentRepository.save(chattingContent);
        System.out.printf("!!!!!!!!!!!!!!!!!!!!!!!!!!" + chattingContentDTO);
    }


    //    채팅방 나가기
    public void deleteRoom(ChattingRoomDTO chattingRoomDTO) {
        chattingContentRepository.deleteById(chattingRoomDTO.getChattingRoomId());
    }

    // 상대방 아이디 하나 구하기
    public ChattingContentDTO findByReceiverId(Long receiver) {
        return chatContentCustomRepository.findByIdOne(receiver);
    }


//    public ChattingDTO findByChattingId(Long chattingId) {
//        return chattingCustomRepository.findByChatting(chattingId);
//    }


    //    채팅방에서 상대방아이디 찾는 쿼리 작성 (상대방아이디 채팅방 아이디 값이랑 같고)
    public Long findChattingAndUserIdRoom(Long chattingId){
        return chattingCustomRepository.findChattingAndUserId(chattingId);
    }

    public List<ChattingRoomDTO> findByReceiverIdChatting(HttpServletRequest request){
        HttpSession session = request.getSession();
        Long receiverId = (Long)session.getAttribute("receiver");

        return chatContentCustomRepository.findByReceiverId(receiverId);
    }
}

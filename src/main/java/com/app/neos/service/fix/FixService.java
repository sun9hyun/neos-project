package com.app.neos.service.fix;

import com.app.neos.domain.chatting.ChattingContentDTO;

import com.app.neos.domain.chatting.ChattingRoomDTO;
import com.app.neos.entity.chatting.ChattingContent;
import com.app.neos.entity.chatting.ChattingRoom;
import com.app.neos.entity.user.User;
import com.app.neos.repository.chatting.*;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.type.chatting.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FixService {

    private final ChatContentCustomRepository chatContentCustomRepository;
    private final ChattingContentRepository chattingContentRepository;
    private final UserRepository userRepository;
    private final ChattingRoomRepository chattingRoomRepository;


    //    채팅방 목록 조회
    public List<ChattingRoomDTO> findContent(Long loginUser) {
        return chatContentCustomRepository.findAllContent(loginUser);
    }


    //    채팅방 아이디 따른 채팅방 내용 조회
    public List<ChattingContentDTO> findByIdTest(Long chattingRoomId) {
        System.out.println("!!!!!!!!!!!!!!!!!!내용나옴요 ");
        return chatContentCustomRepository.findById(chattingRoomId);
    }

    //    채팅 내용 저장
    public void saveChatting(ChattingContentDTO chattingContentDTO) {
        ChattingContent chattingContent = chattingContentDTO.toEntity();

        chattingContent.changeMessageType(chattingContentDTO.getMessageType());
        chattingContent.changeChattingRoom(chattingRoomRepository.findById(chattingContentDTO.getChattingRoomId()).get());
        chattingContent.changeWriter(userRepository.findById(chattingContentDTO.getWriterId()).get());
        chattingContentRepository.save(chattingContent);
        System.out.printf("!!!!!!!!!!!!!!!!!!!!!!!!!!저장됨 " + chattingContentDTO);

        //    채팅방 읽음,안읽음 처리
    }
        public void readChange(Long chattingRoomId, Long writerId) {
            List<ChattingContentDTO> chatDTOList = findByIdTest(chattingRoomId); // 해당 채팅방의 모든 채팅을 가져옴
            List<ChattingContent> findByIdTest = new ArrayList<>();

            /*내가 입력한 메세지가 아닌 상대방이 입력한 메세지만 뽑기*/
            for(ChattingContentDTO chatDTO : chatDTOList) {
                if(chatDTO.getWriter().getUserId() != writerId) {
                    findByIdTest.add(chatDTO.toEntity());
                }
            }
            /*상대가 보낸 모든 채팅은 읽음으로 변경*/
            for(int i = 0; i < findByIdTest.size(); i++) {
                ChattingContent chattingContent = chattingContentRepository.findById(findByIdTest.get(i).getChattingContentId()).get();
                chattingContent.changeMessageType(MessageType.UNREAD);
                chattingContentRepository.save(chattingContent);
            }
        }

    // 채팅방 하나 구하기
    public Long findByReceiverId(Long chattingRoomId) {
        return chatContentCustomRepository.findByIdOne(chattingRoomId);
    }



    //    채팅방 나가기
//    public void deleteRoom(Long chattingRoomId) {
//        chattingContentRepository.deleteById(chattingRoomId);
//    }


    //    채팅방 만들기
    public void createChattingRoom(ChattingRoomDTO chattingRoomDTO) {
        ChattingRoom chattingRoom = chattingRoomDTO.toEntity();

        chattingRoom.changeReceiverRoom(userRepository.findById(chattingRoomDTO.getReceiverRoomId()).get());
        chattingRoom.changeMyRoom(userRepository.findById(chattingRoomDTO.getMyRoomId()).get());
        chattingRoomRepository.save(chattingRoom);
        System.out.println("서비스~~~~~~~~~~~~~~~~~~~~~" +chattingRoom);
    }

////    public ChattingDTO findByChattingId(Long chattingId) {
////        return chattingCustomRepository.findByChatting(chattingId);
////    }
//
//
//    //    채팅방에서 상대방아이디 찾는 쿼리 작성 (상대방아이디 채팅방 아이디 값이랑 같고)
//    public Long findChattingAndUserIdRoom(Long chattingId){
//        return chattingCustomRepository.findChattingAndUserId(chattingId);
//    }
//
//    public List<ChattingRoomDTO> findByReceiverIdChatting(HttpServletRequest request){
//        HttpSession session = request.getSession();
//        Long receiverId = (Long)session.getAttribute("receiver");
//
//        return chatContentCustomRepository.findByReceiverId(receiverId);
//    }
//}

}
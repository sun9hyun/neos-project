package com.app.neos.service.store;

import com.app.neos.domain.store.StoreReplyDTO;
import com.app.neos.entity.store.Store;
import com.app.neos.entity.store.StoreReply;
import com.app.neos.entity.user.User;
import com.app.neos.repository.store.StoreReplyCustomRepository;
import com.app.neos.repository.store.StoreReplyRepository;
import com.app.neos.repository.store.StoreRepository;
import com.app.neos.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreReplyService {
    private final StoreReplyRepository storeReplyRepository;
    private final StoreReplyCustomRepository storeReplyCustomRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    // 자료상점 댓글 작성
    @Transactional(rollbackFor = Exception.class)
    public void saveReply(StoreReplyDTO storeReplyDTO){
        System.out.println("*********************서비스 : " + storeReplyDTO + "*********************");

        StoreReply storeReply = storeReplyDTO.toEntity();
        User user = userRepository.findById(storeReplyDTO.getUserId()).get();
        Store store = storeRepository.findById(storeReplyDTO.getStoreId()).get();
        storeReply.changeUser(user);
        storeReply.changeStore(store);

        storeReplyRepository.save(storeReply);
    }

    // 자료상점 댓글 수정
    @Transactional(rollbackFor = Exception.class)
    public void updateReply(StoreReplyDTO storeReplyDTO){
        updateEntity(storeReplyDTO);
    }

    @Transactional
    public void updateEntity(StoreReplyDTO storeReplyDTO){
        StoreReply storeReply = storeReplyRepository.findById(storeReplyDTO.getStoreReplyId()).get();
        storeReply.update(storeReplyDTO);
    }

    // 자료상점 댓글 개별 삭제
    @Transactional(rollbackFor = Exception.class)
    public void deleteByReplyId(Long storeReplyId){
        storeReplyRepository.deleteById(storeReplyId);
    }
    
    // 자료상점 댓글 전체 삭제
    public void deleteByStoreId(Long storeId){
        storeReplyCustomRepository.deleteByStoreId(storeId);
    }

    // 자료상점 댓글 전체 조회
    public List<StoreReplyDTO> findReply(Long storeId) {
        return storeReplyCustomRepository.findAllReply(storeId);
    }

    // 자료상점 댓글 개별 조회
    public StoreReplyDTO findOneReply(Long storeReplyId) {
        return storeReplyCustomRepository.findOneReply(storeReplyId);
    }

    // 자료상점 댓글 카운팅
    public int countReply(Long storeId){
        return storeReplyCustomRepository.findReplyCount(storeId);
    }
}

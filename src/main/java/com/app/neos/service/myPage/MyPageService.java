package com.app.neos.service.myPage;

import com.app.neos.domain.store.StoreDTO;
import com.app.neos.entity.store.Store;
import com.app.neos.repository.store.StoreCustomRepository;
import com.app.neos.repository.store.StoreReplyCustomRepository;
import com.app.neos.repository.store.StoreReplyRepository;
import com.app.neos.repository.store.StoreRepository;
import com.app.neos.repository.study.StudyCustomRepository;
import com.app.neos.repository.user.UserCustomRepository;
import com.app.neos.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final UserRepository userRepository;
    private final UserCustomRepository userCustomRepository;
    private final StoreCustomRepository storeCustomRepository;
    private final StudyCustomRepository studyCustomRepository;
    
    // 내가 작성한 자료상점 게시글 조회
    public List<StoreDTO> findStoreByUserId(Long userId){
        return storeCustomRepository.findByUserId(userId);
    }

}

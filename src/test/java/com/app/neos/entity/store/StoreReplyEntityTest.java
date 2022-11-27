package com.app.neos.entity.store;


import com.app.neos.domain.store.StoreReplyDTO;
import com.app.neos.repository.store.StoreReplyRepository;
import com.app.neos.repository.store.StoreRepository;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.type.store.StoreReplySecret;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class StoreReplyEntityTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    StoreReplyRepository storeReplyRepository;

    @Test
    public void saveTest(){
        StoreReplyDTO storeReplyDTO = new StoreReplyDTO();
        storeReplyDTO.setStoreReplyContent("테스트용 댓글 내용");
        storeReplyDTO.setStoreReplySecret(StoreReplySecret.PUBLIC);

        storeReplyDTO.setUser(userRepository.findById(2L).get());
        storeReplyDTO.setStore(storeRepository.findById(4L).get());

        StoreReply storeReply = storeReplyDTO.toEntity();
        storeReply.changeUser(storeReplyDTO.getUser());
        storeReply.changeStore(storeReplyDTO.getStore());

        storeReplyRepository.save(storeReply);
    }

    @Test
    public void updateTest(){
        StoreReply storeReply = storeReplyRepository.findById(9L).get();

        StoreReplyDTO storeReplyDTO = new StoreReplyDTO();
        storeReplyDTO.setStoreReplyContent("수정222 테스트용 댓글");

        storeReply.update(storeReplyDTO);
    }

    @Test
    public void deleteTest(){storeReplyRepository.deleteById(9L);}

}

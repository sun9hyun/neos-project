package com.app.neos.entity.store;


import com.app.neos.domain.store.StoreReReplyDTO;
import com.app.neos.repository.store.StoreReReplyRepository;
import com.app.neos.repository.store.StoreReplyRepository;
import com.app.neos.repository.user.UserRepository;
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
public class StoreReReplyEntityTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    StoreReplyRepository storeReplyRepository;
    @Autowired
    StoreReReplyRepository storeReReplyRepository;

    @Test
    public void saveTest(){
        StoreReReplyDTO storeReReplyDTO = new StoreReReplyDTO();
        storeReReplyDTO.setStoreReReplyContent("테스트 대댓글입니당");

        storeReReplyDTO.setUser(userRepository.findById(2L).get());
        storeReReplyDTO.setStoreReply(storeReplyRepository.findById(5L).get());

        StoreReReply storeReReply = storeReReplyDTO.toEntity();
        storeReReply.changeStoreReply(storeReReplyDTO.getStoreReply());
        storeReReply.changeUser(storeReReplyDTO.getUser());

        storeReReplyRepository.save(storeReReply);
    }

    @Test
    public void updateTest(){
        StoreReReply storeReReply = storeReReplyRepository.findById(6l).get();

        StoreReReplyDTO storeReReplyDTO = new StoreReReplyDTO();
        storeReReplyDTO.setStoreReReplyContent("수정 댓글");

        storeReReply.update(storeReReplyDTO);
    }

    @Test
    public void deleteTest(){storeReReplyRepository.deleteById(6L);}

}

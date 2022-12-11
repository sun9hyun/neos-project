package com.app.neos.service.store;

import com.app.neos.domain.store.StoreReplyDTO;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.type.store.StoreReplySecret;
import com.querydsl.jpa.impl.JPAQueryFactory;
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
public class StoreReplyServiceTest {

    @Autowired
    StoreReplyService storeReplyService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Test
    public void saveReplyTest(){
        StoreReplyDTO storeReplyDTO = new StoreReplyDTO();
        storeReplyDTO.setStoreReplyContent("test");
        storeReplyDTO.setStoreReplySecret(StoreReplySecret.PUBLIC);
        storeReplyDTO.setUserId(1L);
        storeReplyDTO.setStoreId(201L);
        storeReplyService.saveReply(storeReplyDTO);
    }

    @Test
    public void updateReplyTest(){
        StoreReplyDTO storeReplyDTO = storeReplyService.findOneReply(213L);
        storeReplyDTO.setStoreReplyContent("수정된 댓글");
        storeReplyService.updateReply(storeReplyDTO);
    }

    @Test
    public void deleteByReplyIdTest(){
        storeReplyService.deleteByReplyId(211L);
    }

    @Test
    public void deleteByStoreIdTest(){
        storeReplyService.deleteByStoreId(201L);
    }

    @Test
    public void findReplyTest(){
        storeReplyService.findReply(201L).stream().map(StoreReplyDTO::getStoreReplyContent).forEach(log::info);
    }

    @Test
    public void countReplyTest(){
        storeReplyService.countReply(201L);
    }

}

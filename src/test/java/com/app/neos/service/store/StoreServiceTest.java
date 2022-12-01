package com.app.neos.service.store;

import com.app.neos.domain.store.StoreDTO;
import com.app.neos.entity.store.Store;
import com.app.neos.entity.user.User;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.service.myPage.MyPageService;
import com.app.neos.type.store.StoreStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class StoreServiceTest {
    @Autowired
    StoreService storeService;

    @Autowired
    UserRepository userRepository;
    // 스토어 글 작성

    @Test
    public void test(){
        userRepository.findAll().stream().map(User::toString).forEach(log::info);
    }
    @Test
    public void saveTest(){
        User user =userRepository.findById(1l).get();
        user.getUserId();

        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setStoreStatus(StoreStatus.FREE);
        storeDTO.setStorePoint(0);
        storeDTO.setStoreTitle("자료판매 테스트 222");
        storeDTO.setStoreContent("아주 좋은 자료입니다.");
        storeDTO.setUser(user);

        Store store = storeDTO.toEntity();
        store.changeUser(storeDTO.getUser());
        storeService.saveStore(store);
    }
}

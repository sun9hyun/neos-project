package com.app.neos.entity.store;

import com.app.neos.domain.store.StoreDTO;
import com.app.neos.repository.store.StoreFileRepository;
import com.app.neos.repository.store.StoreRepository;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.type.store.StoreStatus;
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
public class StoreEntityTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    StoreFileRepository storeFileRepository;

    @Test
    public void saveTest(){
        StoreDTO storeDTO1 = new StoreDTO();
        storeDTO1.setStoreStatus(StoreStatus.FREE);
        storeDTO1.setStorePoint(0);
        storeDTO1.setStoreTitle("자료판매 테스트 1");
        storeDTO1.setStoreContent("아주 좋은 자료입니다.");
        storeDTO1.setUserId(1L);
//        storeDTO1.setUserid(userRepository.findById(2L).get());
//        storeDTO1.setFiles(storeFileRepository.findById(10L).get());

        Store store1 = storeDTO1.toEntity();
        store1.changeUser(userRepository.findById(storeDTO1.getUserId()).get());
        storeRepository.save(store1);
//
//        StoreDTO storeDTO2 = new StoreDTO();
//        storeDTO2.setStoreStatus(StoreStatus.PAY);
//        storeDTO2.setStorePoint(1500);
//        storeDTO2.setStoreTitle("자료판매 테스트 2");
//        storeDTO2.setStoreContent("가보자고");
//        storeDTO2.setUser(userRepository.findById(2L).get());
//
//        Store store2 = storeDTO2.toEntity();
//        store2.changeUser(storeDTO2.getUser());
//        storeRepository.save(store2);
    }

    @Test
    public void updateTest(){
//        Store store = storeRepository.findById(3L).get();
//        StoreDTO storeDTO = new StoreDTO();
//
//        storeDTO.setStoreStatus(StoreStatus.PAY);
//        storeDTO.setStorePoint(500);
//        storeDTO.setStoreTitle("자료상점 제목 수정 테스트");
//        storeDTO.setStoreContent("내용 수정 테스트");
//        storeDTO.setUser(userRepository.findById(2L).get());
//
//        store.changeUser(storeDTO.getUser());
//        store.update(storeDTO);
    }

    @Test
    public void deleteTest(){storeRepository.deleteById(5L);}

}

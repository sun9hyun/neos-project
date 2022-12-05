package com.app.neos.service.store;

import com.app.neos.domain.college.CollegeDTO;
import com.app.neos.domain.store.QStoreDTO;
import com.app.neos.domain.store.StoreDTO;
import com.app.neos.entity.store.QStore;
import com.app.neos.entity.store.Store;
import com.app.neos.entity.user.User;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.service.myPage.MyPageService;
import com.app.neos.type.store.StoreStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class StoreServiceTest {
    @Autowired
    StoreService storeService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JPAQueryFactory jpaQueryFactory;


    @Test
    public void test(){
        userRepository.findAll().stream().map(User::toString).forEach(log::info);
    }

    // 스토어 글 작성
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

//        Store store = storeDTO.toEntity();
//        store.changeUser(storeDTO.getUser());
        storeService.saveStore(storeDTO);
    }

    // 스토어 목록 테스트
    @Test
    public void findAllTest(){
//        List<StoreDTO> storeDTOS = jpaQueryFactory.select(new QStoreDTO(
//                QStore.store.storeId,
//                QStore.store.storeStatus,
//                QStore.store.storePoint,
//                QStore.store.storeTitle,
//                QStore.store.storeContent,
//                QStore.store.user,
//                QStore.store.files
//        ))
//                .from(QStore.store)
//                .fetch();

//        storeService.findStore();
        storeService.findStore().stream().map(StoreDTO::toString).forEach(log::info);

        // 스토어 페이징 테스트
//        @Test
//        public void findAllPage(){
//            //화면에서 파라미터로 받기
//            Pageable pageable = PageRequest.of(1, 10);
//
//        }
    }
}

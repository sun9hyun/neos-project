package com.app.neos.service.store;

import com.app.neos.domain.college.CollegeDTO;
import com.app.neos.domain.store.QStoreDTO;
import com.app.neos.domain.store.StoreDTO;
import com.app.neos.domain.store.StoreFlieDTO;
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

import java.awt.*;
import java.util.ArrayList;
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

    // 자료상점 게시글 작성
    @Test
    public void saveTest(){

        List<StoreFlieDTO> files = new ArrayList<>();
//        StoreFlieDTO storeFlieDTO1 = new StoreFlieDTO();
//        storeFlieDTO1.setStoreFileName("day01.txt");
//        storeFlieDTO1.setStoreFileSize(30L);
//        storeFlieDTO1.setStoreFilePath("test");
//        storeFlieDTO1.setStoreFileUuid("abc111");
////        storeFlieDTO1.setStoreId(26L);
//
//        StoreFlieDTO storeFlieDTO2 = new StoreFlieDTO();
//        storeFlieDTO2.setStoreFileName("day02.txt");
//        storeFlieDTO2.setStoreFileSize(30L);
//        storeFlieDTO2.setStoreFilePath("test");
//        storeFlieDTO2.setStoreFileUuid("abc222");
////        storeFlieDTO2.setStoreId(26L);
//
//        StoreFlieDTO storeFlieDTO3 = new StoreFlieDTO();
//        storeFlieDTO3.setStoreFileName("day02.txt");
//        storeFlieDTO3.setStoreFileSize(30L);
//        storeFlieDTO3.setStoreFilePath("test");
//        storeFlieDTO3.setStoreFileUuid("abc222");
////        storeFlieDTO3.setStoreId(26L);
//
//        files.add(storeFlieDTO1);
//        files.add(storeFlieDTO2);
//        files.add(storeFlieDTO3);


        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setStoreStatus(StoreStatus.FREE);
        storeDTO.setStorePoint(0);
        storeDTO.setStoreTitle("첨부파일이 없는 제목");
        storeDTO.setStoreContent("첨부파일이 없는 내용");
        storeDTO.setUserId(1L);
        storeDTO.setStoreFlieDTOS(files);


//        User user =userRepository.findById(1l).get();
//
//        Store store = storeDTO.toEntity();
//        store.changeUser(user);
//        store.changeFiles(files);
//        store.changeUser(storeDTO.getUserId());
        storeService.saveStore(storeDTO);
    }


    // 자료상점 게시글 수정
    @Test
    public void updateTest(){

        List<StoreFlieDTO> files = new ArrayList<>();
        StoreFlieDTO storeFlieDTO1 = new StoreFlieDTO();
        storeFlieDTO1.setStoreFileName("첨부파일 수정1");
        storeFlieDTO1.setStoreFileSize(30L);
        storeFlieDTO1.setStoreFilePath("test");
        storeFlieDTO1.setStoreFileUuid("abc111");
        storeFlieDTO1.setStoreId(25L);

        StoreFlieDTO storeFlieDTO2 = new StoreFlieDTO();
        storeFlieDTO2.setStoreFileName("첨부파일 수정2");
        storeFlieDTO2.setStoreFileSize(30L);
        storeFlieDTO2.setStoreFilePath("test");
        storeFlieDTO2.setStoreFileUuid("abc222");
        storeFlieDTO2.setStoreId(25L);

        StoreFlieDTO storeFlieDTO3 = new StoreFlieDTO();
        storeFlieDTO3.setStoreFileName("첨부파일 수정3");
        storeFlieDTO3.setStoreFileSize(30L);
        storeFlieDTO3.setStoreFilePath("test");
        storeFlieDTO3.setStoreFileUuid("abc222");
        storeFlieDTO3.setStoreId(25L);

        files.add(storeFlieDTO1);
        files.add(storeFlieDTO2);
        files.add(storeFlieDTO3);


        StoreDTO storeDTO = storeService.findStoreOne(25L);
        storeDTO.setStoreTitle("자료상점 수정");
        storeDTO.setUserId(1L);
        storeDTO.setStoreId(25L);
        storeDTO.setStoreFlieDTOS(files);

//        User user =userRepository.findById(1l).get();
//
//        Store store = storeDTO.toEntity();
//        store.changeUser(user);
//        store.changeFiles(files);
//        store.changeUser(storeDTO.getUserId());
        System.out.println("*********************" + storeDTO.getStoreId());
        storeService.updateStore(storeDTO);
    }

    // 자료상점 게시글 삭제
    @Test
    public void deleteTest(){
        Long storeId= 128L;
//        StoreDTO storeDTO = storeService.findStoreOne(storeId);
        storeService.deleteByStoreId(storeId);
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

package com.app.neos.service.study;

import com.app.neos.repository.study.StudyFollowCustomRepository;
import com.app.neos.repository.study.StudyRepository;
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
public class StudyFollowTest {
    @Autowired
    StudyRepository studyRepository;
    @Autowired
    StudyFollowCustomRepository studyFollowCustomRepository;

    @Test
    public void test1(){
        log.info("좋아요 수 :"+studyRepository.findById(235l).get().getFollowList().size());
    }
    @Test
    public void test2(){
        log.info("좋아요 수 :"+studyRepository.findById(234l).get().getFollowList().size());
    }

    @Test
    public void duplicateTest1(){
        log.info("이미 ? "+studyFollowCustomRepository.duplicate(7l,235l));
        log.info("이미 ? "+studyFollowCustomRepository.duplicate(7l,234l));
        log.info("이미 ? "+studyFollowCustomRepository.duplicate(7l,233l));
    }

    @Test
    public void test3(){
        log.info(studyRepository.findById(235l).get().toDTO().toString());
    }

}

package com.app.neos.service.study;

import com.app.neos.entity.study.Study;
import com.app.neos.repository.study.StudyCustomRepository;
import com.app.neos.repository.study.StudyRepository;
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

@SpringBootTest
@RequiredArgsConstructor
@Slf4j
@Transactional
@Rollback(false)
public class StudyServiceTest {
    @Autowired
    StudyRepository studyRepository;
    @Autowired
    StudyCustomRepository studyCustomRepository;
    @Autowired
    StudyService studyService;

    @Test
    public void findTest(){
        log.info(studyRepository.findById(8l).get().toDTO().toString());
        log.info(studyRepository.findById(10l).get().toDTO().toString());
    }

    @Test
    public void findAllTest(){
        Pageable pageable = PageRequest.of(1,16);
        studyCustomRepository.findAllPage(pageable).stream().map(Study::toDTO).forEach(i->log.info(i.toString()));
    }


    @Test
    public void sizeTesT(){
        log.info("크기: "+studyCustomRepository.getTotal());
    }
    @Test
    public void findAllTest2(){
        Pageable pageable = PageRequest.of(1,16);
        log.info("ss"+studyCustomRepository.findAllPage(pageable).getTotalPages());
    }

    @Test
    public void findCustomTest(){
        Pageable pageable = PageRequest.of(6,16);
        log.info("d"+studyCustomRepository.findAllPage(pageable).isLast());
    }


}

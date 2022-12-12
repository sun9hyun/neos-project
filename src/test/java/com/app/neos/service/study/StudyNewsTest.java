package com.app.neos.service.study;

import com.app.neos.domain.study.StudyNewsDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@SpringBootTest
@Slf4j

public class StudyNewsTest {
    @Autowired private StudyNewsService studyNewsService;

    @Test
    public void test1(){
        Pageable pageable = PageRequest.of(0,10);
        studyNewsService.showList(8l,pageable).stream().map(StudyNewsDTO::toString).forEach(log::info);
    }
}

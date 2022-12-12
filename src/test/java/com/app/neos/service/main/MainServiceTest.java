package com.app.neos.service.main;

import com.app.neos.domain.study.StudyDTO;
import com.app.neos.entity.study.Study;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Access;

@Service
@RequiredArgsConstructor
public class MainServiceTest {
    @Autowired
    MainService mainService;

    @Test
    public void findAll(){
        StudyDTO studyDTO = new StudyDTO();



    }
}

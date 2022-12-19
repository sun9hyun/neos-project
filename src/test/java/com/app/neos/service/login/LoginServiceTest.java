package com.app.neos.service.login;

import com.app.neos.domain.study.StudyDTO;
import com.app.neos.entity.study.Study;
import com.app.neos.entity.user.User;
import com.app.neos.repository.study.StudyRepository;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.type.study.StudyRecruitStatus;
import com.app.neos.type.study.StudyStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class LoginServiceTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    StudyRepository studyRepository;

    @BeforeEach
    public void set(){




    }
    @Test
    public void user1(){
        ArrayList<String> types = new ArrayList<>();
        ArrayList<String> cities = new ArrayList<>();
        ArrayList<Integer> people = new ArrayList<>();
        people.add(2);
        people.add(3);
        people.add(4);
        people.add(5);
        people.add(6);
        people.add(7);
        people.add(8);
        people.add(9);
        types.add("전공");
        types.add("교양");
        types.add("공통");
        cities.add("all");
        cities.add("서울특별시");
        cities.add("경기도");
        cities.add("부산광역시");
        cities.add("인천광역시");
        cities.add("대구광역시");
        cities.add("경상남도");
        cities.add("경상북도");
        cities.add("충청남도");
        cities.add("충청북도");
        cities.add("전라남도");
        cities.add("전라북도");
        cities.add("광주광역시");
        cities.add("강원도");
        cities.add("울산광역시");
        cities.add("제주특별자치도");
        cities.add("세종특별자치시");

        Optional<User> optionalUser = userRepository.findById(54l);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            for (int i = 0 ; i < 17; i++){
                StudyDTO dto = new StudyDTO();
                dto.setStudyTitle("스터디 모집글 "+(i+1));
                dto.setStudyContent("스터디 모집 내용"+(i+1));
                dto.setStudyType(types.get(i%3));
                dto.setStudyKeyword("스터디 키워드 "+(i+1));
                dto.setStudyO2o("all");
                dto.setStudyCity(cities.get(i));
                dto.setStudySupport(people.get(i%8));
                dto.setStudyRecruitStatus(StudyRecruitStatus.RECRUITING);
                dto.setStudyStatus(StudyStatus.READY);
                dto.setStudyView(0);
                dto.setStudyEndDate(LocalDate.now().plusMonths(6));
                Study entity = dto.toEntity();
                entity.changeUser(user);
                studyRepository.save(entity);
            }
        }



    }



}
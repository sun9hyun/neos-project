package com.app.neos.entity.college;

import com.app.neos.domain.college.CollegeDTO;
import com.app.neos.entity.user.User;
import com.app.neos.repository.college.CollegeCustomRepository;
import com.app.neos.repository.college.CollegeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class CollegeEntityTest {
    @Autowired
    CollegeRepository collegeRepository;

    @Autowired
    CollegeCustomRepository collegeCustomRepository;

//    saveTest
    @Test
    public void saveTest(){
        CollegeDTO collegeDTO = new CollegeDTO();

        collegeDTO.setCollegeCity("강원도");
        collegeDTO.setCollegeName("강릉원주대학교");
        collegeDTO.setCollegeLogoFile("/images/admin/college/gangneungCollege.jpg");
        collegeDTO.setCollegeEmailDomain("www.gangneung.ac.kr");

        College college = collegeDTO.toEntity();

        collegeRepository.save(college);
    }


    @Test
    public void updateTest2(){
        CollegeDTO collegeDTO = new CollegeDTO();

        collegeDTO.setCollegeCity("경상남도");
        collegeDTO.setCollegeName("문성대학교");
        collegeDTO.setCollegeLogoFile("/images/admin/college/changwonCollege.jpg");
        collegeDTO.setCollegeEmailDomain("www.changwon.ac.kr");


        collegeRepository.findById(2l).get().update(collegeDTO);
    }


//    페이지 처리 Test
    @Test
    public void pageTest(){
        Pageable pageable = PageRequest.of(0,6);
        collegeCustomRepository.findAllPage(pageable).stream().map(CollegeDTO::toString).forEach(log::info);

//        collegeCustomRepository.findAllPage(pageable).getContent().stream().map(CollegeDTO::toString).forEach(log::info);
    }


//    대학 상세보기 Test
    @Test
    public void findByNameTest(){
        log.info(collegeCustomRepository.findByCollegeId(15L).toString());
    }


//    대학 유저 수
    @Test
    public void countByUser(){
        log.info(collegeCustomRepository.countByUser(15L) + "");
    }

}

package com.app.neos.entity.college;

import com.app.neos.domain.college.CollegeDTO;
import com.app.neos.repository.college.CollegeRepository;
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
public class CollegeEntityTest {
    @Autowired
    CollegeRepository collegeRepository;

    @Test
    public void saveTest(){
        CollegeDTO collegeDTO = new CollegeDTO();

        collegeDTO.setCollegeCity("서울특별시");
        collegeDTO.setCollegeName("고려대학교");
        collegeDTO.setCollegeLogoFile("대학 이미지");
        collegeDTO.setCollegeEmailDomain("www.naver.com");

        College college = collegeDTO.toEntity();

        collegeRepository.save(college);
    }


}

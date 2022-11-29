package com.app.neos.service.admin;

import com.app.neos.domain.college.CollegeDTO;
import com.app.neos.entity.college.College;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class AdminServiceTest {
    @Autowired
    AdminCollegeService adminCollegeService;

    @Test
    public void saveTest(){
        CollegeDTO collegeDTO = new CollegeDTO();
        collegeDTO.setCollegeName("고려대학교");
        collegeDTO.setCollegeCity("서울특별시");
        collegeDTO.setCollegeEmailDomain("이메일");
        collegeDTO.setCollegeLogoFile("이미지");

        College college = collegeDTO.toEntity();

        adminCollegeService.saveCollege(college);

    }}

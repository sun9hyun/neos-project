package com.app.neos.service.admin;

import com.app.neos.domain.college.CollegeDTO;
import com.app.neos.domain.college.QCollegeDTO;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.college.College;
import com.app.neos.entity.college.QCollege;
import com.app.neos.repository.admin.AdminCommunityReplyRepository;
import com.app.neos.repository.admin.AdminCommunityRepository;
import com.app.neos.repository.admin.AdminStudyFeedReplyRepository;
import com.app.neos.repository.store.StoreRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class AdminServiceTest {
    @Autowired
    AdminCollegeService adminCollegeService;

    @Autowired
    AdminService adminService;

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    AdminStudyFeedReplyRepository adminStudyFeedReplyRepository;

    @Autowired
    AdminCommunityReplyRepository adminCommunityReplyRepository;

    @Autowired
    AdminCommunityRepository adminCommunityRepository;




    @Autowired
    JPAQueryFactory jpaQueryFactory;

//    대학 등록 테스트
    @Test
    public void saveTest(){
        CollegeDTO collegeDTO = new CollegeDTO();
        collegeDTO.setCollegeName("고려대학교");
        collegeDTO.setCollegeCity("서울특별시");
        collegeDTO.setCollegeEmailDomain("이메일");
        collegeDTO.setCollegeLogoFile("이미지");

        College college = collegeDTO.toEntity();

        adminCollegeService.saveCollege(college);
    }

//    대학 목록 테스트
    @Test
    public void findAll() {
        List<CollegeDTO> collegeDTOS = jpaQueryFactory.select(new QCollegeDTO(
                QCollege.college.collegeId,
                QCollege.college.collegeCity,
                QCollege.college.collegeName,
                QCollege.college.collegeLogoFile,
                QCollege.college.collegeEmailDomain,
                QCollege.college.createdDate,
                QCollege.college.updatedDate
        ))
                .from(QCollege.college)
                .fetch();
    }

//    대학 상세보기 테스트
    @Test
    public void findByNameTest(){

    }


//    배너 아이디로 조회
    @Test
    public void findByBannerIdTest(){
        adminService.findByBannerId(12L);
    }


//    유저 아이디로 조회
    @Test
    public void findByUserId(){
        log.info(adminService.findByUserId(2L).toString());
    }

    @Test
    public void findByUserDTDId(){
        log.info(adminService.findByUserDTOId(2L).toString());
    }

    @Test
    public void findFeedCount(){
        log.info(adminStudyFeedReplyRepository.findAllByStudyFeedReplyWriter_UserId(2L).size() + "");
//        log.info(adminCommunityReplyRepository.findByUser_UserId(2L).size() + "");
//        log.info(adminCommunityRepository.findByUser_UserId(2L).size()+ "");
    }

}

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


        CollegeDTO collegeDTO1 = new CollegeDTO();

        collegeDTO1.setCollegeCity("강원도");
        collegeDTO1.setCollegeName("강릉원주대학교");
        collegeDTO1.setCollegeLogoFile("/images/admin/college/gangneungCollege.jpg");
        collegeDTO1.setCollegeEmailDomain("www.gangneung.ac.kr");

        College college = collegeDTO1.toEntity();
            collegeRepository.save(college);

        CollegeDTO collegeDTO2 = new CollegeDTO();

        collegeDTO2.setCollegeCity("인천광역시");
        collegeDTO2.setCollegeName("인천대학교");
        collegeDTO2.setCollegeLogoFile("/images/admin/college/incheonCollege.png");
        collegeDTO2.setCollegeEmailDomain("www.inu.ac.kr");

        College college2 = collegeDTO2.toEntity();
            collegeRepository.save(college2);

        CollegeDTO collegeDTO3 = new CollegeDTO();

        collegeDTO3.setCollegeCity("서울특별시");
        collegeDTO3.setCollegeName("서울대학교");
        collegeDTO3.setCollegeLogoFile("/images/admin/college/seoulCollege.png");
        collegeDTO3.setCollegeEmailDomain("www.snu.ac.kr");

        College college3 = collegeDTO3.toEntity();
        collegeRepository.save(college3);

        CollegeDTO collegeDTO4 = new CollegeDTO();

        collegeDTO4.setCollegeCity("대전광역시");
        collegeDTO4.setCollegeName("대전대학교");
        collegeDTO4.setCollegeLogoFile("/images/admin/college/dageonCollege.jpg");
        collegeDTO4.setCollegeEmailDomain("www.dageon.ac.kr");

        College college4 = collegeDTO4.toEntity();
        collegeRepository.save(college4);

        CollegeDTO collegeDTO5 = new CollegeDTO();

        collegeDTO5.setCollegeCity("경상남도");
        collegeDTO5.setCollegeName("경상국립대학교");
        collegeDTO5.setCollegeLogoFile("/images/admin/college/gyeongsangCollege.jpg");
        collegeDTO5.setCollegeEmailDomain("www.gyeongsang.ac.kr");

        College college5 = collegeDTO5.toEntity();
        collegeRepository.save(college5);

        CollegeDTO collegeDTO6 = new CollegeDTO();

        collegeDTO6.setCollegeCity("경상남도");
        collegeDTO6.setCollegeName("문성대학교");
        collegeDTO6.setCollegeLogoFile("/images/admin/college/changwonCollege.jpg");
        collegeDTO6.setCollegeEmailDomain("www.changwon.ac.kr");

        College college6 = collegeDTO6.toEntity();
        collegeRepository.save(college6);

    }


    //    페이지 처리 Test
    @Test
    public void pageTest(){
        Pageable pageable = PageRequest.of(0,10);
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

    //    체크된 대학 삭제
    @Test
    public void deleteTest(){
        String collegeIds = "1,2,3,4,5";
        String[] arCollegeIds = collegeIds.split(",");

        for (int i = 0; i < arCollegeIds.length; i++){
            collegeRepository.deleteById(Long.parseLong(arCollegeIds[i]));
        }
    }


}

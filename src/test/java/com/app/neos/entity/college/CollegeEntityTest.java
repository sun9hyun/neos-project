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
        collegeDTO1.setCollegeEmailDomain("@gwnu.ac.kr");

        College college = collegeDTO1.toEntity();
            collegeRepository.save(college);

        CollegeDTO collegeDTO2 = new CollegeDTO();

        collegeDTO2.setCollegeCity("인천광역시");
        collegeDTO2.setCollegeName("인천대학교");
        collegeDTO2.setCollegeLogoFile("/images/admin/college/incheonCollege.png");
        collegeDTO2.setCollegeEmailDomain("@inha.adu");

        College college2 = collegeDTO2.toEntity();
            collegeRepository.save(college2);

        CollegeDTO collegeDTO3 = new CollegeDTO();

        collegeDTO3.setCollegeCity("서울특별시");
        collegeDTO3.setCollegeName("서울대학교");
        collegeDTO3.setCollegeLogoFile("/images/admin/college/seoulCollege.png");
        collegeDTO3.setCollegeEmailDomain("@snu.ac.kr");

        College college3 = collegeDTO3.toEntity();
        collegeRepository.save(college3);

        CollegeDTO collegeDTO4 = new CollegeDTO();

        collegeDTO4.setCollegeCity("대전광역시");
        collegeDTO4.setCollegeName("대전대학교");
        collegeDTO4.setCollegeLogoFile("/images/admin/college/dageonCollege.jpg");
        collegeDTO4.setCollegeEmailDomain("@dju.ac.kr");

        College college4 = collegeDTO4.toEntity();
        collegeRepository.save(college4);

        CollegeDTO collegeDTO5 = new CollegeDTO();

        collegeDTO5.setCollegeCity("경상남도");
        collegeDTO5.setCollegeName("경상국립대학교");
        collegeDTO5.setCollegeLogoFile("/images/admin/college/gyeongsangCollege.jpg");
        collegeDTO5.setCollegeEmailDomain("@gnu.ac.kr");

        College college5 = collegeDTO5.toEntity();
        collegeRepository.save(college5);

        CollegeDTO collegeDTO6 = new CollegeDTO();

        collegeDTO6.setCollegeCity("서울특별시");
        collegeDTO6.setCollegeName("홍익대학교");
        collegeDTO6.setCollegeLogoFile("/images/admin/college/hongik.jpg");
        collegeDTO6.setCollegeEmailDomain("@hongik.ac.kr");

        College college6 = collegeDTO6.toEntity();
        collegeRepository.save(college6);

        CollegeDTO collegeDTO7 = new CollegeDTO();

        collegeDTO7.setCollegeCity("서울특별시");
        collegeDTO7.setCollegeName("고려대학교");
        collegeDTO7.setCollegeLogoFile("/images/admin/college/koreaCollege.png");
        collegeDTO7.setCollegeEmailDomain("@korea.ac.kr");

        College college7 = collegeDTO7.toEntity();
        collegeRepository.save(college7);

        CollegeDTO collegeDTO8 = new CollegeDTO();

        collegeDTO8.setCollegeCity("서울특별시");
        collegeDTO8.setCollegeName("서강대학교");
        collegeDTO8.setCollegeLogoFile("/images/admin/college/sogangCollege.png");
        collegeDTO8.setCollegeEmailDomain("www.seogang.ac.kr");

        College college8 = collegeDTO8.toEntity();
        collegeRepository.save(college8);

        CollegeDTO collegeDTO9 = new CollegeDTO();

        collegeDTO9.setCollegeCity("서울특별시");
        collegeDTO9.setCollegeName("성균관대학교");
        collegeDTO9.setCollegeLogoFile("/images/admin/college/sungkyunkwanCollege.jpg");
        collegeDTO9.setCollegeEmailDomain("www.sungkyunkwan.ac.kr");

        College college9 = collegeDTO9.toEntity();
        collegeRepository.save(college9);

        CollegeDTO collegeDTO10 = new CollegeDTO();

        collegeDTO10.setCollegeCity("서울특별시");
        collegeDTO10.setCollegeName("연세대학교");
        collegeDTO10.setCollegeLogoFile("/images/admin/college/yenseiCollege.jpg");
        collegeDTO10.setCollegeEmailDomain("@yonsei.ac.kr");

        College college10 = collegeDTO10.toEntity();
        collegeRepository.save(college10);

        CollegeDTO collegeDTO11 = new CollegeDTO();

        collegeDTO11.setCollegeCity("충청남도");
        collegeDTO11.setCollegeName("남서울대학교");
        collegeDTO11.setCollegeLogoFile("/images/admin/college/southseoul.png");
        collegeDTO11.setCollegeEmailDomain("@nsu.ac.kr");

        College college11 = collegeDTO11.toEntity();
        collegeRepository.save(college11);

        CollegeDTO collegeDTO12 = new CollegeDTO();

        collegeDTO12.setCollegeCity("경상남도");
        collegeDTO12.setCollegeName("문성대학교");
        collegeDTO12.setCollegeLogoFile("/images/admin/college/moonsoung.png");
        collegeDTO12.setCollegeEmailDomain("@cmu.ac.kr");

        College college12 = collegeDTO12.toEntity();
        collegeRepository.save(college12);

        CollegeDTO collegeDTO13 = new CollegeDTO();

        collegeDTO13.setCollegeCity("인천광역시");
        collegeDTO13.setCollegeName("인하대학교");
        collegeDTO13.setCollegeLogoFile("/images/admin/college/inHa.png");
        collegeDTO13.setCollegeEmailDomain("www.yensei.ac.kr");

        College college13 = collegeDTO13.toEntity();
        collegeRepository.save(college13);

        CollegeDTO collegeDTO14 = new CollegeDTO();

        collegeDTO14.setCollegeCity("경기도");
        collegeDTO14.setCollegeName("평택대학교");
        collegeDTO14.setCollegeLogoFile("/images/admin/college/pyeongTaek.jpg");
        collegeDTO14.setCollegeEmailDomain("@ptu.ac.kr");

        College college14 = collegeDTO14.toEntity();
        collegeRepository.save(college14);

        CollegeDTO collegeDTO15 = new CollegeDTO();

        collegeDTO15.setCollegeCity("광주광역시");
        collegeDTO15.setCollegeName("조선대학교");
        collegeDTO15.setCollegeLogoFile("/images/admin/college/gwangju.png");
        collegeDTO15.setCollegeEmailDomain("@chosun.ac.kr");

        College college15 = collegeDTO15.toEntity();
        collegeRepository.save(college15);

        CollegeDTO collegeDTO16 = new CollegeDTO();

        collegeDTO16.setCollegeCity("전라남도");
        collegeDTO16.setCollegeName("전남대학교");
        collegeDTO16.setCollegeLogoFile("/images/admin/college/national.jpg");
        collegeDTO16.setCollegeEmailDomain("@jnu.ac.kr");

        College college16 = collegeDTO16.toEntity();
        collegeRepository.save(college16);

        CollegeDTO collegeDTO17 = new CollegeDTO();

        collegeDTO17.setCollegeCity("전라북도");
        collegeDTO17.setCollegeName("전북대학교");
        collegeDTO17.setCollegeLogoFile("/images/admin/college/jeonbuk.jpg");
        collegeDTO17.setCollegeEmailDomain("@jbnu.ac.kr");

        College college17 = collegeDTO17.toEntity();
        collegeRepository.save(college17);

        CollegeDTO collegeDTO18 = new CollegeDTO();

        collegeDTO18.setCollegeCity("부산광역시");
        collegeDTO18.setCollegeName("부산대학교");
        collegeDTO18.setCollegeLogoFile("/images/admin/college/pusan.png");
        collegeDTO18.setCollegeEmailDomain("@pusan.ac.kr");

        College college18 = collegeDTO18.toEntity();
        collegeRepository.save(college18);


        CollegeDTO collegeDTO19 = new CollegeDTO();

        collegeDTO19.setCollegeCity("제주특별시");
        collegeDTO19.setCollegeName("제주대학교");
        collegeDTO19.setCollegeLogoFile("/images/admin/college/jeju.jpg");
        collegeDTO19.setCollegeEmailDomain("@jejunu.ac.kr");

        College college19 = collegeDTO19.toEntity();
        collegeRepository.save(college19);
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

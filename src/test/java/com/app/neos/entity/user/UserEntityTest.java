package com.app.neos.entity.user;

import com.app.neos.domain.user.QUserDTO;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.college.College;
import com.app.neos.repository.college.CollegeRepository;
import com.app.neos.repository.neos.NeosUserCustomRepository;
import com.app.neos.repository.user.*;
import com.app.neos.service.login.LoginService;
import com.app.neos.type.user.UserCollegeMajor;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.app.neos.entity.user.QUser.user;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class UserEntityTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserInterestRepository userInterestRepository;
    @Autowired
    CollegeRepository collegeRepository;
    @Autowired
    JPAQueryFactory jpaQueryFactory;
    @Autowired
    UserCustomRepository userCustomRepository;
    @Autowired
    LoginService loginService;
    @Autowired
    NeosUserCustomRepository neosUserCustomRepository;

    @Test
    public void collegeSave(){
        College college = College.builder().collegeCity("서울특별시").collegeLogoFile("1").collegeEmailDomain("1").collegeName("서울대학교").build();
        College college1 = College.builder().collegeCity("서울특별시").collegeLogoFile("2").collegeEmailDomain("2").collegeName("고려대학교").build();
        College college2 = College.builder().collegeCity("인천광역시").collegeLogoFile("3").collegeEmailDomain("3").collegeName("인하대학교").build();
        College college3 = College.builder().collegeCity("강원도").collegeLogoFile("4").collegeEmailDomain("4").collegeName("원주대학교").build();
        College college4 = College.builder().collegeCity("충청북도").collegeLogoFile("5").collegeEmailDomain("5").collegeName("충북대학교").build();
        College college5 = College.builder().collegeCity("충청남도").collegeLogoFile("6").collegeEmailDomain("6").collegeName("충남대학교").build();
        College college6 = College.builder().collegeCity("대전특별시").collegeLogoFile("7").collegeEmailDomain("7").collegeName("대전대학교").build();

        collegeRepository.save(college);
        collegeRepository.save(college1);
        collegeRepository.save(college2);
        collegeRepository.save(college3);
        collegeRepository.save(college4);
        collegeRepository.save(college5);
        collegeRepository.save(college6);
    }


    @Test
    public void saveTest(){
        for(int i = 0 ; i<20 ; i++) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserNickName("박종우의 분신들");
            userDTO.setUserOAuthId("22222");
            userDTO.setUserOAuthEmail("oauthemail@emailasd.com");
            userDTO.setUserCollegeYear(4);
            userDTO.setUserCollegeEmail("email@eamil.com");
            userDTO.setUserCollegeCertify("true");
            userDTO.setUserNeosPowerLevel(1);
            userDTO.setUserNeosPowerAbility(0);
            userDTO.setUserNeosBadge("1");
            userDTO.setUserNeosPoint(0);
            userDTO.setUserChattingPoint(1000);
            userDTO.setUserIntroduce("설정전");
            userDTO.setUserFile("기본");


            User user1 = userDTO.toEntity();
            user1.changeCollege(collegeRepository.findById(2l).get());

            userRepository.save(user1);
        }
    }

    @Test
    public void updateUserTest(){
        User user = userRepository.findById(2l).get();
        UserDTO userDTO = new UserDTO();
        userDTO.setUserNickName("박종dd우");
        userDTO.setUserOAuthId("22222");
        userDTO.setUserOAuthEmail("oauthemail@emailasd.com");
        userDTO.setUserCollegeYear(4);
        userDTO.setUserCollegeEmail("email@eamil.com");
        userDTO.setUserCollegeCertify("true");
        userDTO.setUserNeosPowerLevel(1);
        userDTO.setUserNeosPowerAbility(0);
        userDTO.setUserNeosBadge("1");
        userDTO.setUserNeosPoint(0);
        userDTO.setUserChattingPoint(1000);
        userDTO.setUserIntroduce("설정전");
        userDTO.setUserFile("기본");
        user.update(userDTO);
    }

    @Test
    public void deleteUserTest(){
        userRepository.deleteById(2l);
    }

    @Test
    public void findAllTesT(){
        userRepository.findAll().stream().map(User::toString).forEach(log::info);
    }

    @Test
    public void findAllTest2(){
        UserDTO userDTO = new UserDTO();
//        jpaQueryFactory.selectFrom(user).fetch().stream().map(User::toString).forEach(log::info);
        jpaQueryFactory.selectFrom(user).fetch().stream().map(User::toString).forEach(log::info);

    }

    @Test
    public void findByNameTest(){
        jpaQueryFactory.selectFrom(user).where(user.userNickName.eq("박종dd우")).orderBy(user.userId.desc()).fetch()
                .stream().map(User::toString).forEach(log::info);
    }

    @Test
    public void updateTest(){
        jpaQueryFactory.update(user).set(user.userNickName,"박종우").where(user.userId.eq(3l)).execute();
    }

    @Test
    public void deleteTest(){
        jpaQueryFactory.delete(user).where(user.userId.eq(5l)).execute();
    }

    @Test
    public void pageTest(){
        Pageable pageable = PageRequest.of(0,1);
        userCustomRepository.findAllPage(pageable).getContent().stream().map(User::toString).forEach(log::info);
    }

    @Test
    public void queryTesT(){
        Search search = new Search();
        search.setUserNickName("박종우");
        userCustomRepository.findAllSearch(search).stream().map(User::toString).forEach(log::info);
    }
    @Test
    public void queryTest(){
        Search search = new Search();
        search.setUserNickName("박종우");
        search.setUserOAuthId("22222");
        userCustomRepository.findAllSearch(search).stream().map(User::toString).forEach(log::info);
    }
    @Test
    public void queryTest3(){
        Search search = new Search();
        search.setUserOAuthId("22222");
        userCustomRepository.findAllSearch(search).stream().map(User::toString).forEach(log::info);
    }

    @Test
    public void queryTest4(){
        Search search = new Search();
        userCustomRepository.findAllSearch(search).stream().map(User::toString).forEach(log::info);
    }

    @Test
    public void findAllByOauthIdTest(){
        assertThat(userRepository.findAllByUserOAuthId("2546432919k").size()).isEqualTo(1);
    }

    @Test
    public void findByOauthIdTest(){
        log.info("들어옴"+userRepository.findByUserOAuthId("2546432919k").isPresent());
    }

    @Test
    public void findByOauthIdTest2(){
        log.info(userCustomRepository.findByOauthId("2546432919k").toString());
    }

    @Test
    public void loginTest(){
        log.info(loginService.login("2546432919k").toString());
    }

    @Test
    public void loginDeniedTest(){
        assertThat(loginService.login("sd")).isEqualTo(null);
    }

    @Test
    public void updateTest2(){
        userRepository.findByUserOAuthId("2546432919k").get().updateNeosPower(50);
    }

    @Test
    public void updateTest3(){
        User user = userRepository.findByUserOAuthId("2546432919k").get();
        user.updateNeosPower(user.getUserNeosPower().getUserNeosPowerAbility()+10);
        log.info(userCustomRepository.findByOauthId("2546432919k").toString());
    }

    @Test
    public void findByIdTest2(){
        log.info(userCustomRepository.findById(4l).toString());
    }

    @Test
    public void test(){
        List<UserDTO> userDTOList = neosUserCustomRepository.userTest();

        userDTOList.stream().map(UserDTO::toString).forEach(log::info);
    }
}

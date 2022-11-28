package com.app.neos.entity.user;

import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.college.College;
import com.app.neos.repository.college.CollegeRepository;
import com.app.neos.repository.user.*;
import com.app.neos.type.user.UserCollegeMajor;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static com.app.neos.entity.user.QUser.user;

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
    @Test
    public void collegeSave(){
        College college = College.builder().collegeCity("서울").collegeLogoFile("1").collegeEmailDomain("1").collegeName("서울대학교").build();
        collegeRepository.save(college);
    }


    @Test
    public void saveTest(){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserNickName("박종우");
        userDTO.setUserOAuthId("22222");
        userDTO.setUserOAuthEmail("oauthemail@emailasd.com");
        userDTO.setUserCollegeMajor(UserCollegeMajor.MATH);
        userDTO.setUserCollegeYear(4);
        userDTO.setUserCollegeEmail("email@eamil.com");
        userDTO.setUserCollegeCertify(true);
        userDTO.setUserNeosPowerLevel(1);
        userDTO.setUserNeosPowerAbility(0);
        userDTO.setUserNeosBadge("1");
        userDTO.setUserNeosPoint(0);
        userDTO.setUserChattingPoint(1000);
        userDTO.setUserIntroduce("설정전");
        userDTO.setUserFile("기본");


        userDTO.setCollege(collegeRepository.findById(1l).get());
        User user1 = userDTO.toEntity();
        user1.changeCollege(userDTO.getCollege());

        userRepository.save(user1);

    }

    @Test
    public void updateUserTest(){
        User user = userRepository.findById(2l).get();
        UserDTO userDTO = new UserDTO();
        userDTO.setUserNickName("박종dd우");
        userDTO.setUserOAuthId("22222");
        userDTO.setUserOAuthEmail("oauthemail@emailasd.com");
        userDTO.setUserCollegeMajor(UserCollegeMajor.MATH);
        userDTO.setUserCollegeYear(4);
        userDTO.setUserCollegeEmail("email@eamil.com");
        userDTO.setUserCollegeCertify(true);
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


}

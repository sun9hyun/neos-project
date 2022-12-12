package com.app.neos.service.neosUser;

import com.app.neos.domain.user.QUserDTO;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.college.QCollege;
import com.app.neos.entity.neos.QNeosPoint;
import com.app.neos.entity.neos.QNeosPower;
import com.app.neos.entity.user.QUser;
import com.app.neos.entity.user.User;
import com.app.neos.repository.user.UserCustomRepository;
import com.app.neos.repository.user.UserCustomRepositoryImpl;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.type.user.UserCollegeMajor;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class NeosUserServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    NeosUserService neosUserService;

    @Autowired
    UserCustomRepository userCustomRepository;

    @Autowired
    JPAQueryFactory jpaQueryFactory;



//    유저 등록 테스트
    @Test
    public void saveTest(){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserNickName("박종우");
        userDTO.setCollegeName("빛대학교");
        userDTO.setUserCollegeYear(3);
        userDTO.setUserCollegeMajor(UserCollegeMajor.공학계열);
        userDTO.setUserNeosBadge("4");
        userDTO.setUserNeosPowerAbility(200);
        userDTO.setUserNeosPoint(3000);
        userDTO.setUserNeosPowerLevel(4);
        userDTO.setUserChattingPoint(1000);

        User user = userDTO.toEntity();

        userRepository.save(user);

        UserDTO user1DTO = new UserDTO();
        user1DTO.setUserNickName("성은지대왕님");
        user1DTO.setCollegeName("마다가스카대학교");
        user1DTO.setUserCollegeYear(4);
        user1DTO.setUserCollegeMajor(UserCollegeMajor.의약계열);
        user1DTO.setUserNeosBadge("4");
        user1DTO.setUserNeosPowerAbility(200);
        user1DTO.setUserNeosPoint(3000);
        user1DTO.setUserNeosPowerLevel(4);
        user1DTO.setUserChattingPoint(1000);

        User user1 = user1DTO.toEntity();

        neosUserService.saveUser(user1);


    }

//    @Test
//    public void findByKeywordTest(){
//        UserDTO userDTO = new UserDTO();
//
//
//        neosUserService.findByKeyword("인천대학교");
//
//    }

}

package com.app.neos.service.study;


import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.study.QStudy;
import com.app.neos.entity.study.QStudyMember;
import com.app.neos.entity.study.Study;
import com.app.neos.entity.study.StudyMember;
import com.app.neos.entity.user.QUser;
import com.app.neos.entity.user.User;
import com.app.neos.repository.study.StudyRepository;
import com.app.neos.repository.user.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.app.neos.entity.study.QStudy.*;
import static com.app.neos.entity.study.QStudyMember.*;
import static com.app.neos.entity.user.QUser.*;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class UserSearchTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    JPAQueryFactory factory;
    @Autowired
    StudyRepository studyRepository;


    @Test
    public void findTest(){


        Pageable pageable = PageRequest.of(0,8);
        String keyword = "박";

        List<User> list = factory.selectFrom(user).where(user.userNickName.like("%"+keyword+"%")).orderBy(user.userId.desc()).offset(pageable.getOffset()).limit(pageable.getPageSize()+1).fetch();

        List<UserDTO> dtos = list.stream().map(User::toDTO).collect(Collectors.toList());

        ArrayList<UserDTO> content = (ArrayList<UserDTO>)dtos;

        boolean hasNext = false;
        if (content.size() > pageable.getPageSize()) {
            content.remove(pageable.getPageSize());
            hasNext = true;
        }

        Slice<UserDTO> users = new SliceImpl<>(content,pageable,hasNext);

        users.stream().map(UserDTO::toString).forEach(log::info);

    }

    @Test
    public void findTest3(){
        log.info(studyRepository.findById(11l).get().toDTO().toString());
    }

    @Test
    public void findTest4(){
        Long userId= 121l;

        List<Study> list = factory.selectFrom(study).join(studyMember).on(study.studyId.eq(studyMember.study.studyId)).where(studyMember.user.userId.eq(userId)).fetch();

        List<Study> list2 = factory.selectFrom(study).where(study.user.userId.eq(121l)).fetch();



        list.stream().map(i->i.getStudyTitle()).forEach(log::info);
        log.info("____________________________________________________");
        list2.stream().map(i->i.getStudyTitle()).forEach(log::info);


    }

    @Test
    public void findTest5(){
        Long userId= 121l;

        List<Study> list = factory.selectFrom(study).leftJoin(studyMember).on(study.studyId.eq(studyMember.study.studyId))
                .where(studyMember.user.userId.eq(userId).or(study.user.userId.eq(userId))).fetch();

        list.stream().map(i->i.getStudyTitle()).forEach(log::info);

    }
}

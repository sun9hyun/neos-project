package com.app.neos.repository.neos;

import com.app.neos.domain.study.QStudyDTO;
import com.app.neos.domain.study.StudyDTO;
import com.app.neos.domain.user.QUserDTO;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.college.QCollege;
import com.app.neos.entity.study.QStudy;
import com.app.neos.entity.study.Study;
import com.app.neos.entity.user.QUser;
import com.app.neos.entity.user.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.app.neos.entity.study.QStudy.*;
import static com.app.neos.entity.user.QUser.*;

@RequiredArgsConstructor
@Repository
public class NeosUserCustomRepositoryImpl implements NeosUserCustomRepository {


    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<UserDTO> findAll(){
        return jpaQueryFactory.select(new QUserDTO(user.userId , user.userNickName ,user.userOAuthId , user.userOAuthEmail , user.userCollegeEmail , user.userPhoneNumber, user.userCollegeCertify , user.userCollegeInfo.userCollegeYear , user.userCollegeInfo.userCollegeMajor , user.userNeosPower.userNeosBadge , user.userNeosPower.userNeosPowerLevel, user.userNeosPower.userNeosPowerAbility , user.userNeosPoint , user.userChattingPoint , user.userLike.userO2o , user.userLike.userCity , user.userLike.userDay , user.userLike.userTime , user.userMBTI.userMbtiName , user.userMBTI.userMbtiColor , user.userIntroduce , user.userFile , user.college.collegeId , user.college.collegeCity , user.college.collegeName , user.college.collegeLogoFile , user.college.collegeEmailDomain))
                .from(user).fetch();

    }

    @Override
    public List<Study> findByUserId(Long userId) {
        System.out.println("TEST Service 호출");
        System.out.println("service -> " + jpaQueryFactory.selectFrom(study).where(study.user.userId.eq(userId)).fetch());
        return jpaQueryFactory.selectFrom(study).where(study.user.userId.eq(userId)).fetch();
    }

    @Override
    public Slice<UserDTO> findAllPage(Pageable pageable) {

        List<UserDTO> userDTOList = jpaQueryFactory.select(new QUserDTO(
                user.userId,
                user.userNickName,
                user.userOAuthId,
                user.userOAuthEmail,
                user.userCollegeEmail,
                user.userPhoneNumber,
                user.userCollegeCertify,
                user.userCollegeInfo.userCollegeYear,
                user.userCollegeInfo.userCollegeMajor,
                user.userNeosPower.userNeosBadge,
                user.userNeosPower.userNeosPowerLevel,
                user.userNeosPower.userNeosPowerAbility,
                user.userNeosPoint,
                user.userChattingPoint,
                user.userLike.userO2o,
                user.userLike.userCity,
                user.userLike.userDay,
                user.userLike.userTime,
                user.userMBTI.userMbtiName,
                user.userMBTI.userMbtiColor,
                user.userIntroduce,
                user.userFile,
                user.college.collegeId,
                user.college.collegeCity,
                user.college.collegeName,
                user.college.collegeLogoFile,
                user.college.collegeEmailDomain

        ))
                .from(user)
                .orderBy(user.updatedDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        ArrayList<UserDTO> content = (ArrayList<UserDTO>)userDTOList;

        boolean hasNext = false;
        if (content.size() > pageable.getPageSize()){
            content.remove(pageable.getPageSize());
            hasNext=true;
        }
        return new SliceImpl<>(content , pageable , hasNext);
    }






//    @Override
//    public List<UserDTO> findByKeyword(String keyword) {
//        List<User> list = jpaQueryFactory.selectFrom(user).where(user.userNickName.eq(keyword).or(user.college.collegeName.eq(keyword))).orderBy(user.userId.desc()).fetch();
//        return list.stream().map(i->i.toDTO()).collect(Collectors.toList());
//    }

}

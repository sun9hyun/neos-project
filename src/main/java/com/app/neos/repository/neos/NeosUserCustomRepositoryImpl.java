package com.app.neos.repository.neos;

import com.app.neos.domain.user.QUserDTO;
import com.app.neos.domain.user.UserDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

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



}

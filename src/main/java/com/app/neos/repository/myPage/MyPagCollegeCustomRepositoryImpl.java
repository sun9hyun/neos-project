package com.app.neos.repository.myPage;

import com.app.neos.domain.college.CollegeDTO;
import com.app.neos.domain.college.QCollegeDTO;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.college.QCollege;
import com.app.neos.entity.user.QUser;
import com.app.neos.entity.user.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.app.neos.entity.college.QCollege.college;

@Repository
@RequiredArgsConstructor
public class MyPagCollegeCustomRepositoryImpl implements MyPageCollegeCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public CollegeDTO findCollege(String college) {
        return jpaQueryFactory.select(new QCollegeDTO(
                QCollege.college.collegeId,
                QCollege.college.collegeCity,
                QCollege.college.collegeName,
                QCollege.college.collegeLogoFile,
                QCollege.college.collegeEmailDomain
                ))
                .from(QCollege.college)
                .where(QCollege.college.collegeName.eq(college))
                .fetchOne();
    }
}

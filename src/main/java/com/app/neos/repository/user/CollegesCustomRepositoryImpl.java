package com.app.neos.repository.user;


import com.app.neos.domain.college.CollegeDTO;
import com.app.neos.domain.college.QCollegeDTO;
import com.app.neos.entity.college.QCollege;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.neos.entity.college.QCollege.*;

@RequiredArgsConstructor
@Repository
public class CollegesCustomRepositoryImpl implements CollegesCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<CollegeDTO> findAll() {
        return jpaQueryFactory.select(new QCollegeDTO(college.collegeId,college.collegeCity,college.collegeName,college.collegeLogoFile,college.collegeEmailDomain))
                .from(college).fetch();
    }

    @Override
    public List<CollegeDTO> findByCollegeCity(String collegeCity) {
        return jpaQueryFactory.select(new QCollegeDTO(college.collegeId,college.collegeCity,college.collegeName,college.collegeLogoFile,college.collegeEmailDomain))
                .from(college)
                .where(college.collegeCity.eq(collegeCity))
                .fetch();
    }

    @Override
    public CollegeDTO findByCollegeName(String collegeName) {
        return jpaQueryFactory.select(new QCollegeDTO(college.collegeId,college.collegeCity,college.collegeName,college.collegeLogoFile,college.collegeEmailDomain))
                .from(college)
                .where(college.collegeName.eq(collegeName))
                .fetchOne();
    }


}

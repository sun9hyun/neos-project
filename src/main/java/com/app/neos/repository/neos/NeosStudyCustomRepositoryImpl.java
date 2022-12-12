package com.app.neos.repository.neos;

import com.app.neos.domain.study.QStudyDTO;
import com.app.neos.domain.study.StudyDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.app.neos.entity.study.QStudy.study;


@RequiredArgsConstructor
@Repository
public class NeosStudyCustomRepositoryImpl implements NeosStudyCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public Slice<StudyDTO> findStudyAllPage(Pageable pageable) {

        List<StudyDTO> studyDTOS = jpaQueryFactory.select(new QStudyDTO(
                study.studyId,
                study.studyTitle,
                study.studyField.studyType,
                study.studyField.studyKeyword,
                study.studyOnlineWhether.studyO2o,
                study.studyOnlineWhether.studyCity,
                study.studySupport,
                study.studyRecruitStatus,
                study.studyStatus,
                study.studyContent,
                study.studyView,
                study.studyEndDate,
                study.user.college.collegeId,
                study.user.college.collegeLogoFile,
                study.user.college.collegeName,
                study.user.userId,
                study.user.userNickName,
                study.createdDate

        ))
                .from(study)
                .orderBy(study.updatedDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        ArrayList<StudyDTO> content = (ArrayList<StudyDTO>)studyDTOS;

        boolean hasNext = false;
        if (content.size() > pageable.getPageSize()){
            content.remove(pageable.getPageSize());
            hasNext=true;
        }


        return new SliceImpl<>(content , pageable , hasNext);
    }
}

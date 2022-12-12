package com.app.neos.service.study;


import com.app.neos.domain.study.StudyNewsDTO;
import com.app.neos.entity.study.QStudyNews;
import com.app.neos.entity.study.StudyNews;
import com.app.neos.repository.study.StudyNewsRepository;
import com.app.neos.repository.study.StudyRepository;
import com.app.neos.repository.user.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.app.neos.entity.study.QStudyNews.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudyNewsService {

    private final UserRepository userRepository;
    private final StudyRepository studyRepository;
    private final StudyNewsRepository studyNewsRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Transactional
    public Slice<StudyNewsDTO> showList(Long studyId, Pageable pageable){
        List<StudyNews> list = jpaQueryFactory.selectFrom(studyNews).where(studyNews.study.studyId.eq(studyId))
                .orderBy(studyNews.updatedDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()+1)
                .fetch();
        List<StudyNewsDTO> dtoList = list.stream().map(StudyNews::toDTO).collect(Collectors.toList());
        ArrayList<StudyNewsDTO> content =  (ArrayList<StudyNewsDTO>)dtoList;
        boolean hasNext = false;
        if(content.size() > pageable.getPageSize()){
            content.remove(pageable.getPageSize());
            hasNext=true;
        }
        return new SliceImpl<>(content,pageable,hasNext);
    }
}

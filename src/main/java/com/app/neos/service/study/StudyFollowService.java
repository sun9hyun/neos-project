package com.app.neos.service.study;

import com.app.neos.entity.follow.Follow;
import com.app.neos.entity.study.StudyFollow;
import com.app.neos.repository.study.StudyFollowCustomRepository;
import com.app.neos.repository.study.StudyFollowRepository;
import com.app.neos.repository.study.StudyRepository;
import com.app.neos.repository.user.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudyFollowService {
    private final JPAQueryFactory jpaQueryFactory;
    private final StudyFollowRepository studyFollowRepository;
    private final UserRepository userRepository;
    private final StudyRepository studyRepository;
    private final StudyFollowCustomRepository studyFollowCustomRepository;

    public boolean follow(Long myId, Long studyId){

        if(studyFollowCustomRepository.duplicate(myId,studyId)){
            cancel(myId,studyId);
        }else{
            StudyFollow studyFollow = StudyFollow.create();
            studyFollow.changeStudy(studyRepository.findById(studyId).get());
            studyFollow.changeUser(userRepository.findById(myId).get());
            studyFollowRepository.save(studyFollow);
        }
        return false;

    }

    public void cancel(Long myId, Long studyId){
        studyFollowRepository.delete(studyFollowCustomRepository.findByIdAndStudyId(myId,studyId));
    }

}

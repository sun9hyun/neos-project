package com.app.neos.service.study;

import com.app.neos.domain.neos.NeosPowerDTO;
import com.app.neos.entity.follow.Follow;
import com.app.neos.entity.neos.NeosPower;
import com.app.neos.entity.study.StudyFollow;
import com.app.neos.entity.user.User;
import com.app.neos.repository.neos.NeosPowerRepository;
import com.app.neos.repository.study.StudyFollowCustomRepository;
import com.app.neos.repository.study.StudyFollowRepository;
import com.app.neos.repository.study.StudyRepository;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.type.point.NeosPowerContent;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudyFollowService {
    private final JPAQueryFactory jpaQueryFactory;
    private final StudyFollowRepository studyFollowRepository;
    private final UserRepository userRepository;
    private final StudyRepository studyRepository;
    private final StudyFollowCustomRepository studyFollowCustomRepository;
    private final NeosPowerRepository neosPowerRepository;

    @Transactional
    public boolean follow(Long myId, Long studyId, Long userId){

        if(studyFollowCustomRepository.duplicate(myId,studyId)){
            User user = userRepository.findById(userId).get();
            user.updateNeosPower(user.getUserNeosPower().getUserNeosPowerAbility()-100);
            user.levelUp();
            NeosPowerDTO dto = new NeosPowerDTO();
            dto.setNeosPowerAbility(-100);
            dto.setNeosPowerContent(NeosPowerContent.STUDYFOLLOW);
            NeosPower entity = dto.toEntity();
            entity.changeUser(user);
            neosPowerRepository.save(entity);
            cancel(myId,studyId);
        }else{
            StudyFollow studyFollow = StudyFollow.create();
            studyFollow.changeStudy(studyRepository.findById(studyId).get());
            studyFollow.changeUser(userRepository.findById(myId).get());
            studyFollowRepository.save(studyFollow);

            User user = userRepository.findById(userId).get();
            user.updateNeosPower(user.getUserNeosPower().getUserNeosPowerAbility()+100);
            user.levelUp();

            NeosPowerDTO dto = new NeosPowerDTO();
            dto.setNeosPowerAbility(100);
            dto.setNeosPowerContent(NeosPowerContent.STUDYFOLLOW);
            NeosPower entity = dto.toEntity();
            entity.changeUser(user);
            neosPowerRepository.save(entity);
        }
        return false;

    }

    public void cancel(Long myId, Long studyId){
        studyFollowRepository.delete(studyFollowCustomRepository.findByIdAndStudyId(myId,studyId));
    }

}

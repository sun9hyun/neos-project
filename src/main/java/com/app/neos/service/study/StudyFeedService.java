package com.app.neos.service.study;

import com.app.neos.domain.study.StudyFeedDTO;
import com.app.neos.entity.study.StudyFeed;
import com.app.neos.repository.study.StudyFeedCustomRepository;
import com.app.neos.repository.study.StudyFeedRepository;
import com.app.neos.repository.study.StudyRepository;
import com.app.neos.repository.user.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudyFeedService {
    private final StudyFeedRepository studyFeedRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private final StudyRepository studyRepository;
    private final UserRepository userRepository;
    private final StudyFeedCustomRepository studyFeedCustomRepository;

    public void post(StudyFeedDTO studyFeedDTO){
        StudyFeed feed = studyFeedDTO.toEntity();
        feed.changeStudy(studyRepository.findById(studyFeedDTO.getStudy().getStudyId()).get());
        feed.changeStudyFeedWriter(userRepository.findById(studyFeedDTO.getStudyFeedWriter().getUserId()).get());
        studyFeedRepository.save(feed);
    }

    public List<StudyFeedDTO> showList(Long studyId){
        return studyFeedCustomRepository.findAllRecent(studyId).stream().map(StudyFeed::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public void update(StudyFeedDTO studyFeedDTO){
        StudyFeed feed = studyFeedRepository.findById(studyFeedDTO.getStudyFeedId()).get();
        feed.update(studyFeedDTO);
    }


    @Transactional
    public void remove(Long studyFeedId){
        studyFeedRepository.deleteById(studyFeedId);
    }

}

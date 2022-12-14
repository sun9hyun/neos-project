package com.app.neos.service.alarm;

import com.app.neos.aspect.annotation.AlarmInput;
import com.app.neos.domain.Alarm.AlarmDTO;
import com.app.neos.domain.study.*;
import com.app.neos.entity.alarm.Alarm;
import com.app.neos.repository.alarm.AlarmCustomRepository;
import com.app.neos.repository.alarm.AlarmRepository;
import com.app.neos.repository.study.StudyRepository;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.type.alarm.AlarmCategory;
import com.app.neos.type.alarm.ReadStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AlarmService {
    private final AlarmRepository repository;
    private final UserRepository userRepository;
    private final StudyRepository studyRepository;
    private final AlarmCustomRepository alarmCustomRepository;

    @AlarmInput
    public void alarm(Object obj, AlarmCategory category){
        log.info(obj.toString());
        log.info(category.toString());
    }
//     STUDYMEMBER, INQUIRY, FOLLOW, QUESTIONREPLY, FEEDREPLY, STUDYSUPPORT, COMMUNITYREPLY, COUNSELINGREPLY, SHOPREPLY
    public void alarmSend(Object obj, AlarmCategory category){
        switch (category){
            case STUDYMEMBER:
                memberPass(obj);
                break;

            case INQUIRY:
                break;

            case FOLLOW:
                break;

            case QUESTIONREPLY:
                questionAlarm(obj);
                break;

            case FEEDREPLY:
                feedAlarm(obj);
                break;

            case STUDYSUPPORT:
                studySupportAlarm(obj);
                break;
            case COMMUNITYREPLY:
                break;

            case COUNSELINGREPLY:
                break;

            case SHOPREPLY:
                break;

            case SUPPORTFAIL:
                supportFail(obj);
                break;

        }
    }

    @Transactional
    public void feedAlarm(Object obj){

        StudyFeedReplyDTO dto = (StudyFeedReplyDTO)obj;
       String studyName =  dto.getStudyFeed().getStudy().getStudyTitle();

       Long feedWriterUserId = dto.getStudyFeed().getStudyFeedWriter().getUserId();
       Long feedReplyWriterUserId = dto.getStudyFeedReplyWriter().getUserId();


       if(feedWriterUserId != feedReplyWriterUserId){
           AlarmDTO alarmDTO = new AlarmDTO();
           alarmDTO.setAlarmContent("[스터디] "+studyName+ " 게시글 피드에 댓글이 달렸습니다.");
           alarmDTO.setReadStatus(ReadStatus.NO);
           alarmDTO.setAlarmCategory(AlarmCategory.FEEDREPLY);
           alarmDTO.setContentId(dto.getStudyFeedReplyId());

           Alarm entity = alarmDTO.toEntity();
           entity.changeUser(userRepository.findById(feedWriterUserId).get());
           repository.save(entity);

       }

    }

    @Transactional
    public void questionAlarm(Object obj){
        StudyQuestionReplyDTO dto = (StudyQuestionReplyDTO)obj;

        String studyName = dto.getStudyQuestion().getStudyDTO().getStudyTitle();
        Long studyQuestionWriterId = dto.getStudyQuestion().getStudyQuestionWriter().getUserId();
        Long studyQuestionReplyWriterId = dto.getStudyQuestionReplyWriter().getUserId();

        if(studyQuestionWriterId != studyQuestionReplyWriterId){
            AlarmDTO alarmDTO = new AlarmDTO();
            alarmDTO.setAlarmContent("[스터디] "+studyName+ " 게시글 질문에 댓글이 달렸습니다.");
            alarmDTO.setReadStatus(ReadStatus.NO);
            alarmDTO.setAlarmCategory(AlarmCategory.QUESTIONREPLY);
            alarmDTO.setContentId(dto.getStudyQuestionReplyId());
            Alarm entity = alarmDTO.toEntity();
            entity.changeUser(userRepository.findById(studyQuestionWriterId).get());
            repository.save(entity);
        }


    }

    //지원자가 생기면 주인에게 알림이 가는 상황
    @Transactional
    public void studySupportAlarm(Object obj){
        StudySupporterDTO dto = (StudySupporterDTO)obj;
        StudyDTO studyDTO = studyRepository.findById(dto.getStudyId()).get().toDTO();
        String studyName = studyDTO.getStudyTitle();
        String supporterName = dto.getUser().getUserNickName();
        Long studyOwnerId = studyDTO.getUserId();
        Long supporterId = dto.getUser().getUserId();


        if(studyOwnerId != supporterId){
            AlarmDTO alarmDTO = new AlarmDTO();
            alarmDTO.setAlarmContent("[스터디] "+studyName+ "에 "+supporterName+"님이 지원하셨습니다.");
            alarmDTO.setReadStatus(ReadStatus.NO);
            alarmDTO.setAlarmCategory(AlarmCategory.STUDYSUPPORT);
            alarmDTO.setContentId(dto.getStudySupporterId());

            Alarm entity = alarmDTO.toEntity();
            entity.changeUser(userRepository.findById(studyOwnerId).get());
            repository.save(entity);

        }




    }

    //지원한 후 실패했을 시 실패했다고 해당 유저에게 알림이 간다.
    @Transactional
    public void supportFail(Object obj){
        StudySupporterDTO dto = (StudySupporterDTO)obj;
        StudyDTO studyDTO = studyRepository.findById(dto.getStudyId()).get().toDTO();
        String studyName = studyDTO.getStudyTitle();
        String supporterName = dto.getUser().getUserNickName();
        Long studyOwnerId = studyDTO.getUserId();
        Long supporterId = dto.getUser().getUserId();

        if(studyOwnerId != supporterId){
            AlarmDTO alarmDTO = new AlarmDTO();
            alarmDTO.setAlarmContent("[스터디] "+studyName+ "에 아쉽게도 함께 하실 수 없습니다.");
            alarmDTO.setReadStatus(ReadStatus.NO);
            alarmDTO.setAlarmCategory(AlarmCategory.SUPPORTFAIL);
            alarmDTO.setContentId(dto.getStudySupporterId());

            Alarm entity = alarmDTO.toEntity();
            entity.changeUser(userRepository.findById(supporterId).get());
            repository.save(entity);
        }


    }

    @Transactional
    public void memberPass(Object obj){
        StudyMemberDTO dto = (StudyMemberDTO) obj;
        StudyDTO studyDTO = studyRepository.findById(dto.getStudyId()).get().toDTO();
        String studyName = studyDTO.getStudyTitle();
        String memberName = dto.getUserDTO().getUserNickName();

        Long studyOwnerId = studyDTO.getUserId();
        Long memberId = dto.getUserDTO().getUserId();


        if(studyOwnerId != memberId){
            AlarmDTO alarmDTO = new AlarmDTO();
            alarmDTO.setAlarmContent("[스터디] "+studyName+ "에 가입되셨습니다.");
            alarmDTO.setReadStatus(ReadStatus.NO);
            alarmDTO.setAlarmCategory(AlarmCategory.STUDYMEMBER);
            alarmDTO.setContentId(dto.getStudyMemberId());

            Alarm entity = alarmDTO.toEntity();
            entity.changeUser(userRepository.findById(memberId).get());
            repository.save(entity);

        }
    }



    public List<AlarmDTO> showNoRead(Long userId){
        return alarmCustomRepository.findAllNoReadAlarmByUserId(userId).stream().map(Alarm::toDTO).collect(Collectors.toList());
    }

    public List<AlarmDTO> showAll(Long userId){
        return alarmCustomRepository.findAllAlarmByUserId(userId).stream().map(Alarm::toDTO).collect(Collectors.toList());
    }

    public AlarmDTO showNoReadRecent(Long userId){
        return alarmCustomRepository.findByUserIdNoRead(userId).get(0).toDTO();
    }

}

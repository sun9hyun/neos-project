package com.app.neos.service.alarm;

import com.app.neos.aspect.annotation.AlarmInput;
import com.app.neos.domain.Alarm.AlarmDTO;
import com.app.neos.domain.community.CommunityReplyDTO;
import com.app.neos.domain.counseling.CounselingReplyDTO;
import com.app.neos.domain.inquiry.InquiryDTO;
import com.app.neos.domain.store.StoreReplyDTO;
import com.app.neos.domain.study.*;
import com.app.neos.domain.user.FollowDTO;
import com.app.neos.entity.alarm.Alarm;
import com.app.neos.entity.inquiry.Inquiry;
import com.app.neos.entity.store.StoreReply;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AlarmService {
    private final AlarmRepository repository;
    private final UserRepository userRepository;
    private final StudyRepository studyRepository;
    private final AlarmCustomRepository alarmCustomRepository;
    private final AlarmRepository alarmRepository;


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
                inquiryAlarm(obj);
                break;

            case FOLLOW:
                followAlarm(obj);
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
                communityReplyAlarm(obj);
                break;

            case COUNSELINGREPLY:
                counselingReplyAlarm(obj);
                break;

            case SHOPREPLY:
                shopReplyAlarm(obj);
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
       Long studyNumber = dto.getStudyFeed().getStudy().getStudyId();

       Long feedWriterUserId = dto.getStudyFeed().getStudyFeedWriter().getUserId();
       Long feedReplyWriterUserId = dto.getStudyFeedReplyWriter().getUserId();


       if(feedWriterUserId != feedReplyWriterUserId){
           AlarmDTO alarmDTO = new AlarmDTO();
           alarmDTO.setAlarmContent("[스터디] "+studyName+ " 게시글 피드에 댓글이 달렸습니다.");
           alarmDTO.setReadStatus(ReadStatus.NO);
           alarmDTO.setAlarmCategory(AlarmCategory.FEEDREPLY);
           alarmDTO.setContentId(dto.getStudyFeedReplyId());
           alarmDTO.setUrl("/study/feed/"+studyNumber);

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
        Long studyNumber = dto.getStudyQuestion().getStudyDTO().getStudyId();

        if(studyQuestionWriterId != studyQuestionReplyWriterId){
            AlarmDTO alarmDTO = new AlarmDTO();
            alarmDTO.setAlarmContent("[스터디] "+studyName+ " 게시글 질문에 댓글이 달렸습니다.");
            alarmDTO.setReadStatus(ReadStatus.NO);
            alarmDTO.setAlarmCategory(AlarmCategory.QUESTIONREPLY);
            alarmDTO.setContentId(dto.getStudyQuestionReplyId());
            alarmDTO.setUrl("/study/question/"+studyNumber);

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
        Long studyNumber =dto.getStudyId();


        if(studyOwnerId != supporterId){
            AlarmDTO alarmDTO = new AlarmDTO();
            alarmDTO.setAlarmContent("[스터디] "+studyName+ "에 "+supporterName+"님이 지원하셨습니다.");
            alarmDTO.setReadStatus(ReadStatus.NO);
            alarmDTO.setAlarmCategory(AlarmCategory.STUDYSUPPORT);
            alarmDTO.setContentId(dto.getStudySupporterId());
            alarmDTO.setUrl("/study/list/"+studyNumber);
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
            alarmDTO.setUrl("no");

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
        Long studyNumber =dto.getStudyId();


        if(studyOwnerId != memberId){
            AlarmDTO alarmDTO = new AlarmDTO();
            alarmDTO.setAlarmContent("[스터디] "+studyName+ "에 가입되셨습니다.");
            alarmDTO.setReadStatus(ReadStatus.NO);
            alarmDTO.setAlarmCategory(AlarmCategory.STUDYMEMBER);
            alarmDTO.setContentId(dto.getStudyMemberId());
            alarmDTO.setUrl("/study/list/"+studyNumber);

            Alarm entity = alarmDTO.toEntity();
            entity.changeUser(userRepository.findById(memberId).get());
            repository.save(entity);

        }
    }


    @Transactional
    public void shopReplyAlarm(Object obj){
        StoreReply storeReply = (StoreReply)obj;
        String name = storeReply.getStore().getStoreTitle();

        Long storeId = storeReply.getStore().getStoreId();
        Long ownerId = storeReply.getStore().getUser().getUserId();
        Long replierId = storeReply.getUser().getUserId();

        if(ownerId != replierId){
            AlarmDTO alarmDTO = new AlarmDTO();
            alarmDTO.setAlarmContent("[자료상점] "+name+ "에 댓글이 달렸습니다.");
            alarmDTO.setReadStatus(ReadStatus.NO);
            alarmDTO.setAlarmCategory(AlarmCategory.SHOPREPLY);
            alarmDTO.setContentId(storeReply.getStoreReplyId());
            alarmDTO.setUrl("/store/store-detail?storeId="+storeId);

            Alarm entity = alarmDTO.toEntity();
            entity.changeUser(userRepository.findById(ownerId).get());
            repository.save(entity);
        }



    }

    @Transactional
    public void communityReplyAlarm(Object obj){
        CommunityReplyDTO dto = (CommunityReplyDTO)obj;

        Long ownerId = dto.getCommunity().getUser().getUserId();
        Long replierId = dto.getUserId();
        String name = dto.getCommunity().getCommunityTitle();

        if(ownerId != replierId){
            AlarmDTO alarmDTO = new AlarmDTO();
            alarmDTO.setAlarmContent("[커뮤니티] "+name+ "에 댓글이 달렸습니다.");
            alarmDTO.setReadStatus(ReadStatus.NO);
            alarmDTO.setAlarmCategory(AlarmCategory.COMMUNITYREPLY);
            alarmDTO.setContentId(dto.getCommunityReplyId());
            alarmDTO.setUrl("/community/community");

            Alarm entity = alarmDTO.toEntity();
            entity.changeUser(userRepository.findById(ownerId).get());
            repository.save(entity);
        }



    }

    @Transactional
    public void counselingReplyAlarm(Object obj){
        CounselingReplyDTO dto = (CounselingReplyDTO) obj;

        Long ownerId = dto.getCounseling().getUser().getUserId();
        Long replierId = dto.getUserId();
        String name = dto.getCounselingTitle();

        if(ownerId != replierId){
            AlarmDTO alarmDTO = new AlarmDTO();
            alarmDTO.setAlarmContent("[고민상담] "+name+ "에 댓글이 달렸습니다.");
            alarmDTO.setReadStatus(ReadStatus.NO);
            alarmDTO.setAlarmCategory(AlarmCategory.COUNSELINGREPLY);
            alarmDTO.setContentId(dto.getCounselingId());
            alarmDTO.setUrl("/community/counseling");

            Alarm entity = alarmDTO.toEntity();
            entity.changeUser(userRepository.findById(ownerId).get());
            repository.save(entity);
        }

    }

    @Transactional
    public void inquiryAlarm(Object obj){
       Inquiry inquiry = (Inquiry)obj;

        String name =inquiry.getUser().getUserNickName();
        Long ownerId = inquiry.getUser().getUserId();

        AlarmDTO alarmDTO = new AlarmDTO();
        alarmDTO.setAlarmContent("[문의] "+name+ "의 문의 글에 답변이 달렸습니다.");
        alarmDTO.setReadStatus(ReadStatus.NO);
        alarmDTO.setAlarmCategory(AlarmCategory.INQUIRY);
        alarmDTO.setContentId(inquiry.getInquiryId());
        alarmDTO.setUrl("/inquiry/list?userId="+ownerId);

        Alarm entity = alarmDTO.toEntity();
        entity.changeUser(userRepository.findById(ownerId).get());
        repository.save(entity);


    }

    @Transactional
    public void followAlarm(Object obj){
        FollowDTO dto = (FollowDTO)obj;
        Long my = dto.getMy().getUserId();
        Long following = dto.getFollowing().getUserId();
        String myName = dto.getMy().getUserNickName();


        if(my != following){
            AlarmDTO alarmDTO = new AlarmDTO();
            alarmDTO.setAlarmContent("[팔로우] "+myName+ "님이 팔로우 하셨습니다.");
            alarmDTO.setReadStatus(ReadStatus.NO);
            alarmDTO.setAlarmCategory(AlarmCategory.FOLLOW);
            alarmDTO.setContentId(dto.getFollowId());
            alarmDTO.setUrl("/my-page/my");
            Alarm entity = alarmDTO.toEntity();
            entity.changeUser(userRepository.findById(following).get());
            repository.save(entity);
        }
    }





    public List<AlarmDTO> showNoRead(Long userId){
        return alarmCustomRepository.findAllNoReadAlarmByUserId(userId).stream().map(Alarm::toDTO).collect(Collectors.toList());
    }

    public List<AlarmDTO> showAll(Long userId){
        return alarmCustomRepository.findAllAlarmByUserId(userId).stream().map(Alarm::toDTO).collect(Collectors.toList());
    }



    @Transactional
    public String read(Long alarmId){
        Optional<Alarm> entity = alarmRepository.findById(alarmId);
        if(entity.isPresent()){
            Alarm alarm = entity.get();
            alarm.updateStatus();
            return alarm.getUrl();
        }
        return "fail";
    }
}

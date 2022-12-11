package com.app.neos.repository.admin;

import com.app.neos.domain.community.CommunityDTO;
import com.app.neos.domain.community.CommunityReplyDTO;
import com.app.neos.domain.counseling.CounselingDTO;
import com.app.neos.domain.counseling.CounselingReplyDTO;
import com.app.neos.domain.store.StoreDTO;
import com.app.neos.domain.store.StoreReplyDTO;
import com.app.neos.domain.study.StudyDTO;
import com.app.neos.domain.study.StudyFeedReplyDTO;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.study.StudyFeedReply;
import com.app.neos.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminCustomRepository {

    public User findByUserId(Long userId);

    public UserDTO findByUserDTOId(Long userId);

//    public Page<UserDTO> findAllUserPage(Pageable pageable);



    public Page<StudyDTO> findAllStudyPage(Pageable pageable);

    public List<StudyDTO> findAllStudy();

    public Page<CommunityDTO> findAllCommunityPage(Pageable pageable);

    public List<CommunityDTO> findAllCommunity();

    public Page<CounselingDTO> findAllCounselingPage(Pageable pageable);

    public List<CounselingDTO> findAllCounseling();

    public Page<StoreDTO> findAllStorePage(Pageable pageable);

    public List<StoreDTO> findAllStore();



    public Page<StudyFeedReplyDTO> findAllStudyReplyPage(Pageable pageable);

    public List<StudyFeedReplyDTO> findAllStudyReply();

    public Page<CommunityReplyDTO> findAllCommunityReplyPage(Pageable pageable);

    public List<CommunityReplyDTO> findAllCommunityReply();

    public Page<CounselingReplyDTO> findAllCounselingReplyPage(Pageable pageable);

    public List<CounselingReplyDTO> findAllCounselingReply();

    public Page<StoreReplyDTO> findAllStoreReplyPage(Pageable pageable);

    public List<StoreReplyDTO> findAllStoreReply();


}

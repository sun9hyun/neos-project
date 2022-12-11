package com.app.neos.service.counseling;

import com.app.neos.domain.counseling.CounselingReplyDTO;
import com.app.neos.entity.counseling.Counseling;
import com.app.neos.entity.counseling.CounselingReply;
import com.app.neos.entity.user.User;
import com.app.neos.repository.counseling.CounselingReplyCustomRepository;
import com.app.neos.repository.counseling.CounselingReplyRepository;
import com.app.neos.repository.counseling.CounselingRepository;
import com.app.neos.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CounselingReplyService {
    public final CounselingReplyRepository counselingReplyRepository;
    public final CounselingReplyCustomRepository counselingReplyCustomRepository;
    public final CounselingRepository counselingRepository;
    public final UserRepository userRepository;

    public void saveCounselingReply(CounselingReplyDTO counselingReplyDTO){
        CounselingReply counselingReply = counselingReplyDTO.toEntity();
        Long userId = counselingReplyDTO.getUserId();
        User user = userRepository.findById(userId).get();
        Long counselingId = counselingReplyDTO.getCounselingId();
        Counseling counseling = counselingRepository.findById(counselingId).get();
        counselingReply.changeUser(user);
        counselingReply.changeCounseling(counseling);
        counselingReplyRepository.save(counselingReply);
    }

    public List<CounselingReplyDTO> findReplyAll(Long counselingId){
        return counselingReplyCustomRepository.findAll(counselingId);
    }

    @Transactional
    public void updateReply(CounselingReplyDTO counselingReplyDTO){
        CounselingReply counselingReply = counselingReplyRepository.findById(counselingReplyDTO.getCounselingId()).get();
        counselingReply.update(counselingReplyDTO);
    }

    public void deleteReply(Long counselingReplyId){
        counselingReplyRepository.deleteById(counselingReplyId);
    }

}

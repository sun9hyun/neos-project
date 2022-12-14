package com.app.neos.service.counseling;

import com.app.neos.domain.counseling.CounselingReplyDTO;
import com.app.neos.domain.neos.NeosPowerDTO;
import com.app.neos.entity.counseling.Counseling;
import com.app.neos.entity.counseling.CounselingReply;
import com.app.neos.entity.neos.NeosPower;
import com.app.neos.entity.user.User;
import com.app.neos.repository.counseling.CounselingReplyCustomRepository;
import com.app.neos.repository.counseling.CounselingReplyRepository;
import com.app.neos.repository.counseling.CounselingRepository;
import com.app.neos.repository.neos.NeosPowerRepository;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.type.point.NeosPowerContent;
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
    private final NeosPowerRepository neosPowerRepository;

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

    //네오력 상승
    @Transactional
    public void postEXP(Long userId){
        User user = userRepository.findById(userId).get();
        user.updateNeosPower(user.getUserNeosPower().getUserNeosPowerAbility()+10);
        user.levelUp();

        NeosPowerDTO dto = new NeosPowerDTO();
        dto.setNeosPowerAbility(10);
        dto.setNeosPowerContent(NeosPowerContent.REPLY);
        NeosPower entity = dto.toEntity();
        entity.changeUser(user);
        neosPowerRepository.save(entity);
    }

    //네오력 차감
    @Transactional
    public void postDeleteEXP(Long userId){
        User user = userRepository.findById(userId).get();
        user.updateNeosPower(user.getUserNeosPower().getUserNeosPowerAbility()-10);
        user.levelUp();

        NeosPowerDTO dto = new NeosPowerDTO();
        dto.setNeosPowerAbility(-10);
        dto.setNeosPowerContent(NeosPowerContent.REPLY);
        NeosPower entity = dto.toEntity();
        entity.changeUser(user);
        neosPowerRepository.save(entity);
    }
}

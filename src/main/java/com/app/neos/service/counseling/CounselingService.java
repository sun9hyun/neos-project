package com.app.neos.service.counseling;

import com.app.neos.domain.counseling.CounselingDTO;
import com.app.neos.domain.neos.NeosPowerDTO;
import com.app.neos.entity.counseling.Counseling;
import com.app.neos.entity.neos.NeosPower;
import com.app.neos.entity.user.User;
import com.app.neos.repository.counseling.CounselingCustomRepository;
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
public class CounselingService {
    private  final CounselingRepository counselingRepository;
    private  final CounselingCustomRepository counselingCustomRepository;
    private  final UserRepository userRepository;
    private final NeosPowerRepository neosPowerRepository;

    public void saveCounseling(CounselingDTO counselingDTO){
        Counseling counseling = counselingDTO.toEntity();
        Long userId = counselingDTO.getUserId();
        User user = userRepository.findById(userId).get();
        counseling.changeUser(user);
        counselingRepository.save(counseling);
    }

    public List<CounselingDTO> findAll(){
        return counselingCustomRepository.findAll();
    }

    @Transactional
    public void updateCounseling(CounselingDTO counselingDTO){
        Counseling counseling = counselingRepository.findById(counselingDTO.getCounselingId()).get();
        counseling.update(counselingDTO);
    }

    public void deleteCounseling(CounselingDTO counselingDTO){
        counselingRepository.deleteById(counselingDTO.getCounselingId());
    }

    //네오력 상승
    @Transactional
    public void postEXP(Long userId){
        User user = userRepository.findById(userId).get();
        user.updateNeosPower(user.getUserNeosPower().getUserNeosPowerAbility()+50);
        user.levelUp();

        NeosPowerDTO dto = new NeosPowerDTO();
        dto.setNeosPowerAbility(50);
        dto.setNeosPowerContent(NeosPowerContent.POST);
        NeosPower entity = dto.toEntity();
        entity.changeUser(user);
        neosPowerRepository.save(entity);
    }

    //네오력 차감
    @Transactional
    public void postDeleteEXP(Long userId){
        User user = userRepository.findById(userId).get();
        user.updateNeosPower(user.getUserNeosPower().getUserNeosPowerAbility()-100);
        user.levelUp();

        NeosPowerDTO dto = new NeosPowerDTO();
        dto.setNeosPowerAbility(-100);
        dto.setNeosPowerContent(NeosPowerContent.POST);
        NeosPower entity = dto.toEntity();
        entity.changeUser(user);
        neosPowerRepository.save(entity);
    }
}

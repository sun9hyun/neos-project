package com.app.neos.service.counseling;

import com.app.neos.domain.counseling.CounselingDTO;
import com.app.neos.entity.counseling.Counseling;
import com.app.neos.entity.user.User;
import com.app.neos.repository.counseling.CounselingCustomRepository;
import com.app.neos.repository.counseling.CounselingRepository;
import com.app.neos.repository.user.UserRepository;
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
}

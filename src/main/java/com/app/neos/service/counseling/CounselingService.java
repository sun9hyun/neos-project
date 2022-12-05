package com.app.neos.service.counseling;

import com.app.neos.domain.counseling.CounselingDTO;
import com.app.neos.entity.counseling.Counseling;
import com.app.neos.repository.counseling.CounselingCustomRepository;
import com.app.neos.repository.counseling.CounselingRepository;
import com.app.neos.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CounselingService {
    private  final CounselingRepository counselingRepository;
    private  final CounselingCustomRepository counselingCustomRepository;
    private  final UserRepository userRepository;

    public void saveCounseling(Counseling counseling){
        counselingRepository.save(counseling);
    }

    public List<CounselingDTO> findAll(){
        return counselingCustomRepository.findAll();
    }
}

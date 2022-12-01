package com.app.neos.service.counseling;

import com.app.neos.entity.counseling.Counseling;
import com.app.neos.repository.counseling.CounselingRepository;
import com.app.neos.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CounselingService {
    private  final CounselingRepository counselingRepository;
    private  final UserRepository userRepository;

    public void saveCounseling(Counseling counseling){
        counselingRepository.save(counseling);
    }
}

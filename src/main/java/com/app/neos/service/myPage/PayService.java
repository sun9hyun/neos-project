package com.app.neos.service.myPage;

import com.app.neos.domain.neos.NeosPointDTO;
import com.app.neos.domain.neos.NeosPowerDTO;
import com.app.neos.entity.neos.NeosPoint;
import com.app.neos.entity.neos.NeosPower;
import com.app.neos.entity.user.User;
import com.app.neos.repository.neos.NeosPointRepository;
import com.app.neos.repository.neos.NeosPowerRepository;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.type.point.NeosPowerContent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PayService {
//    private static final String Host = "https://kapi.kakao.com";
    private final NeosPointRepository neosPointRepository;
    private final UserRepository userRepository;
    private final NeosPowerRepository neosPowerRepository;

    //포인트 저장(결제)
    public void savePoint(NeosPointDTO neosPointDTO){
        NeosPoint neosPoint = neosPointDTO.toEntity();
        Long userId = neosPointDTO.getUserId();
        User user = userRepository.findById(userId).get();
        neosPoint.changeUser(user);
        neosPointRepository.save(neosPoint);
    }

    //user 포인트 저장
    @Transactional
    public void userPointUpdate(NeosPointDTO neosPointDTO){
        User user = userRepository.findById(neosPointDTO.getUserId()).get();
        user.updatePoint(neosPointDTO);
    }


//    네오력 상승
    @Transactional
    public void postEXP(Long userId, int pay){
        User user = userRepository.findById(userId).get();
        int payPoint = (pay/10);
        user.updateNeosPower(user.getUserNeosPower().getUserNeosPowerAbility()+payPoint);
        user.levelUp();

        NeosPowerDTO dto = new NeosPowerDTO();
        dto.setNeosPowerAbility(payPoint);
        dto.setNeosPowerContent(NeosPowerContent.POINT);
        NeosPower entity = dto.toEntity();
        entity.changeUser(user);
        neosPowerRepository.save(entity);
    }
}

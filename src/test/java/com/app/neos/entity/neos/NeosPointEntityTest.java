package com.app.neos.entity.neos;

import com.app.neos.domain.neos.NeosPointDTO;
import com.app.neos.repository.neos.NeosPointRepository;
import com.app.neos.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static com.app.neos.type.point.NeosPointContent.포인트충전;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class NeosPointEntityTest {

    @Autowired
    NeosPointRepository neosPointRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void neosPointSaveTest(){
        NeosPointDTO neosPointDTO = new NeosPointDTO();

        neosPointDTO.setNeosPointContent(포인트충전);
        neosPointDTO.setNeosPointMoney(1000);
        neosPointDTO.setUser(userRepository.findById(1l).get());

        NeosPoint neosPoint = neosPointDTO.toEntity();
        neosPoint.changeUser(neosPointDTO.getUser());
        neosPointRepository.save(neosPoint);

    }

    @Test
    public void deleteNeosPointTest(){
        neosPointRepository.deleteById(22l);
    }





}

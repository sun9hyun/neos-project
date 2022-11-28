package com.app.neos.entity.neos;

import com.app.neos.domain.neos.NeosPowerDTO;
import com.app.neos.repository.neos.NeosPowerRepository;
import com.app.neos.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static com.app.neos.type.point.NeosPowerContent.네오력상승;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class NeosPowerEntityTest {

    @Autowired
    NeosPowerRepository neosPowerRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void neosPowerSaveTest(){
        NeosPowerDTO neosPowerDTO = new NeosPowerDTO();

        neosPowerDTO.setNeosPowerContent(네오력상승);
        neosPowerDTO.setNeosPowerAbility(100);
        neosPowerDTO.setUser(userRepository.findById(2L).get());

        NeosPower neosPower = neosPowerDTO.toEntity();
        neosPower.changeUser(neosPowerDTO.getUser());
        neosPowerRepository.save(neosPower);

    }

    @Test
    public void deleteNeosPowerTest(){
        neosPowerRepository.deleteById(24l);
    }







}

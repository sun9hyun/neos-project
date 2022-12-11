package com.app.neos.entity.counseling;

import com.app.neos.domain.counseling.CounselingDTO;
import com.app.neos.repository.counseling.CounselingRepository;
import com.app.neos.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class CounselingEntityTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CounselingRepository counselingRepository;

    @Test
    public void saveTest(){
        for(int i = 0; i < 100; i++) {

            CounselingDTO counselingDTO = new CounselingDTO();
            counselingDTO.setCounselingTitle("고민 게시판");
            counselingDTO.setCounselingContent("고민 게시판 내용");

            counselingDTO.setUser(userRepository.findById(5L).get());

            Counseling counseling = counselingDTO.toEntity();
            counseling.changeUser(counselingDTO.getUser());

            counselingRepository.save(counseling);
        }
    }

    @Test
    public void updateTest(){
        Counseling counseling = counselingRepository.findById(3L).get();
        CounselingDTO counselingDTO = new CounselingDTO();
        counselingDTO.setCounselingTitle("고민 게시판 수정");
        counselingDTO.setCounselingContent("수정");
        counseling.update(counselingDTO);
    }

    @Test
    public void deleteTest(){
        counselingRepository.deleteById(3L);
    }

    @Test
    public void findAllTest(){
        counselingRepository.findAll().stream().map(Counseling::toString).forEach(log::info);
    }
}

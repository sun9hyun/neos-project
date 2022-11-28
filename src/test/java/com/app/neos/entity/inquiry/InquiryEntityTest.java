package com.app.neos.entity.inquiry;

import com.app.neos.domain.inquiry.InquiryDTO;
import com.app.neos.repository.inquiry.InquiryRepository;
import com.app.neos.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static com.app.neos.type.inquiry.InquiryStatus.COMPLETE;
import static com.app.neos.type.inquiry.InquiryStatus.WAITING;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class InquiryEntityTest {


    @Autowired
    InquiryRepository inquiryRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    public void inquirySaveTest(){
        InquiryDTO inquiryDTO = new InquiryDTO();
        inquiryDTO.setInquiryContent("사랑한다");
        inquiryDTO.setInquiryReply("나두사랑해");
        inquiryDTO.setInquiryStatus(COMPLETE);
        inquiryDTO.setUser(userRepository.findById(2L).get());
        Inquiry inquiry = inquiryDTO.toEntity();
        inquiry.changeUser(inquiryDTO.getUser());
        inquiryRepository.save(inquiry);
    }
    @Test
    public void deleteInquiryTest(){ inquiryRepository.deleteById(21l); }


}

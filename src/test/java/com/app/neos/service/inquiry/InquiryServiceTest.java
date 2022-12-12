package com.app.neos.service.inquiry;

import com.app.neos.domain.inquiry.InquiryDTO;
import com.app.neos.entity.inquiry.Inquiry;
import com.app.neos.entity.user.User;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.type.inquiry.InquiryStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class InquiryServiceTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    JPAQueryFactory jpaQueryFactory;
    @Autowired
    InquiryService inquiryService;

    @Test
    public void saveInquiryTest(){
        User user = userRepository.findById(141L).get();
        user.getUserId();

        InquiryDTO inquiryDTO = new InquiryDTO();
        inquiryDTO.setInquiryStatus(InquiryStatus.WAITING);
        inquiryDTO.setInquiryContent("문의를 할래요");
        inquiryDTO.setInquiryId(40L);
        inquiryDTO.setUser(user);

        Inquiry inquiry = inquiryDTO.toEntity();
        inquiry.changeUser(inquiryDTO.getUser());

        inquiryService.saveInquiry(inquiryDTO);
    }


    @Test
    public void deleteByInqiryIdTest(){
        inquiryService.remove(999L);
    }

}

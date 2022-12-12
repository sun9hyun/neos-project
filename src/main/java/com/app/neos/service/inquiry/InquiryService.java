package com.app.neos.service.inquiry;

import com.app.neos.domain.inquiry.InquiryDTO;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.inquiry.Inquiry;
import com.app.neos.entity.user.User;
import com.app.neos.repository.inquiry.InquiryCustomRepository;
import com.app.neos.repository.inquiry.InquiryRepository;
import com.app.neos.repository.neos.NeosUserCustomRepository;
import com.app.neos.repository.user.UserCustomRepository;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.type.inquiry.InquiryStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InquiryService {

    private final InquiryCustomRepository inquiryCustomRepository;
    private final InquiryRepository inquiryRepository;
    private final UserRepository userRepository;

    /*  각 각의 유저의 문의하기 리스트 */
    public List<InquiryDTO> findbyInquiryId(Long userId){
        return  inquiryCustomRepository.findById(userId);

    }

    /*삭제 */
    public void remove(Long InquiryId){
        inquiryRepository.deleteById(InquiryId);
    }

    /*문의 글 등록 */
    public void saveInquiry(InquiryDTO inquiryDTO, Long userId){
        inquiryDTO.setInquiryStatus(InquiryStatus.WAITING);
        Inquiry inquiry = inquiryDTO.toEntity();
        User user = userRepository.findById(userId).get();
        inquiry.changeUser(user);
        log.info(inquiry.toString());
        inquiryRepository.save(inquiry);

    }



}

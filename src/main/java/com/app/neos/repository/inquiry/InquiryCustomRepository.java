package com.app.neos.repository.inquiry;

import com.app.neos.domain.inquiry.InquiryDTO;
import com.app.neos.domain.user.UserDTO;

import java.util.List;

public interface InquiryCustomRepository {

    public List<InquiryDTO> findById(Long userId);




}

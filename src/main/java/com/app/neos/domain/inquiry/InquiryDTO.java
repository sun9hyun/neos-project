package com.app.neos.domain.inquiry;

import com.app.neos.entity.inquiry.Inquiry;
import com.app.neos.entity.user.User;
import com.app.neos.type.inquiry.InquiryStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class InquiryDTO {

    private Long inquiryId;
    private String inquiryContent;
    private String inquiryReply;
    private InquiryStatus inquiryStatus;
    private LocalDateTime createdDate;
    private User user;

    public Inquiry toEntity(){
        return Inquiry.builder()
                .inquiryContent(inquiryContent)
                .inquiryReply(inquiryReply)
                .inquiryStatus(inquiryStatus)
                .build();
    }

    @QueryProjection
    public InquiryDTO(Long inquiryId, String inquiryContent, String inquiryReply, InquiryStatus inquiryStatus,LocalDateTime createdDate ,User user) {
        this.inquiryId = inquiryId;
        this.inquiryContent = inquiryContent;
        this.inquiryReply = inquiryReply;
        this.inquiryStatus = inquiryStatus;
        this.createdDate = createdDate;
        this.user = user;
    }
}

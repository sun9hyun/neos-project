package com.app.neos.domain.inquiry;

import com.app.neos.entity.inquiry.Inquiry;
import com.app.neos.entity.user.User;
import com.app.neos.type.inquiry.InquiryStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private User user;

    private String userNickName;
    private String userFile;
    private Long userId;

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

    @QueryProjection
    public InquiryDTO(Long inquiryId, String inquiryContent, String inquiryReply, InquiryStatus inquiryStatus, String userNickName, LocalDateTime createdDate) {
        this.inquiryId = inquiryId;
        this.inquiryContent = inquiryContent;
        this.inquiryReply = inquiryReply;
        this.inquiryStatus = inquiryStatus;
        this.userNickName = userNickName;
        this.createdDate = createdDate;
    }

    @QueryProjection
    public InquiryDTO(Long inquiryId, String inquiryContent, String inquiryReply, InquiryStatus inquiryStatus, String userNickName, LocalDateTime createdDate, Long userId) {
        this.inquiryId = inquiryId;
        this.inquiryContent = inquiryContent;
        this.inquiryReply = inquiryReply;
        this.inquiryStatus = inquiryStatus;
        this.userNickName = userNickName;
        this.createdDate = createdDate;
        this.userId = userId;
    }

    @QueryProjection
    public InquiryDTO(Long inquiryId, String inquiryContent, String inquiryReply, InquiryStatus inquiryStatus, String userNickName, String userFile, LocalDateTime createdDate) {
        this.inquiryId = inquiryId;
        this.inquiryContent = inquiryContent;
        this.inquiryReply = inquiryReply;
        this.inquiryStatus = inquiryStatus;
        this.userNickName = userNickName;
        this.userFile = userFile;
        this.createdDate = createdDate;
    }

    @QueryProjection
    public InquiryDTO(Long inquiryId, String inquiryContent, String inquiryReply, InquiryStatus inquiryStatus,LocalDateTime createdDate ,User user, LocalDateTime updatedDate) {
        this.inquiryId = inquiryId;
        this.inquiryContent = inquiryContent;
        this.inquiryReply = inquiryReply;
        this.inquiryStatus = inquiryStatus;
        this.createdDate = createdDate;
        this.user = user;
        this.updatedDate = updatedDate;
    }

}

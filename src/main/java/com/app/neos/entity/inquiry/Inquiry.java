package com.app.neos.entity.inquiry;

import com.app.neos.domain.inquiry.InquiryDTO;
import com.app.neos.entity.period.Period;
import com.app.neos.entity.user.User;
import com.app.neos.type.inquiry.InquiryStatus;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name="TBL_INQUIRY")
@Getter @ToString(exclude = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Inquiry extends Period {
    @Id @GeneratedValue
    private Long inquiryId;

    @NotNull
    private String inquiryContent;
    private String inquiryReply;

    @Enumerated(EnumType.STRING)
    @NotNull
    private InquiryStatus inquiryStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public void changeUser(User user){
        this.user = user;
    }

    @Builder
    public Inquiry(@NotNull String inquiryContent, @NotNull String inquiryReply, @NotNull InquiryStatus inquiryStatus) {
        this.inquiryContent = inquiryContent;
        this.inquiryReply = inquiryReply;
        this.inquiryStatus = inquiryStatus;
    }

    public void update(InquiryDTO inquiryDTO){
        this.inquiryReply = inquiryDTO.getInquiryReply();
        this.inquiryStatus = inquiryDTO.getInquiryStatus();
    }

    public InquiryDTO toDto(){
        InquiryDTO dto = new InquiryDTO();
        dto.setInquiryId(inquiryId);
        dto.setInquiryContent(inquiryContent);
        dto.setInquiryReply(inquiryReply);
        dto.setInquiryStatus(inquiryStatus);
        dto.setUser(user);
        dto.setCreatedDate(getCreatedDate());
        dto.setUpdatedDate(getUpdatedDate());
        dto.setUserFile(user.getUserFile());
        dto.setUserNickName(user.getUserNickName());
        return dto;
    }
}

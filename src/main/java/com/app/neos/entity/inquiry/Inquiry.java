package com.app.neos.entity.inquiry;

import com.app.neos.domain.inquiry.InquiryDTO;
import com.app.neos.entity.period.Period;
import com.app.neos.entity.user.User;
import com.app.neos.type.inquiry.InquiryStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="TBL_INQUIRY")
@Getter @ToString(exclude = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Inquiry extends Period {
    @Id @GeneratedValue
    private Long inquiryId;

    @NonNull
    private String inquiryContent;
    @NonNull
    private String inquiryReply;

    @Enumerated(EnumType.STRING)
    @NonNull
    private InquiryStatus inquiryStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User user;

    public void changeUser(User user){
        this.user = user;
    }

    @Builder
    public Inquiry(@NonNull String inquiryContent, @NonNull String inquiryReply, @NonNull InquiryStatus inquiryStatus) {
        this.inquiryContent = inquiryContent;
        this.inquiryReply = inquiryReply;
        this.inquiryStatus = inquiryStatus;
    }

    public void update(InquiryDTO inquiryDTO){
        this.inquiryStatus = inquiryDTO.getInquiryStatus();
    }
}

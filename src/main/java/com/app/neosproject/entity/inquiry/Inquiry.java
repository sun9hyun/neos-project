package com.app.neosproject.entity.inquiry;

import com.app.neosproject.entity.period.Period;
import com.app.neosproject.entity.user.User;
import com.app.neosproject.type.inquiry.InquiryStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="TBL_INQUIRY")
@Getter @ToString(exclude = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Inquiry extends Period {
    @Id @GeneratedValue
    private Long inquiryId;

    private String inquiryContent;
    private String inquiryReply;
    private InquiryStatus inquiryStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User user;

    public void changeUser(User user){
        this.user = user;
    }

    @Builder
    public Inquiry(String inquiryContent, String inquiryReply, InquiryStatus inquiryStatus, User user) {
        this.inquiryContent = inquiryContent;
        this.inquiryReply = inquiryReply;
        this.inquiryStatus = inquiryStatus;
        this.user = user;
    }
}

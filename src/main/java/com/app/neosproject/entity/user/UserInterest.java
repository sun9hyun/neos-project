package com.app.neosproject.entity.user;

import com.app.neosproject.entity.period.Period;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="TBL_USER_INTEREST")
@Getter @ToString(exclude = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInterest extends Period {
    @Id @GeneratedValue
    private Long userInterestId;

    private String InterestField;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User user;

    public void changeUser(User user){
        this.user = user;
    }

    @Builder
    public UserInterest(String interestField, User user) {
        InterestField = interestField;
        this.user = user;
    }
}

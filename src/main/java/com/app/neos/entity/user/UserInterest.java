package com.app.neos.entity.user;

import com.app.neos.domain.user.UserInterestDTO;
import com.app.neos.entity.period.Period;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="TBL_USER_INTEREST")
@Getter @ToString(exclude = "user")
@NoArgsConstructor/*(access = AccessLevel.PROTECTED)*/
public class UserInterest extends Period {
    @Id @GeneratedValue @NonNull
    private Long userInterestId;
    @NonNull
    private String interestField;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User user;

    public void changeUser(User user){
        this.user = user;
    }

    @Builder
    public UserInterest(@NonNull String interestField) {
        this.interestField = interestField;
    }

    public void update(UserInterestDTO userInterestDTO){
        this.interestField = userInterestDTO.getInterestField();
    }

}

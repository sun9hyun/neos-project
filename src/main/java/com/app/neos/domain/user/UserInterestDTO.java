package com.app.neos.domain.user;

import com.app.neos.entity.user.User;
import com.app.neos.entity.user.UserInterest;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class UserInterestDTO {
    private Long userInterestId;
    private String interestField;
    private User user;

    public UserInterest toEntity(){
        return UserInterest.builder().interestField(interestField).build();
    }

    @QueryProjection
    public UserInterestDTO(Long userInterestId, String interestField, User user) {
        this.userInterestId = userInterestId;
        this.interestField = interestField;
        this.user = user;
    }
}

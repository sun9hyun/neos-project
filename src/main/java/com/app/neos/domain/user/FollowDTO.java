package com.app.neos.domain.user;

import com.app.neos.entity.follow.Follow;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class FollowDTO {
    private Long followId;
    private UserDTO my;
    private UserDTO following;

    public Follow toEntity(){
        return Follow.builder().build();
    }

}

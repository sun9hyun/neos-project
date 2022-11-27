package com.app.neos.repository.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class Search {
    private String userNickName;
    private String userOAuthId;
}

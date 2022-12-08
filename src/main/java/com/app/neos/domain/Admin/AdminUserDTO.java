package com.app.neos.domain.Admin;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class AdminUserDTO {

    private Long storeCount;
    private Long studyCount;
    private Long communityCount;
    private Long counselingCount;
    private Long replyCount;
    private Long inquiryCount;


}

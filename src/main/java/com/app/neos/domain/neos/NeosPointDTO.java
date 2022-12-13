package com.app.neos.domain.neos;

import com.app.neos.entity.neos.NeosPoint;
import com.app.neos.entity.user.User;
import com.app.neos.type.point.NeosPointContent;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@Data
@NoArgsConstructor
public class NeosPointDTO {
    private Long neosPointId;
    private Integer neosPointMoney;
    private NeosPointContent neosPointContent;
    private User user;
    private Long userId;

    public NeosPoint toEntity(){
        return NeosPoint.builder()
                .neosPointMoney(neosPointMoney)
                .neosPointContent(neosPointContent)
                .build();
    }

    @QueryProjection
    public NeosPointDTO(Long neosPointId, Integer neosPointMoney, NeosPointContent neosPointContent, User user) {
        this.neosPointId = neosPointId;
        this.neosPointMoney = neosPointMoney;
        this.neosPointContent = neosPointContent;
        this.user = user;
    }

    @QueryProjection
    public NeosPointDTO(Long neosPointId, Integer neosPointMoney, NeosPointContent neosPointContent, Long userId) {
        this.neosPointId = neosPointId;
        this.neosPointMoney = neosPointMoney;
        this.neosPointContent = neosPointContent;
        this.userId = userId;
    }
}

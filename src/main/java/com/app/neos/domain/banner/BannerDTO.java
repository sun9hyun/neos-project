package com.app.neos.domain.banner;

import com.app.neos.entity.banner.Banner;
import com.app.neos.type.banner.BannerStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class BannerDTO {

    private Long bannerId;
    private String bannerTitle;
    private String bannerUrl;
    private BannerStatus bannerStatus;

    public Banner toEntity(){
        return Banner.builder().
                bannerTitle(bannerTitle)
                .bannerUrl(bannerUrl)
                .bannerStatus(BannerStatus.게재중).
                build();

    }

    @QueryProjection
    public BannerDTO(Long bannerId, String bannerTitle, String bannerUrl, BannerStatus bannerStatus) {
        this.bannerId = bannerId;
        this.bannerTitle = bannerTitle;
        this.bannerUrl = bannerUrl;
        this.bannerStatus = bannerStatus;
    }
}

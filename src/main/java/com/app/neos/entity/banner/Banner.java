package com.app.neos.entity.banner;

import com.app.neos.domain.banner.BannerDTO;
import com.app.neos.entity.period.Period;
import com.app.neos.type.banner.BannerStatus;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="TBL_BANNER")
@Getter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Banner extends Period {
    @Id @GeneratedValue
    private Long bannerId;
    @NotNull
    private String bannerTitle;
    @NotNull
    private String bannerUrl;

   @Enumerated(EnumType.STRING) @NotNull
   private BannerStatus bannerStatus;

    @Builder
    public Banner(String bannerTitle, String bannerUrl, BannerStatus bannerStatus) {
        this.bannerTitle = bannerTitle;
        this.bannerUrl = bannerUrl;
        this.bannerStatus = bannerStatus;
    }

    public void update(BannerDTO bannerDTO){
        this.bannerTitle = bannerDTO.getBannerTitle();
        this.bannerUrl = bannerDTO.getBannerUrl();
        this.bannerStatus = bannerDTO.getBannerStatus();
    }


}

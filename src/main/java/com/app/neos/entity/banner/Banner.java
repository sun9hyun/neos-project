package com.app.neos.entity.banner;

import com.app.neos.domain.banner.BannerDTO;
import com.app.neos.entity.period.Period;
import com.app.neos.type.banner.BannerStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="TBL_BANNER")
@Getter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Banner extends Period {
    @Id @GeneratedValue @NonNull
    private Long bannerId;
    @NonNull
    private String bannerTitle;
    @NonNull
    private String bannerUrl;
    @NonNull
    private String fileName;

   @Enumerated(EnumType.STRING) @NonNull
   private BannerStatus bannerStatus;

    @Builder
    public Banner(String bannerTitle, String bannerUrl, String fileName, BannerStatus bannerStatus) {
        this.bannerTitle = bannerTitle;
        this.bannerUrl = bannerUrl;
        this.fileName = fileName;
        this.bannerStatus = bannerStatus;
    }

    public void update(BannerDTO bannerDTO){
        this.bannerTitle = bannerDTO.getBannerTitle();
        this.bannerUrl = bannerDTO.getBannerUrl();
        this.fileName = bannerDTO.getFileName();
        this.bannerStatus = bannerDTO.getBannerStatus();
    }


}

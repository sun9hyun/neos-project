package com.app.neosproject.entity.banner;

import com.app.neosproject.entity.period.Period;
import com.app.neosproject.type.banner.BannerStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="TBL_BANNER")
@Getter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Banner extends Period {
    @Id @GeneratedValue
    private Long bannerId;

   private String bannerTitle;
   private String bannerUrl;
   private String fileName;

   @Enumerated(EnumType.STRING)
   private BannerStatus bannerStatus;

    @Builder
    public Banner(String bannerTitle, String bannerUrl, String fileName, BannerStatus bannerStatus) {
        this.bannerTitle = bannerTitle;
        this.bannerUrl = bannerUrl;
        this.fileName = fileName;
        this.bannerStatus = bannerStatus;
    }
}

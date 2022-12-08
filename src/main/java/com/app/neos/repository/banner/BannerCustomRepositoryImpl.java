package com.app.neos.repository.banner;

import com.app.neos.domain.banner.BannerDTO;
import com.app.neos.domain.banner.QBannerDTO;
import com.app.neos.entity.banner.QBanner;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.neos.entity.banner.QBanner.banner;

@Repository
@RequiredArgsConstructor
public class BannerCustomRepositoryImpl implements BannerCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<BannerDTO> findAll() {
        return jpaQueryFactory.select(new QBannerDTO(
                banner.bannerId,
                banner.bannerTitle,
                banner.bannerUrl,
                banner.bannerStatus
        ))
                .from(banner)
                .orderBy(banner.bannerId.desc())
                .fetch();
    }

    @Override
    public BannerDTO findById(Long bannerId) {
        return jpaQueryFactory.select(new QBannerDTO(
                banner.bannerId,
                banner.bannerTitle,
                banner.bannerUrl,
                banner.bannerStatus
        ))
                .from(banner)
                .where(banner.bannerId.eq(bannerId))
                .fetchOne();
    }


}

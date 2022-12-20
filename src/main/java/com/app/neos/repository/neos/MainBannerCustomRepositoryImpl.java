package com.app.neos.repository.neos;

import com.app.neos.domain.banner.BannerDTO;
import com.app.neos.domain.banner.QBannerDTO;
import com.app.neos.domain.store.StoreDTO;
import com.app.neos.entity.banner.QBanner;
import com.app.neos.type.banner.BannerStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.app.neos.entity.banner.QBanner.banner;

@RequiredArgsConstructor
@Repository
public class MainBannerCustomRepositoryImpl implements MainBannerCustomRepository{


    private final JPAQueryFactory jpaQueryFactory;


//    @Override
//    public Slice<BannerDTO> findAllBannerpage(Pageable pageable) {
//
//        List<BannerDTO> bannerDTOS = jpaQueryFactory.select(new QBannerDTO(
//
//                banner.bannerId,
//                banner.bannerTitle,
//                banner.bannerUrl,
//                banner.bannerStatus
//
//        ))
//
//                .from(banner)
//                .orderBy(banner.updatedDate.desc())
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize() + 1)
//                .fetch();
//
//        ArrayList<BannerDTO> content = (ArrayList<BannerDTO>)bannerDTOS;
//
//        boolean hasNext = false;
//        if (content.size() > pageable.getPageSize()){
//            content.remove(pageable.getPageSize());
//            hasNext=true;
//        }
//        return new SliceImpl<>(content , pageable , hasNext);
//    }

    @Override
    public List<BannerDTO> findAllBanner() {
        return jpaQueryFactory.select(new QBannerDTO(
                banner.bannerId,
                banner.bannerTitle,
                banner.bannerUrl,
                banner.bannerStatus

        ))
                .from(banner)
                .where(banner.bannerStatus.eq(BannerStatus.개제중))
                .fetch();
    }
}

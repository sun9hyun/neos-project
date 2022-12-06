package com.app.neos.entity.banner;

import com.app.neos.domain.banner.BannerDTO;
import com.app.neos.repository.banner.BannerRepository;
import com.app.neos.type.banner.BannerStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class BannerEntityTest {
    @Autowired
    BannerRepository bannerRepository;

    @Test
    public void saveTest(){

        BannerDTO bannerDTO = new BannerDTO();
        bannerDTO.setBannerTitle("11월 이벤트 배너");
        bannerDTO.setBannerStatus(BannerStatus.게재중);
        bannerDTO.setBannerUrl("호근이 마음속");

        Banner banner = bannerDTO.toEntity();
        bannerRepository.save(banner);
    }


    @Test
    public void updateTest(){
        Banner banner = bannerRepository.findById(10L).get();

        BannerDTO bannerDTO = new BannerDTO();
        bannerDTO.setBannerTitle("12월 이벤트 배너");
        bannerDTO.setBannerStatus(BannerStatus.기간만료);
        bannerDTO.setBannerUrl("호근이 마음속");

        banner.update(bannerDTO);
    }

    @Test
    public void findTest(){
        bannerRepository.findAll().stream().map(Banner::toString).forEach(log::info);
    }


    @Test
    public void deleteTest(){
        bannerRepository.deleteAll();
    }

}

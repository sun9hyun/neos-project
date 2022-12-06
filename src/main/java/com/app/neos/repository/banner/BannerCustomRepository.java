package com.app.neos.repository.banner;

import com.app.neos.domain.banner.BannerDTO;

import java.util.List;

public interface BannerCustomRepository {

    public List<BannerDTO> findAll();

}

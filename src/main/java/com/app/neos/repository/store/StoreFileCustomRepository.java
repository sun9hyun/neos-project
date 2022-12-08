package com.app.neos.repository.store;

import com.app.neos.domain.store.StoreFlieDTO;
import com.app.neos.entity.store.StoreFile;

import java.util.List;

public interface StoreFileCustomRepository {

    public List<StoreFlieDTO> findByStoreId(Long storeId);

    public void deleteByStoreId(Long storeId);
}

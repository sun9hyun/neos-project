package com.app.neos.repository.store;
import com.app.neos.domain.study.StudySupporterDTO;
import com.app.neos.entity.store.StorePurchase;

import java.util.List;

public interface StorePurchaseCustomRepository {

    public boolean duplicate(Long userId, Long storeId);

    public StorePurchase findByIdAndStoreId(Long userId, Long storeId);

}

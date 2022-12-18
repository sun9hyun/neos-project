package com.app.neos.repository.neos;

import com.app.neos.domain.store.StoreDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface MainStoreCustomRepository {

    public Slice<StoreDTO> findAllPage(Pageable pageable);

//    public List<StoreDTO> findStoreForMain();
}

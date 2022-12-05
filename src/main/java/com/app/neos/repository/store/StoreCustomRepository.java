package com.app.neos.repository.store;

import com.app.neos.domain.store.StoreDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StoreCustomRepository {

    public List<StoreDTO> findAll();

    public Page<StoreDTO> findAllPage(Pageable pageable);

}

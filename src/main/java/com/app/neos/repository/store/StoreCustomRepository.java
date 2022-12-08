package com.app.neos.repository.store;

import com.app.neos.domain.store.StoreDTO;
import com.app.neos.entity.store.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StoreCustomRepository {

    // 자료상점 게시글 수정
    public void update(Store store);

    // 자료상점 게시글 삭제
    public void delete(Long storeId);

    // 자료상점 게시글 조회
    public StoreDTO selectOne(Long storeId);

    public List<StoreDTO> findAll();

    public Page<StoreDTO> findAllPage(Pageable pageable);

}

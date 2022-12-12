package com.app.neos.repository.store;

import com.app.neos.domain.store.StoreDTO;
import com.app.neos.entity.store.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface StoreCustomRepository {

    // 자료상점 게시글 수정
    public void update(Store store);

    // 자료상점 게시글 삭제
    public void delete(Long storeId);

    // 자료상점 게시글 조회
    public StoreDTO selectOne(Long storeId);

    // 자료상점 게시글 조회_유저ID
    public List<StoreDTO> findByUserId(Long userId);

    // 자료상점 게시글 전체 조회
    public List<StoreDTO> findAll();

    // 자료상점 게시글 페이징 처리
    public Page<StoreDTO> findAllPage(Pageable pageable);
    
    // 자료상점 게시글 슬라이스 페이징 처리
    public Slice<StoreDTO> findAllPageBySlice(Pageable pageable, Long userId);

}

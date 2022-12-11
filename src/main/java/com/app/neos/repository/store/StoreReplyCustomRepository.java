package com.app.neos.repository.store;

import com.app.neos.domain.store.StoreReplyDTO;
import com.app.neos.entity.store.StoreReply;

import java.util.List;

public interface StoreReplyCustomRepository {

    // 자료상점 전체 댓글 조회
    public List<StoreReplyDTO> findAllReply(Long storeId);

    // 자료상점 댓글 개별 조회
    public StoreReplyDTO findOneReply(Long storeReplyId);

    // 자료상점 댓글 수정
    public void update(StoreReply storeReply);

    // 자료상점 전체 댓글 카운팅
    public int findReplyCount(Long storeId);

    // 자료상점 기준 댓글 삭제
    public void deleteByStoreId(Long storeId);

}

package com.app.neos.service.store;

import com.app.neos.domain.store.StoreFlieDTO;
import com.app.neos.entity.store.Store;
import com.app.neos.entity.store.StoreFile;
import com.app.neos.repository.store.StoreFileCustomRepository;
import com.app.neos.repository.store.StoreFileRepository;
import com.app.neos.repository.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreFileService {
    private final StoreRepository storeRepository;
    private final StoreFileRepository storeFileRepository;
    private final StoreFileCustomRepository storeFileCustomRepository;

    // 첨부파일 추가
    public void saveStoreFile(StoreFlieDTO storeFlieDTO){
        StoreFile storeFile = storeFlieDTO.toEntity();
        Store store =storeRepository.findById(storeFlieDTO.getStoreId()).get();
        storeFile.changeStore(store);
        storeFileRepository.save(storeFile);
    }

    // 첨부파일 조회
    public List<StoreFlieDTO> findStoreFile(Long storeId) {
        return storeFileCustomRepository.findByStoreId(storeId);
    }

    // 첨부파일 삭제
    public void deleteStoreFile(Long storeId){
        storeFileCustomRepository.deleteByStoreId(storeId);
    }

}
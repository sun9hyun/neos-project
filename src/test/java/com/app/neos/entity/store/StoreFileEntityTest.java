package com.app.neos.entity.store;


import com.app.neos.domain.store.StoreFlieDTO;
import com.app.neos.repository.store.StoreFileCustomRepository;
import com.app.neos.repository.store.StoreFileRepository;
import com.app.neos.repository.store.StoreRepository;
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
public class StoreFileEntityTest {
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    StoreFileRepository storeFileRepository;
    @Autowired
    StoreFileCustomRepository storeFileCustomRepository;

    @Test
    public void saveTest(){
        StoreFlieDTO storeFlieDTO = new StoreFlieDTO();
        storeFlieDTO.setStoreFileName("222Test.doc");
        storeFlieDTO.setStoreFilePath("c:test/test/Test.doc");
        storeFlieDTO.setStoreFileUuid("22Test.doc");
        storeFlieDTO.setStoreFileSize(50L);
        storeFlieDTO.setStoreFileQR("22TestDoc.img");

//        storeFlieDTO.setStoreId(22L);
        storeFlieDTO.setStore(storeRepository.findById(22L).get());
        StoreFile storeFile = storeFlieDTO.toEntity();
//        storeFile.changeStore(storeRepository.findById(storeFlieDTO.getStoreId()).get());
        storeFile.changeStore(storeFlieDTO.getStore());

        storeFileRepository.save(storeFile);
    }

    @Test
    public void deleteTest(){ storeFileRepository.deleteById(8L);}

    @Test
    public void findByStoreIdTest(){
        storeFileCustomRepository.findByStoreId(22L).stream().map(StoreFlieDTO::toString).forEach(log::info);
    }

    @Test
    public void deleteByStoreIdTest(){
        storeFileCustomRepository.deleteByStoreId(22L);
    }
}

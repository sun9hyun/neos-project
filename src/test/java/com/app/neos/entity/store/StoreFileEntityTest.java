package com.app.neos.entity.store;


import com.app.neos.domain.store.StoreFlieDTO;
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

    @Test
    public void saveTest(){
        StoreFlieDTO storeFlieDTO = new StoreFlieDTO();
        storeFlieDTO.setStoreFileName("Test.doc");
        storeFlieDTO.setStoreFilePath("c:test/test/Test.doc");
        storeFlieDTO.setStoreFileQR("TestDoc.img");

        storeFlieDTO.setStore(storeRepository.findById(6L).get());
        StoreFile storeFile = storeFlieDTO.toEntity();
        storeFile.changeStore(storeFlieDTO.getStore());

        storeFileRepository.save(storeFile);
    }

    @Test
    public void deleteTest(){ storeFileRepository.deleteById(8L);}
}

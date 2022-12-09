package com.app.neos.service.store;

import com.app.neos.domain.store.StoreDTO;
import com.app.neos.domain.store.StoreFlieDTO;
import com.app.neos.entity.store.Store;
import com.app.neos.entity.store.StoreFile;
import com.app.neos.entity.user.User;
import com.app.neos.repository.store.StoreCustomRepository;
import com.app.neos.repository.store.StoreFileCustomRepository;
import com.app.neos.repository.store.StoreFileRepository;
import com.app.neos.repository.store.StoreRepository;
import com.app.neos.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final StoreCustomRepository storeCustomRepository;
    private final UserRepository userRepository;
    private final StoreFileRepository storeFileRepository;
    private final StoreFileCustomRepository storeFileCustomRepository;

    // 자료상점 게시글 작성
//    public void saveStore(Store store) { storeRepository.save(store);}
    @Transactional(rollbackFor = Exception.class)
    public void saveStore(StoreDTO storeDTO) {

        System.out.println("*********************  storeDTO: " + storeDTO + "*********************");


        List<StoreFlieDTO> storeFlieDTOS = storeDTO.getStoreFlieDTOS();

        System.out.println("*********************  storeFlieDTOS: " + storeFlieDTOS + "*********************");


        Store store = storeDTO.toEntity();

        User user = userRepository.findById(storeDTO.getUserId()).get();
        store.changeUser(user);

        List<StoreFile> files = new ArrayList<>();

        if(storeFlieDTOS != null) {
            storeFlieDTOS.forEach(storeFlieDTO -> {
                StoreFile storeFile = storeFlieDTO.toEntity();
                files.add(storeFile);
            });
        }

        store.changeFiles(files);
        System.out.println("*********************  files: " + files + "*********************");

        storeRepository.save(store);
        System.out.println("*********************  store: " + store + "*********************");

        if(storeFlieDTOS != null ) {
            storeFlieDTOS.forEach(storeFlieDTO -> {
                StoreFile storeFile = storeFlieDTO.toEntity();

                Store store1 = storeRepository.findById(store.getStoreId()).get();
                storeFile.changeStore(store1);

                storeFileRepository.save(storeFile);
            });
        }
     }

     // 자료상점 게시글 수정
     @Transactional(rollbackFor = Exception.class)
     public void updateStore(StoreDTO storeDTO) {


         List<StoreFlieDTO> storeFlieDTOS = storeDTO.getStoreFlieDTOS();
         System.out.println("*********************  1: " + storeDTO + "*********************");
         System.out.println("*********************  1: " + storeDTO.getStoreId() + "*********************");

         storeFileCustomRepository.deleteByStoreId(storeDTO.getStoreId());

         updateEntity(storeDTO);
         Store store = storeRepository.findById(storeDTO.getStoreId()).get();
         System.out.println("*********************  2: " + store + "*********************");

//         User user = userRepository.findById(storeDTO.getUserId()).get();
//         store.changeUser(user);

         List<StoreFile> updateFiles = new ArrayList<>();

         if (storeFlieDTOS != null) {
             storeFlieDTOS.forEach(storeFlieDTO -> {
                 StoreFile storeFile = storeFlieDTO.toEntity();
                 updateFiles.add(storeFile);
             });
         }
         store.changeFiles(updateFiles);

         System.out.println("*********************  2: " + store + "*********************");


         if (storeFlieDTOS != null) {
             storeFlieDTOS.forEach(storeFlieDTO -> {
                 StoreFile storeFile = storeFlieDTO.toEntity();

                 Store store1 = storeRepository.findById(storeDTO.getStoreId()).get();
                 storeFile.changeStore(store1);

                 storeFileRepository.save(storeFile);
             });

             System.out.println("*********************  3: " + store + "*********************");


             storeCustomRepository.update(store);
         }
     }

     // 자료상점 게시글 삭제
     @Transactional(rollbackFor = Exception.class)
     public void deleteByStoreId(Long storeId){
        storeFileCustomRepository.deleteByStoreId(storeId);
        storeCustomRepository.delete(storeId);
    }

    @Transactional
    public void updateEntity(StoreDTO storeDTO){
        Store store = storeRepository.findById(storeDTO.getStoreId()).get();
        store.update(storeDTO);
    }

     // 자료상점 게시글 조회
    public StoreDTO findStoreOne(Long storeId){
        StoreDTO storeDTO = storeCustomRepository.selectOne(storeId);
        storeDTO.setStoreFlieDTOS(storeFileCustomRepository.findByStoreId(storeId));

        return storeDTO;}

    // 스토어 글 목록
    public List<StoreDTO> findStore() { return storeCustomRepository.findAll();}






//    // 첨부파일 등록
//    public void saveStoreFile(StoreFlieDTO storeFlieDTO){
//        StoreFile storeFile = storeFlieDTO.toEntity();
//        Store store =storeRepository.findById(storeFlieDTO.getStoreId()).get();
//        storeFile.changeStore(store);
//        storeFileRepository.save(storeFile);
//    }
//
//    // 첨부파일 조회
//    public List<StoreFlieDTO> findStoreFile(Long storeId) {
//        return storeFileCustomRepository.findByStoreId(storeId);
//    }
//
//    // 첨부파일 삭제
//    public void deleteStoreFile(Long storeId){
//        storeFileCustomRepository.deleteByStoreId(storeId);
//    }

}

package com.app.neos.service.store;

import com.app.neos.domain.store.StoreDTO;
import com.app.neos.entity.store.Store;
import com.app.neos.entity.user.User;
import com.app.neos.repository.store.StoreCustomRepository;
import com.app.neos.repository.store.StoreFileRepository;
import com.app.neos.repository.store.StoreRepository;
import com.app.neos.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final StoreCustomRepository storeCustomRepository;
    private final UserRepository userRepository;
    private final StoreFileRepository storeFileRepository;

    // 스토어 글 작성
//    public void saveStore(Store store) { storeRepository.save(store);}
    public void saveStore(StoreDTO storeDTO) {

        Store store = storeDTO.toEntity();

        Long userId = storeDTO.getUserId();

        User user = userRepository.findById(userId).get();

        store.changeUser(user);

        storeRepository.save(store);}

    // 스토어 글 목록
    public List<StoreDTO> findStore() { return storeCustomRepository.findAll();}
}

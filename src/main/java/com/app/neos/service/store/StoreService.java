package com.app.neos.service.store;

import com.app.neos.entity.store.Store;
import com.app.neos.repository.store.StoreRepository;
import com.app.neos.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    // 스토어 글 작성
    public void saveStore(Store store) { storeRepository.save(store);}
}

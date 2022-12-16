package com.app.neos.service.store;

import com.app.neos.domain.neos.NeosPointDTO;
import com.app.neos.entity.neos.NeosPoint;
import com.app.neos.entity.store.Store;
import com.app.neos.entity.store.StorePurchase;
import com.app.neos.entity.user.User;
import com.app.neos.repository.neos.NeosPointRepository;
import com.app.neos.repository.store.StorePurchaseCustomRepository;
import com.app.neos.repository.store.StorePurchaseRepository;
import com.app.neos.repository.store.StoreRepository;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.type.point.NeosPointContent;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class StorePurchaseService {
    private final JPAQueryFactory jpaQueryFactory;
    private final StorePurchaseRepository storePurchaseRepository;
    private final StorePurchaseCustomRepository storePurchaseCustomRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final NeosPointRepository neosPointRepository;

    @Transactional
    public boolean checkPurchase(Long userId, Long storeId){
        return storePurchaseCustomRepository.duplicate(userId, storeId);
        // 중복이 있으면(결제 O) ture, 중복이 없으면(결제 X) false
    }

    @Transactional
    public boolean purchase(Long userId, Long storeId){
        if(!storePurchaseCustomRepository.duplicate(userId,storeId)){
            StorePurchase storePurchase = StorePurchase.create();
            storePurchase.changeStore(storeRepository.findById(storeId).get());
            storePurchase.changeUser(userRepository.findById(userId).get());
            storePurchaseRepository.save(storePurchase);

            int neoPoint = storeRepository.findById(storeId).get().getStorePoint();

            // 자료 구매자
            User user = userRepository.findById(userId).get();
            user.updateNeosPoint(user.getUserNeosPoint() - neoPoint);

            NeosPointDTO neosPointDTO = new NeosPointDTO();
            neosPointDTO.setNeosPointMoney(neoPoint*(-1));
            neosPointDTO.setNeosPointContent(NeosPointContent.자료구매);
            NeosPoint neosPoint = neosPointDTO.toEntity();
            neosPoint.changeUser(user);
            neosPointRepository.save(neosPoint);
            
            // 자료 판매자
            User seller = userRepository.findById(storeRepository.findById(storeId).get().getUser().getUserId()).get();
            seller.updateNeosPoint(seller.getUserNeosPoint() + neoPoint);

            NeosPointDTO neosPointDTO2 = new NeosPointDTO();
            neosPointDTO2.setNeosPointMoney(neoPoint);
            neosPointDTO2.setNeosPointContent(NeosPointContent.자료판매);
            NeosPoint neosPoint2 = neosPointDTO2.toEntity();
            neosPoint2.changeUser(seller);
            neosPointRepository.save(neosPoint2);
        }
        return false;
    }
}

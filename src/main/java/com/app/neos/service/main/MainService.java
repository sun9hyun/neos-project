package com.app.neos.service.main;

import com.app.neos.domain.banner.BannerDTO;
import com.app.neos.domain.community.CommunityDTO;
import com.app.neos.domain.store.StoreDTO;
import com.app.neos.domain.study.StudyDTO;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.user.User;
import com.app.neos.repository.community.CommunityCustomRepository;
import com.app.neos.repository.neos.MainBannerCustomRepository;
import com.app.neos.repository.neos.MainStoreCustomRepository;
import com.app.neos.repository.neos.NeosStudyCustomRepository;
import com.app.neos.repository.neos.NeosUserCustomRepository;
import com.app.neos.repository.study.StudyCustomRepository;
import com.app.neos.repository.user.UserCustomRepository;
import com.app.neos.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MainService {

    private final UserRepository userRepository;
    private final UserCustomRepository userCustomRepository;
    private final NeosUserCustomRepository neosUserCustomRepository;
    private final StudyCustomRepository studyCustomRepository;
    private final NeosStudyCustomRepository neosStudyCustomRepository;
    private final MainStoreCustomRepository mainStoreCustomRepository;
    private final CommunityCustomRepository communityCustomRepository;
    private final MainBannerCustomRepository mainBannerCustomRepository;

    public void saveUser(User user) { userRepository.save(user);}



//    유저 불러오기
    public Slice<UserDTO> findUserPage(Pageable pageable){
        return neosUserCustomRepository.findAllPage(pageable);
    }

//    스터디 불러오기
    public Slice<StudyDTO> findStudyPage(Pageable pageable){
        
        return neosStudyCustomRepository.findStudyAllPage(pageable);

    }

//    상점 불러오기
    public Slice<StoreDTO> findStorePage(Pageable pageable){
        return mainStoreCustomRepository.findAllPage(pageable);
    }
//    커뮤니티 불러오기
    public Slice<CommunityDTO> findCommunityPage(Pageable pageable){
        return communityCustomRepository.findAllPage(pageable);
    }


//  배너 불러오기
    public Slice<BannerDTO> findBannerPage(Pageable pageable){
        return mainBannerCustomRepository.findAllBannerpage(pageable);
    }

    }

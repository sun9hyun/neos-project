package com.app.neos.service.main;

import com.app.neos.domain.banner.BannerDTO;
import com.app.neos.domain.community.CommunityDTO;
import com.app.neos.domain.store.StoreDTO;
import com.app.neos.domain.study.StudyDTO;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.study.Study;
import com.app.neos.entity.user.User;
import com.app.neos.repository.community.CommunityCustomRepository;
import com.app.neos.repository.neos.*;
import com.app.neos.repository.study.StudyCustomRepository;
import com.app.neos.repository.user.UserCustomRepository;
import com.app.neos.repository.user.UserRepository;
import com.sun.xml.bind.v2.util.CollisionCheckStack;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    private final MainUserCustomRepository mainUserCustomRepository;


    public void saveUser(User user) { userRepository.save(user);}



//    유저 불러오기
//    public Slice<UserDTO> findUserPage(Pageable pageable){
//        return neosUserCustomRepository.findAllPage(pageable);
//    }

    public List<UserDTO> findUserPage(){
        return mainUserCustomRepository.findUserForMain().stream().map(User::toDTO).collect(Collectors.toList());
    }

//    스터디 불러오기
    public List<StudyDTO> findStudyPage(){
        return neosStudyCustomRepository.findStudyForMain().stream().map(Study::toDTO).collect(Collectors.toList());

    }

//    상점 불러오기
    public Slice<StoreDTO> findStorePage(Pageable pageable){
        return mainStoreCustomRepository.findAllPage(pageable);
    }
//    public List<StoreDTO> findStorePage(){
//        return mainStoreCustomRepository.findStoreForMain(store);
//    }


//    커뮤니티 불러오기
    public Slice<CommunityDTO> findCommunityPage(Pageable pageable){
        return communityCustomRepository.findAllPage(pageable);
    }


//  배너 불러오기
    public Slice<BannerDTO> findBannerPage(Pageable pageable){
        return mainBannerCustomRepository.findAllBannerpage(pageable);
    }

//  유저 상세
    public UserDTO findByUserId(Long userId){
        return userCustomRepository.findById(userId);
    }

    }

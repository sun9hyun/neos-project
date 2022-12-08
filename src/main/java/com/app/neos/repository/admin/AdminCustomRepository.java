package com.app.neos.repository.admin;

import com.app.neos.domain.community.CommunityDTO;
import com.app.neos.domain.counseling.CounselingDTO;
import com.app.neos.domain.store.StoreDTO;
import com.app.neos.domain.study.StudyDTO;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminCustomRepository {

    public User findByUserId(Long userId);

    public UserDTO findByUserDTOId(Long userId);

//    public Page<UserDTO> findAllUserPage(Pageable pageable);



    public Page<StudyDTO> findAllStudyPage(Pageable pageable);

    public Page<CommunityDTO> findAllCommunityPage(Pageable pageable);

    public Page<CounselingDTO> findAllCounselingPage(Pageable pageable);

    public Page<StoreDTO> findAllStorePage(Pageable pageable);


}

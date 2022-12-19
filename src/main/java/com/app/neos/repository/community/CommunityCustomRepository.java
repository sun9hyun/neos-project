package com.app.neos.repository.community;

import com.app.neos.domain.community.CommunityDTO;
import com.app.neos.domain.study.StudyDTO;
import com.app.neos.domain.user.UserDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface CommunityCustomRepository {
    public List<CommunityDTO> findAll();

//  네오스인 5인 목록
    public List<UserDTO> findNeosUser();

//  스터디 5개 목록
    public List<StudyDTO> findStudy();

    public Slice<CommunityDTO> findAllPageS(Pageable pageable);

    public List<CommunityDTO> findAllPage(Pageable pageable);

    public CommunityDTO findByCommunityId(Long communityId);

//    public List<CommunityLikeDTO> findByLikeId(Long communityId);

//    public List<CommunityLikeDTO> findLike(Long communityId, Long userId);

//    public CommunityDTO update(Long communityId);

//    public List<CommunityLikeDTO> findByCommunityAndUser(Long communityId, Long userId);


//    좋아요 할 때 쓸 커뮤니티 PK만 다 가지고 오기
    public List<CommunityDTO> findPK();

}

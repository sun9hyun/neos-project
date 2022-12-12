package com.app.neos.repository.neos;

import com.app.neos.domain.study.StudyDTO;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.study.Study;
import com.app.neos.entity.user.User;
import com.app.neos.repository.user.Search;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NeosUserCustomRepository {
//   모든 유저 조회
    public List<UserDTO> findAll();


//  유저가 속한 스터디
    public List<Study> findByUserId(Long userId);

//    페이징
    public Slice<UserDTO> findAllPage(Pageable pageable);



//    검색
//    public List<UserDTO> findByKeyword(String keyword);


}

package com.app.neos.repository.neos;

import com.app.neos.domain.study.StudyDTO;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.study.Study;
import com.app.neos.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NeosUserCustomRepository {

    public List<UserDTO> findAll();

    public List<Study> findByUserId(Long userId);


}

package com.app.neos.repository.admin;

import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminCustomRepository {

    public User findByUserId(Long userId);

    public UserDTO findByUserDTOId(Long userId);

    public Page<UserDTO> findAllUserPage(Pageable pageable);

}

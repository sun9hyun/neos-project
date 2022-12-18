package com.app.neos.repository.user;

import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserCustomRepository {
    public Page<User> findAllPage(Pageable pageable);



    public List<User> findAllSearch(Search search);

    public List<UserDTO> findAllByOauthId(String oauthId);

    public UserDTO findByOauthId(String oauthId);

    public UserDTO findNoCollegeById(Long userId);

    /* 유저 상세보기 */
    public UserDTO findById(Long userId);


}

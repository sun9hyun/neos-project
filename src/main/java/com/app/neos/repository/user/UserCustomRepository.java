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
}

package com.app.neos.repository.user;

import com.app.neos.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    public List<User> findAllByUserOAuthId(String userOauthId);

    public Optional<User> findByUserOAuthId(String userOauthId);
}

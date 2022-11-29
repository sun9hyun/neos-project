package com.app.neos.repository.user;

import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.user.UserInterest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserInterestRepository extends JpaRepository<UserInterest, Long> {


}

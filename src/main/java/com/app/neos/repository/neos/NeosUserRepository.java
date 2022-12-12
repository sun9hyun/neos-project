package com.app.neos.repository.neos;

import com.app.neos.domain.study.StudyMemberDTO;
import com.app.neos.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NeosUserRepository  extends JpaRepository<User , Integer> {


}

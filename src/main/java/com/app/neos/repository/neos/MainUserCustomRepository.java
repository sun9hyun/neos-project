package com.app.neos.repository.neos;

import com.app.neos.entity.user.User;

import java.util.List;

public interface MainUserCustomRepository {

    public List<User> findUserForMain();

}

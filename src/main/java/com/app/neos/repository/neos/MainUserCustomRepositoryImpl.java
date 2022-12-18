package com.app.neos.repository.neos;


import com.app.neos.entity.user.QUser;
import com.app.neos.entity.user.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.neos.entity.user.QUser.user;

@RequiredArgsConstructor
@Repository
public class MainUserCustomRepositoryImpl implements MainUserCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<User> findUserForMain() {
        return jpaQueryFactory.selectFrom(user).orderBy(user.createdDate.desc()).limit(4L).fetch();
    }
}

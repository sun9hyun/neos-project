package com.app.neos.repository.user;

import com.app.neos.domain.user.QUserDTO;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.user.QUser;
import com.app.neos.entity.user.User;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.app.neos.entity.user.QUser.*;

@Repository
@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<User> findAllPage(Pageable pageable) {

        List<User> users= jpaQueryFactory.selectFrom(user)
               .orderBy(user.userId.desc())
               .offset(pageable.getOffset())
               .limit(pageable.getPageSize())
                .fetch();

        long total = jpaQueryFactory.selectFrom(user).fetch().size();

        return new PageImpl<>(users,pageable,total);
    }

    @Override
    public List<User> findAllSearch(Search search) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if(search.getUserNickName() != null){
            booleanBuilder.and(user.userNickName.eq(search.getUserNickName()));
        }
        if(search.getUserOAuthId() != null){
            booleanBuilder.and(user.userOAuthId.eq(search.getUserOAuthId()));
        }


        return jpaQueryFactory.selectFrom(user)
                .where(
                        booleanBuilder
                ).fetch();
    }




    private BooleanExpression nameEq(String userNickName){
        return StringUtils.hasText(userNickName) ? user.userNickName.eq(userNickName) : null;
    }

    private BooleanExpression oauthEq(String userOAuthId){
        return StringUtils.hasText(userOAuthId) ? user.userOAuthId.eq(userOAuthId) : null;
    }
    private BooleanExpression nameEqOrOauthEq(String userNickName, String userOauthId){
        return nameEq(userNickName).or(oauthEq(userOauthId));
    }

}

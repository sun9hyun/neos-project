package com.app.neos.repository.notice;

import com.app.neos.domain.notice.NoticeDTO;
import com.app.neos.domain.notice.QNoticeDTO;
import com.app.neos.domain.user.QUserDTO;
import com.app.neos.domain.user.UserDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.neos.entity.notice.QNotice.notice;
import static com.app.neos.entity.user.QUser.user;

@Repository
@RequiredArgsConstructor
public class NoticeCustomRepositoryImpl implements NoticeCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;
    
    @Override
    public List<NoticeDTO> findAll() {
        return jpaQueryFactory.select(new QNoticeDTO(
                notice.noticeId,
                notice.noticeTitle,
                notice.noticeContent,
                notice.createdDate,
                notice.updatedDate
                ))
                .from(notice)
                .orderBy(notice.noticeId.desc())
                .fetch();
    }


    @Override
    public Page<NoticeDTO> findAllPage(Pageable pageable) {
        List<NoticeDTO> noticeDTOS = jpaQueryFactory.select(new QNoticeDTO(
                notice.noticeId,
                notice.noticeTitle,
                notice.noticeContent,
                notice.createdDate,
                notice.updatedDate
                 ))
                .from(notice)
                .orderBy(notice.noticeId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = jpaQueryFactory.select(new QNoticeDTO(
                notice.noticeId,
                notice.noticeTitle,
                notice.noticeContent,
                notice.createdDate,
                notice.updatedDate
        ))
                .from(notice)
                .fetch().size();

        return new PageImpl<>(noticeDTOS,pageable,total);
    }

    @Override
    public NoticeDTO findByNoticeId(Long noticeId) {
        return jpaQueryFactory.select(new QNoticeDTO(
                notice.noticeId,
                notice.noticeTitle,
                notice.noticeContent,
                notice.createdDate,
                notice.updatedDate
        ))
                .from(notice)
                .where(notice.noticeId.eq(noticeId))
                .fetchOne();
    }
}

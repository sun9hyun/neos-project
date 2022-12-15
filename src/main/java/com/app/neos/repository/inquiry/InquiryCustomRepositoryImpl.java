package com.app.neos.repository.inquiry;

import com.app.neos.domain.inquiry.InquiryDTO;
import com.app.neos.domain.inquiry.QInquiryDTO;
import com.app.neos.domain.user.QUserDTO;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.inquiry.QInquiry;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.neos.entity.inquiry.QInquiry.inquiry;
import static com.app.neos.entity.user.QUser.user;

@RequiredArgsConstructor
@Repository
public class InquiryCustomRepositoryImpl implements InquiryCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<InquiryDTO> findById(Long userId) {

        return jpaQueryFactory.select(new QInquiryDTO(
                inquiry.inquiryId,
                inquiry.inquiryContent,
                inquiry.inquiryReply,
                inquiry.inquiryStatus,
                inquiry.createdDate,
                inquiry.user,
                inquiry.updatedDate
        ))
                .from(inquiry)
                .where(inquiry.user.userId.eq(userId))
                .orderBy(inquiry.updatedDate.desc())
                .fetch();

    }
}

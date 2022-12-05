package com.app.neos.repository.counseling;

import com.app.neos.domain.counseling.CounselingDTO;
import com.app.neos.domain.counseling.QCounselingDTO;
import com.app.neos.entity.counseling.QCounseling;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CounselingCustomRepositoryImpl implements CounselingCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<CounselingDTO> findAll() {
        return jpaQueryFactory.select(new QCounselingDTO(
                QCounseling.counseling.counselingId,
                QCounseling.counseling.counselingTitle,
                QCounseling.counseling.counselingContent,
                QCounseling.counseling.user
        ))
                .from(QCounseling.counseling)
                .orderBy(QCounseling.counseling.counselingId.desc())
                .fetch();
    }
}

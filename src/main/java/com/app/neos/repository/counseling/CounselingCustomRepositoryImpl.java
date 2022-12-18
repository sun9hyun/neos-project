package com.app.neos.repository.counseling;

import com.app.neos.domain.counseling.CounselingDTO;
import com.app.neos.domain.counseling.QCounselingDTO;
import com.app.neos.entity.counseling.QCounseling;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.app.neos.entity.counseling.QCounseling.*;

@Repository
@RequiredArgsConstructor
public class CounselingCustomRepositoryImpl implements CounselingCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<CounselingDTO> findAll() {
        return jpaQueryFactory.select(new QCounselingDTO(
                counseling.counselingId,
                counseling.counselingTitle,
                counseling.counselingContent,
                counseling.user,
                counseling.createdDate,
                counseling.updatedDate
        ))
                .from(counseling)
                .orderBy(counseling.counselingId.desc())
                .fetch();
    }

    @Override
    public Slice<CounselingDTO> findAllPage(Pageable pageable) {
        List<CounselingDTO> counselingDTOS = jpaQueryFactory.select(new QCounselingDTO(
                counseling.counselingId,
                counseling.counselingTitle,
                counseling.counselingContent,
                counseling.user,
                counseling.createdDate,
                counseling.updatedDate
        ))
                .from(counseling)
                .orderBy(counseling.updatedDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()+1)
                .fetch();

        ArrayList<CounselingDTO> content = (ArrayList<CounselingDTO>)counselingDTOS;

        boolean hasNext = false;
        if(content.size() > pageable.getPageSize()){
            content.remove(pageable.getPageSize());
            hasNext = true;
        }
        return new SliceImpl<>(content, pageable, hasNext);
    }
}

package com.app.neos.service.search;

import com.app.neos.domain.store.StoreDTO;
import com.app.neos.domain.study.StudyDTO;
import com.app.neos.repository.search.SearchCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final SearchCustomRepository searchCustomRepository;

    // 스터디 검색
    public Slice<StudyDTO> findByKeywordStudy(String keyword, Pageable pageable){
        return searchCustomRepository.findByKeywordStudy(keyword, pageable);
    }

    //자료상점 검색
    public Slice<StoreDTO> findByKeywordStore(String keyword, Pageable pageable){
        return searchCustomRepository.findByKeywordStore(keyword, pageable);
    }
}

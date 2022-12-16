package com.app.neos.controller.search;

import com.app.neos.domain.store.StoreDTO;
import com.app.neos.domain.study.StudyDTO;
import com.app.neos.repository.search.SearchCustomRepository;
import com.app.neos.service.search.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search/*")
public class SearchRestController {
    private final SearchService searchService;

    @PostMapping("/study")
    public Slice<StudyDTO> studyDTOS(@PageableDefault(size = 5, sort = "id" , direction = Sort.Direction.DESC) Pageable pageable, String keyword){
        return searchService.findByKeywordStudy(keyword, pageable);
    }

    @PostMapping("/store")
    public Slice<StoreDTO> storeDTOS(@PageableDefault(size = 5, sort = "id" , direction = Sort.Direction.DESC) Pageable pageable, String keyword){
        return searchService.findByKeywordStore(keyword, pageable);
    }





}

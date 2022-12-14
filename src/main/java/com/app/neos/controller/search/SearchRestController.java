package com.app.neos.controller.search;

import com.app.neos.domain.study.StudyDTO;
import com.app.neos.repository.search.SearchCustomRepository;
import com.app.neos.service.search.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search/*")
public class SearchRestController {
    private final SearchService searchService;


    @PostMapping("{keyword}")
    public Slice<StudyDTO> studyDTOS(@PageableDefault(size = 5, sort = "id" , direction = Sort.Direction.DESC) Pageable pageable, @PathVariable("keyword")  String keyword){
        return searchService.findByKeyWord(keyword, pageable);
    }
}

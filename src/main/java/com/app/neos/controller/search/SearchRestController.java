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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search/*")
public class SearchRestController {
    private final SearchService searchService;

    // 스터디 더보기
    @PostMapping("/study")
    public Slice<StudyDTO> studyDTOS(@PageableDefault(size = 5, sort = "id" , direction = Sort.Direction.DESC) Pageable pageable, String keyword){
        return searchService.findByKeywordStudy(keyword, pageable);
    }

    // 자료상점 더보기
    @PostMapping("/store")
    public Slice<StoreDTO> storeDTOS(@PageableDefault(size = 5, sort = "id" , direction = Sort.Direction.DESC) Pageable pageable, String keyword){
        return searchService.findByKeywordStore(keyword, pageable);
    }


    //    @PostMapping("/search")
//    public String searchBefore(StudyDTO studyDTO, StoreDTO storeDTO, Model model, @PageableDefault(size = 5, sort = "id" , direction = Sort.Direction.DESC) Pageable pageable, String keyword){
//        Slice<StudyDTO> studyDTOList =  searchService.findByKeywordStudy(keyword, pageable);
//        Slice<StoreDTO> storeDTOList =  searchService.findByKeywordStore(keyword, pageable);
////
////        model.addAttribute("keyword", keyword);
////        model.addAttribute("studys", studyDTOList);
////        model.addAttribute("studysize", studyDTOList.getNumberOfElements());
////        model.addAttribute("stores", storeDTOList);
////        model.addAttribute("storesize", storeDTOList.getNumberOfElements());
////        model.addAttribute("total", studyDTOList.getNumberOfElements()+storeDTOList.getNumberOfElements());
//        return "app/search/searchComplete";
//    }



}

package com.app.neos.controller.search;

import com.app.neos.domain.store.StoreDTO;
import com.app.neos.domain.study.StudyDTO;
import com.app.neos.service.search.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/search/*")
@Slf4j
public class SearchController {
    private final SearchService searchService;

    @GetMapping("/search")
    public String search(){
        return "app/search/searchComplete";
    }

    @PostMapping("/search")
    public String searchBefore(StudyDTO studyDTO, StoreDTO storeDTO, Model model,@PageableDefault(size = 5, sort = "id" , direction = Sort.Direction.DESC) Pageable pageable,  String keyword){
        Slice<StudyDTO> studyDTOList =  searchService.findByKeywordStudy(keyword, pageable);
        Slice<StoreDTO> storeDTOList =  searchService.findByKeywordStore(keyword, pageable);

        model.addAttribute("keyword", keyword);
        model.addAttribute("studys", studyDTOList);
        model.addAttribute("studysize", studyDTOList.getNumberOfElements());
        model.addAttribute("stores", storeDTOList);
        model.addAttribute("storesize", storeDTOList.getNumberOfElements());
        model.addAttribute("total", studyDTOList.getNumberOfElements()+storeDTOList.getNumberOfElements());
        return "app/search/searchComplete";
    }

//    @PostMapping("/searchOk")
//    public RedirectView searchOk(){
//
//        return new RedirectView("search");
//    }

//    @PostMapping("{keyword}")
//    public Slice<StudyDTO> studyDTOS(@PageableDefault(size = 5, sort = "id" , direction = Sort.Direction.DESC) Pageable pageable, @PathVariable("keyword")  String keyword){
//        return searchService.findByKeywordStudy(keyword, pageable);
//    }
//
//    @PutMapping("{keyword}")
//    public Slice<StoreDTO> storeDTOS(@PageableDefault(size = 5, sort = "id" , direction = Sort.Direction.DESC) Pageable pageable, @PathVariable("keyword")  String keyword){
//        return searchService.findByKeywordStore(keyword, pageable);
//    }


    @GetMapping("/search-after")
    public String searchAfter(){
        return "app/search/searchAfter";
    }

    @GetMapping("/search-complete")
    public String searchComplete(){
        return "app/search/searchComplete";
    }
}

package com.example.neosproject.controller.search;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/search/*")
public class SearchController {
    @GetMapping("/searchBefore")
    public String searchBefore(){
        return "app/search/searchBefore";
    }
    @GetMapping("/searchAfter")
    public String searchAfter(){
        return "app/search/searchAfter";
    }
    @GetMapping("/searchComplete")
    public String searchComplete(){
        return "app/search/searchComplete";
    }
}

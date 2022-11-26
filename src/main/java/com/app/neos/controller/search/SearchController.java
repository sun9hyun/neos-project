package com.app.neos.controller.search;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/search/*")
public class SearchController {
    @GetMapping("/search-before")
    public String searchBefore(){
        return "app/search/searchBefore";
    }
    @GetMapping("/search-after")
    public String searchAfter(){
        return "app/search/searchAfter";
    }
    @GetMapping("/search-complete")
    public String searchComplete(){
        return "app/search/searchComplete";
    }
}

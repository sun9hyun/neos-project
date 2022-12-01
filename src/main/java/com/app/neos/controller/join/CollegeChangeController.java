package com.app.neos.controller.join;

import com.app.neos.domain.college.CollegeDTO;
import com.app.neos.service.join.JoinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/change/*")
@RequiredArgsConstructor
@Slf4j
public class CollegeChangeController {
    private final JoinService joinService;

    @PostMapping("/{collegeCity}")
    public List<CollegeDTO> getNameList(@PathVariable String collegeCity){

        return joinService.showColleges(collegeCity);
    }

    @PutMapping("/{collegeName}")
    public CollegeDTO getUrl(@PathVariable String collegeName){
        return joinService.showUrl(collegeName);
    }
}

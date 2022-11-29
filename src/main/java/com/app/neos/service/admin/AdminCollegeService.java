package com.app.neos.service.admin;

import com.app.neos.entity.college.College;
import com.app.neos.repository.college.CollegeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminCollegeService {
    private final CollegeRepository collegeRepository;


//    대학교 추가
    public void saveCollege(College college){
        collegeRepository.save(college);
    }

}

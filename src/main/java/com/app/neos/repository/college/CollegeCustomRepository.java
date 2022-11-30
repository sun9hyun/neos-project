package com.app.neos.repository.college;

import com.app.neos.domain.college.CollegeDTO;
import com.app.neos.entity.college.College;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CollegeCustomRepository {
    public List<CollegeDTO> findAll();

    public Page<CollegeDTO> findAllPage(Pageable pageable);

    public CollegeDTO findByCollegeId(Long collegeId);

    public int countByUser(Long collegeId);
}

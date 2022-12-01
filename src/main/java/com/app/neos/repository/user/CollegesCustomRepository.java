package com.app.neos.repository.user;


import com.app.neos.domain.college.CollegeDTO;

import java.util.List;

public interface CollegesCustomRepository {

    public List<CollegeDTO> findAll();

    public List<CollegeDTO> findByCollegeCity(String collegeCity);

    public CollegeDTO findByCollegeName(String collegeName);
}

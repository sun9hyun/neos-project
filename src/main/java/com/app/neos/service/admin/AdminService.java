package com.app.neos.service.admin;

import com.app.neos.domain.college.CollegeDTO;
import com.app.neos.entity.college.College;
import com.app.neos.repository.college.CollegeCustomRepository;
import com.app.neos.repository.college.CollegeCustomRepositoryImpl;
import com.app.neos.repository.college.CollegeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final CollegeRepository collegeRepository;
    private final CollegeCustomRepository collegeCustomRepository;


    //    대학교 추가
    public void saveCollege(College college){
        collegeRepository.save(college);
    }

    //    대학교 목록
    public List<CollegeDTO> findCollege(){
        return collegeCustomRepository.findAll();
    }

    //    대학교 목록 페이징 처리
    public Page<CollegeDTO> findCollegePage(Pageable pageable){
        return collegeCustomRepository.findAllPage(pageable);
    }

    //    대학교 상세 보기
    public CollegeDTO findByCollegeId(Long collegeId){
        return collegeCustomRepository.findByCollegeId(collegeId);
    }

    //    대학교 관련 유저 수
    public int countByUser(Long collegeId){
        return collegeCustomRepository.countByUser(collegeId);
    }


    //    대학교 엔티티 아이디로 조회
    public College findCollegeEntity(Long collegeId){
        return collegeRepository.findById(collegeId).get();
    }

}

package com.app.neos.service.admin;

import com.app.neos.domain.banner.BannerDTO;
import com.app.neos.domain.college.CollegeDTO;
import com.app.neos.entity.banner.Banner;
import com.app.neos.entity.college.College;
import com.app.neos.entity.notice.Notice;
import com.app.neos.repository.banner.BannerCustomRepository;
import com.app.neos.repository.banner.BannerRepository;
import com.app.neos.repository.college.CollegeCustomRepository;
import com.app.neos.repository.college.CollegeCustomRepositoryImpl;
import com.app.neos.repository.college.CollegeRepository;
import com.app.neos.repository.notice.NoticeRepository;
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
    private final BannerRepository bannerRepository;
    private final BannerCustomRepository bannerCustomRepository;
    private final NoticeRepository noticeRepository;



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

    //    대학 체크 여부에 따라 삭제하기
    public void deleteByCheck(String collegeIds){
        String[] arCollegeIds = collegeIds.split(",");

        for (int i = 0; i < arCollegeIds.length; i++){
            collegeRepository.deleteById(Long.parseLong(arCollegeIds[i]));
        }
    }

    //    대학교 아이디 하나로 삭제
    public void deleteById(String collegeId){
        collegeRepository.deleteById(Long.parseLong(collegeId));
    }



    //    배너 등록
    public void saveBanner(Banner banner){
        bannerRepository.save(banner);
    }

    //    배너 전체 목록 조회
    public List<BannerDTO> findBanner(){
        return bannerCustomRepository.findAll();
    }





    //    공지사항 등록
    public void saveNotice(Notice notice){
        noticeRepository.save(notice);
    }


}

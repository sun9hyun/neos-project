package com.app.neos.controller.myPage;

import com.app.neos.domain.Alarm.AlarmDTO;
import com.app.neos.domain.college.CollegeDTO;
import com.app.neos.domain.neos.NeosPointDTO;
import com.app.neos.domain.neos.NeosPowerDTO;
import com.app.neos.domain.store.StoreDTO;
import com.app.neos.domain.study.StudyDTO;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.college.College;
import com.app.neos.entity.study.StudyFollow;
import com.app.neos.entity.user.User;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.service.admin.AdminService;
import com.app.neos.service.alarm.AlarmService;
import com.app.neos.service.join.JoinService;
import com.app.neos.service.myPage.MyPageService;
import com.app.neos.service.neosUser.NeosUserService;
import com.app.neos.service.store.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/my-detail/*")
public class MyPageRestController {
    private final MyPageService myPageService;
    private final StoreService storeService;
    private final JoinService joinService;
    private final UserRepository userRepository;
    private final AlarmService alarmService;
    private final AdminService adminService;

    // 유저 탈퇴
    @GetMapping("/delete/{userId}")
    public String delete(@PathVariable("userId") String userId){
        System.out.println("*********************userId " + userId + "*********************");
        adminService.deleteByUserId(userId);
        return "delete success";
    }

    // 마이페이지 정보 수정
    @PostMapping("/modify/{userId}")
    public String updateUserInfo(@RequestBody UserDTO userDTO, @PathVariable("userId") Long userId){
        System.out.println("*********************업데이트 " + userDTO.getUserFile() + "*********************");
//        User user = userRepository.findById(userDTO.getUserId()).get();
//        College college = myPageService.findCollege(userDTO.getCollegeName()).toEntity();
//        user.changeCollege(college);

        myPageService.updateUser(userDTO);
        return "update success";
    }

//    // 마이페이지 대학 정보 수정
//    @PostMapping("/check/{college}")
//    public Long checkCollegeId(@PathVariable("college") String college){
//        return myPageService.findCollege(college);
//    }
    
    // 마이페이지 알림 조회
    @GetMapping("/alarm/{userId}")
    public List<AlarmDTO> findAlarmByUserId(@PathVariable("userId") Long userId){
        return alarmService.showAll(userId);
    }

    // 마이페이지 프로필 사진 이름 저장
    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile profileImageFile) throws IOException {
        System.out.println("*********************들어옴*********************");

        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
        String uploadForder = Paths.get("C:", "neos", "upload").toString();
        String profileUploadForder = Paths.get("profileImage", today).toString();
        String uploadPath = Paths.get(uploadForder, profileUploadForder).toString();

        File dir = new File(uploadPath);
        if (dir.exists() == false) {
            dir.mkdirs();
        }


        UUID uuid = UUID.randomUUID();
        String profileImageName = uuid + "_" + profileImageFile.getOriginalFilename();

        try {
            File target = new File(uploadPath, profileImageName);
            profileImageFile.transferTo(target);

        } catch (Exception e) {
            return "false";
        }

        return profileUploadForder + "\\" + profileImageName;
    }

    // 마이페이지 스터디 구독 조회
    @GetMapping("/study-follow/{userId}")
    public List<StudyDTO> selectStudyFollow(@PathVariable("userId") Long userId){
        return myPageService.findStudyFollow(userId);
    }

    // 마이페이지 네오스인 구독 조회
    @GetMapping("/follow/{userId}")
    public List<UserDTO> selectFollow(@PathVariable("userId") Long userId){
        return myPageService.findFollow(userId);
    }

    // 마이페이지 스터디 참여 조회
    @GetMapping("/study/{userId}")
    public List<StudyDTO> selectStudyJoin(@PathVariable("userId") Long userId){
        return myPageService.findJoinStudy(userId);
    }

    // 마이페이지 스터디 지원 조회
    @PostMapping("/study/{userId}")
    public List<StudyDTO> selectStudySupporter(@PathVariable("userId") Long userId){
        return myPageService.findSupportStudy(userId);
    }

    // 마이페이지 네오포인트
    @GetMapping("/neosPoint/{userId}")
    public List<NeosPointDTO> neoPointList(@PathVariable("userId") Long userId){
        return myPageService.findNeosPoint(userId);
    }

    // 마이페이지 네오력
    @GetMapping("/neosPower/{userId}")
    public List<NeosPowerDTO> neoPowerList(@PathVariable("userId") Long userId){
        return myPageService.findNeosPower(userId);
    }

    // 마이페이지 대학교 도시 조회
    @GetMapping("/information/city")
    public List<String> collegeCityInfo(){
        return joinService.getCollegeCityList();
    }

    // 마이페이지 도시별 대학교 조회
    @GetMapping("/change/{collegeCity}")
    public List<CollegeDTO> getNameList(@PathVariable String collegeCity){
        return joinService.showColleges(collegeCity);
    }

    // 마이페이지 대학교 이메일 조회
    @GetMapping("/change-email/{collegeName}")
    public CollegeDTO getUrl(@PathVariable String collegeName){
        return joinService.showUrl(collegeName);
    }

    // 마이페이지 자료상점 게시글 조회
    @GetMapping("/store/{userId}")
//    public List<StoreDTO> storeList(@PathVariable("userId") Long userId) {return myPageService.findStoreByUserId(userId);}
    public Slice<StoreDTO> storeList(@PageableDefault(size = 4) Pageable pageable, @PathVariable("userId") Long userId){
        return storeService.findStorePageBySlice(pageable, userId);
    }

    // 마이페이지 스터디 게시글 조회
//    @GetMapping("/study/{userId}")
//    public Slice<StoreDTO> storeList(@PageableDefault(size = 4) Pageable pageable, @PathVariable("userId") Long userId){
//        return storeService.findStorePageBySlice(pageable, userId);
//    }

    // 마이페이지 유저 정보 조회
    @GetMapping("/information/{userId}")
    public UserDTO userInfo(@PathVariable("userId") Long userId){
//        return myPageService.findUserById(userId);
        return userRepository.findById(userId).get().toDTO();
    }

    // 마이페이지 네오력
//    @GetMapping("/neoPower/{userID}")
//    public NeosPowerDTO neoPowerList(@PathVariable("userId") Long userId){
//        return userRepository.findById(userId).get().toDTO();
//    }


}




























package com.app.neos.controller.community;

import com.app.neos.domain.counseling.CounselingDTO;
import com.app.neos.entity.counseling.Counseling;
import com.app.neos.repository.counseling.CounselingCustomRepository;
import com.app.neos.service.counseling.CounselingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/counseling/*")
public class CounselingRestController {
    private final CounselingService counselingService;
    private final CounselingCustomRepository counselingCustomRepository;

//    게시글 전체 조회
    @GetMapping("/counselingList")
    public Slice<CounselingDTO> list(@PageableDefault(size = 5, sort = "updatedDate", direction = Sort.Direction.DESC) Pageable pageable){
        return counselingCustomRepository.findAllPage(pageable);
    }

//    게시글 등록
    @PostMapping("/counselingOk")
    public String write(@RequestBody CounselingDTO counselingDTO){
        counselingService.saveCounseling(counselingDTO);
        return "counseling write success";
    }

//    게시글 수정
    @PutMapping("/counselingUpdate")
    public String modify(@RequestBody CounselingDTO counselingDTO){
        counselingService.updateCounseling(counselingDTO);
        return "counseling modify success";
    }

//    게시글 삭제
    @DeleteMapping("/counselingDelete")
    public String delete(@RequestBody CounselingDTO counselingDTO){
        counselingService.deleteCounseling(counselingDTO);
        return "counseling delete success";
    }

}

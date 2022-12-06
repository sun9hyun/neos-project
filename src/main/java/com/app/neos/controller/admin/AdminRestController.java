package com.app.neos.controller.admin;

import com.app.neos.domain.college.CollegeDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin-rest/*")
@Slf4j
public class AdminRestController {

    @PostMapping("college-upload")
    public String updateProfileImage(@RequestParam MultipartFile profileImageFile) {
        log.info("이미지"+profileImageFile.toString());
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

    @PostMapping("banner-upload")
    public String updateBannerImage(@RequestPart("bannerImageFile") MultipartFile bannerImageFile) {
        log.info("이미지"+bannerImageFile.toString());
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
        String uploadForder = Paths.get("C:", "neos", "upload").toString();
        String profileUploadForder = Paths.get("profileImage", today).toString();
        String uploadPath = Paths.get(uploadForder, profileUploadForder).toString();

        File dir = new File(uploadPath);
        if (dir.exists() == false) {
            dir.mkdirs();
        }


        UUID uuid = UUID.randomUUID();
        String profileImageName = uuid + "_" + bannerImageFile.getOriginalFilename();

        try {
            File target = new File(uploadPath, profileImageName);
            bannerImageFile.transferTo(target);

        } catch (Exception e) {
            return "false";
        }

        return profileUploadForder + "\\" + profileImageName;
    }
}

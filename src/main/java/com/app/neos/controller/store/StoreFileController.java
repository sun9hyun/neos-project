package com.app.neos.controller.store;

import com.app.neos.domain.store.StoreFlieDTO;
import com.app.neos.service.store.StoreFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/file/*")
public class StoreFileController {

    // 추가
    @PostMapping("/upload")
    public List<StoreFlieDTO> upload(MultipartFile upload) throws IOException {

        System.out.println("*********************" + upload + "*********************");

        String rootPath = "C:/upload";
        String uploadFileName = null;
//        String fileQRPath = "https://chart.googleapis.com/chart?cht=qr&chs=200x200&chl=";
        List<StoreFlieDTO> files = new ArrayList<>();

        File uploadPath = new File(rootPath, createDirectoryByNow());
        // 이미 파일이 만들어졌는지 확인
        if(!uploadPath.exists()){
            uploadPath.mkdirs();
        }

        StoreFlieDTO storeFlieDTO = new StoreFlieDTO();
        UUID uuid = UUID.randomUUID();
        String fileName = upload.getOriginalFilename();
        uploadFileName = uuid.toString() + "_" + fileName;
        storeFlieDTO.setStoreFileName(fileName);
        storeFlieDTO.setStoreFileUuid(uuid.toString());
        storeFlieDTO.setStoreFilePath(createDirectoryByNow());
        storeFlieDTO.setStoreFileSize(upload.getSize());
//            storeFlieDTO.setStoreFileQR(fileQRPath);

        File saveFile = new File(uploadPath, uploadFileName);
        upload.transferTo(saveFile);

        files.add(storeFlieDTO);

//        for(MultipartFile multipartFile : upload){
//            StoreFlieDTO storeFlieDTO = new StoreFlieDTO();
//            UUID uuid = UUID.randomUUID();
//            String fileName = multipartFile.getOriginalFilename();
//            uploadFileName = uuid.toString() + "_" + fileName;
//            storeFlieDTO.setStoreFileName(fileName);
//            storeFlieDTO.setStoreFileUuid(uuid.toString());
//            storeFlieDTO.setStoreFilePath(createDirectoryByNow());
//            storeFlieDTO.setStoreFileSize(multipartFile.getSize());
////            storeFlieDTO.setStoreFileQR(fileQRPath);
//
//            File saveFile = new File(uploadPath, uploadFileName);
//            multipartFile.transferTo(saveFile);
//
//            files.add(storeFlieDTO);
//        }
        return files;
    }

    @PostMapping("/delete")
    public void delete(String uploadPath, String fileName){
        File file = new File("C:/upload", uploadPath + "/" + fileName);
        if(file.exists()){
            file.delete();
        }
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> download(String fileName) throws UnsupportedEncodingException {
        Resource resource =new FileSystemResource("C:/upload/" + fileName);
        HttpHeaders header = new HttpHeaders();
        String name = resource.getFilename();
        name = name.substring(name.indexOf("_") + 1);
        header.add("Content-Disposition", "attachment;filename=" + new String(name.getBytes("UTF-8")));
        return new ResponseEntity<>(resource, header, HttpStatus.OK);
    }

    public String createDirectoryByNow(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date now = new Date();
        return format.format(now);
    }

}

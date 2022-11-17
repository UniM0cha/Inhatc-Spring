package kr.inhatc.spring.solstice_shop.item.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileService {
    public String uploadFile(String uploadPath, String oriFileName, byte[] fileData) throws IOException {
        // uuid 생성
        UUID uuid = UUID.randomUUID();

        // 확장자
        String extention = oriFileName.substring(oriFileName.lastIndexOf("."));

        // 파일 이름은 uuid + 확장자
        String savedFileName = uuid.toString() + extention;

        // 업로드 된 파일 경로
        String fileUploadUrl = uploadPath + "/" + savedFileName;

        FileOutputStream fos = new FileOutputStream(fileUploadUrl);
        fos.write(fileData);
        fos.close();

        return savedFileName;
    }

    public void deleteFile(String filePath) {
        File deleteFile = new File(filePath);

        if (deleteFile.exists()) {
            deleteFile.delete();
            log.info("파일을 삭제했습니다.");
        } else {
            log.info("파일이 존재하지 않습니다.");
        }
    }
}

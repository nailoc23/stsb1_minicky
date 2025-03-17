package com.touzone.stsb.config;

import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;
import java.io.File;

@Configuration
public class FileUploadConfig {

    private static final String UPLOAD_DIR = "D:/upload"; // 원하는 업로드 경로 설정
    //private static final String UPLOAD_DIR = "/minicookei/upload"; // 원하는 업로드 경로 설정

    @PostConstruct
    public void init() {
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            boolean created = uploadDir.mkdirs(); // 경로가 없으면 생성
            if (created) {
                System.out.println("업로드 디렉토리가 생성되었습니다: " + UPLOAD_DIR);
            } else {
                System.err.println("업로드 디렉토리 생성 실패: " + UPLOAD_DIR);
            }
        } else {
            System.out.println("업로드 디렉토리가 이미 존재합니다: " + UPLOAD_DIR);
        }
    }

    public static String getUploadDir() {
        return UPLOAD_DIR;
    }
}

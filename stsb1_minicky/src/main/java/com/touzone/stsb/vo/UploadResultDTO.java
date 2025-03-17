package com.touzone.stsb.vo;

import java.io.Serializable;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

//lombok 라이브러리 사용시
//@Data
//@AllArgsConstructor
public class UploadResultDTO implements Serializable {

    private String fileName;
    private String uuid;
    private String folderPath;

    public String getImageURL() {
        return URLEncoder.encode(folderPath+"/"+uuid+"_"+fileName, StandardCharsets.UTF_8);
    }
    
    // 모든 필드를 초기화하는 생성자 추가
    public UploadResultDTO(String fileName, String uuid, String folderPath) {
        this.fileName = fileName;
        this.uuid = uuid;
        this.folderPath = folderPath;
    }

    // toString() 추가 (객체 정보 확인용)
    @Override
    public String toString() {
        return "UploadResultDTO{" +
                "fileName='" + fileName + '\'' +
                ", uuid='" + uuid + '\'' +
                ", folderPath='" + folderPath + '\'' +
                '}';
    }
}

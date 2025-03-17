package com.touzone.stsb.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.touzone.stsb.vo.UploadResultDTO;

@RestController
public class UploadController {

	private String uploadPath = "D:/upload";

    /*파일 업로드, 업로드 결과 반환*/
	@PostMapping("/uploadAjax")
    public ResponseEntity<List<UploadResultDTO>> uploadFile(MultipartFile[] uploadFiles){
        List<UploadResultDTO> resultDTOList = new ArrayList<>();

        for(MultipartFile uploadFile : uploadFiles) {

            //이미지 파일만 업로드 가능
            if(uploadFile.getContentType().startsWith("image")==false){
                System.out.println("this file is not image type");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            //실제 파일 이름 IE나 Edge는 전체 경로가 들어오므로
            String originalName = uploadFile.getOriginalFilename();
            String fileName = originalName.substring(originalName.lastIndexOf("\\") +1);
            System.out.println("fileName: " + fileName);

            //날짜 폴더 생성
            String folderPath = makeFolder();

            //UUID
            String uuid= UUID.randomUUID().toString();
            //저장할 파일 이름 중간에 "_" 를 이용해서 구분
            String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;
            //saveName = uploadPath\folderPath\ uuid_fileName
            Path savePath = Paths.get(saveName); // 상대 경로 정의
            try{
                uploadFile.transferTo(savePath); // multipartFile 객체의 transferTo 메서드, 업로드처리이다.
                resultDTOList.add(new UploadResultDTO(fileName,uuid,folderPath));
                System.out.println("getImageURL: " + resultDTOList.get(0).getImageURL()); //한국어를 encoding
            } catch(IOException e){
                e.printStackTrace();
            }
        } //end for
        return new ResponseEntity<>(resultDTOList, HttpStatus.OK);
    }

    /*날짜 폴더 생성*/
    private String makeFolder() {

        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        String folderPath = str.replace("/", File.separator);

        // make folder --------
        File uploadPathFolder = new File(uploadPath, folderPath);

        if(!uploadPathFolder.exists()) {
            boolean mkdirs = uploadPathFolder.mkdirs();
            System.out.println("uploadPathFolder.exists(): "+uploadPathFolder.exists());
        }

        return folderPath;

    }
    
    @GetMapping("/display")
    public ResponseEntity<byte[]> getFile(String fileName) {

        ResponseEntity<byte[]> result;

        try {
            String srcFileName = URLDecoder.decode(fileName, StandardCharsets.UTF_8);

            System.out.println("fileName: " + srcFileName);

            File file = new File(uploadPath + File.separator + srcFileName);

            System.out.println("file: " + file);

            HttpHeaders header = new HttpHeaders();


            // MIME 타입 처리
            header.add("Content-Type", Files.probeContentType(file.toPath()));

            // 파일 데이터 처리
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);


        } catch (Exception e) {
        	System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return result;

    }
    
    // 예제 싱글 또는 멀티
    // 날짜별로 폴더생성
    public String makeDir() {
    	Date date=new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
    	String now=sdf.format(date);

    	String path=uploadPath + "\\" +now; //경로
    	File file = new File(path);

    	if(file.exists()==false) {//파일이 존재하면 true
    		file.mkdir(); //폴더생성
    	}

    	return path;
    }
    
    @PostMapping("/uploadsingle_ok")
	@ResponseBody //Response가 붙으면 return의 값이 요청이 온곳으로 반환
	public String uploadSingleOk(@RequestParam("file")MultipartFile file, @RequestParam("writer")String writer) {
		//System.out.println(file);
		//System.out.println(writer);
		//파일명
		String origin = file.getOriginalFilename(); 
		//브라우저별로 경로가 포함되서 올라오는 경우가 있기에 간단한 처리.
		String filename=origin.substring(origin.lastIndexOf("\\")+1); 
		//폴더생성
		String filepath=makeDir();
		//중복파일의 처리
		String uuid=UUID.randomUUID().toString();
		//최종저장경로
		String savename=filepath+"\\"+uuid+"_"+filename;
		try {
			File save = new File(savename); //세이브경로
			file.transferTo(save);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "success";
	}
    
    @PostMapping("/uploadmulti_ok")
   	@ResponseBody //Response가 붙으면 return의 값이 요청이 온곳으로 반환
   	public String uploadMultiOk(@RequestParam("file") List<MultipartFile> list, @RequestParam("writer")String writer) {
    	//리스트에서 빈값은 제거
		list=list.stream().filter((x)->x.isEmpty()==false ).collect(Collectors.toList());
		
		for(MultipartFile file:list) {
			//파일명
			String origin = file.getOriginalFilename(); 
			//브라우저별로 경로가 포함되서 올라오는 경우가 있기에 간단한 처리.
			String filename=origin.substring(origin.lastIndexOf("\\")+1); 
			//폴더생성
			String filepath=makeDir();
			//중복파일의 처리
			String uuid=UUID.randomUUID().toString();
			//최종저장경로
			String savename=filepath+"\\"+uuid+"_"+filename;
			try {
				File save = new File(savename); //세이브경로
				file.transferTo(save);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
   		
   		return "success";
   	}

}
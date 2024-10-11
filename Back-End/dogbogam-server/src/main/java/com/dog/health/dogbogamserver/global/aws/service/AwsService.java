package com.dog.health.dogbogamserver.global.aws.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.dog.health.dogbogamserver.global.web.dto.response.ExceptionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.dog.health.dogbogamserver.global.web.exception.CustomException;
import com.dog.health.dogbogamserver.global.web.exception.ErrorCode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


@RequiredArgsConstructor
@Service
@Slf4j
public class AwsService {
    @Value("${aws.s3.bucket}")
    private String bucketName;

    private final AmazonS3 S3Client;

    public Map<String, Object> uploadFile(MultipartFile multipartFile, String path) throws IOException {
        Map<String, Object> uploadParam = new HashMap<>();

        String localFileName = UUID.randomUUID() +"_" +multipartFile.getOriginalFilename();
        File uploadFile = convert(multipartFile, localFileName)
                .orElseThrow(() -> new CustomException(ErrorCode.FAILED_CONVERT_FILE));

        String generatedFileName = path + "/" + localFileName;

        uploadParam.put("s3FileName", generatedFileName);

        log.info(uploadParam.toString());

        try {
            S3Client.putObject(
                    new PutObjectRequest(bucketName, generatedFileName, uploadFile));
                            //.withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (AmazonS3Exception e) {
            throw new IOException("Error uploading file", e);
        }
        String uploadImageUrl = S3Client.getUrl(bucketName, generatedFileName).toString();
        uploadParam.put("uploadImageUrl", uploadImageUrl);

        uploadFile.delete();
        log.info("Local file deleted : {}", uploadFile.getAbsolutePath());

        return uploadParam;
    }

    private Optional<File> convert(MultipartFile file, String fileName) throws IOException {
        log.info("Converting file: {}", fileName);
        File convertFile = new File(fileName);
        if (convertFile.createNewFile()) {
            try (FileOutputStream fileOutputStream = new FileOutputStream(convertFile)) {
                fileOutputStream.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }

    // 이미지 삭제
    public void deleteFile(String s3FileName) throws IOException {
        try {
            S3Client.deleteObject(new DeleteObjectRequest(bucketName, s3FileName));
        } catch (AmazonS3Exception e) {
            throw new IOException("Error deleting photo ", e);
        }
    }

}
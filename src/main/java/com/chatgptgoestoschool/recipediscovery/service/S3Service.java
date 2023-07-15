package com.chatgptgoestoschool.recipediscovery.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.chatgptgoestoschool.recipediscovery.configuration.AwsS3Config;

@Service
@Transactional
public class S3Service {
  @Value("${aws.s3.bucket.name}")
  private String bucketName;
  private final AmazonS3 client;

  public S3Service(AwsS3Config config) {
    client = config.amazonS3();
  }

  public String putObject(String filename, MultipartFile file) {
    ObjectMetadata md = new ObjectMetadata();
    md.setContentLength(file.getSize());
    md.setContentType(file.getContentType());

    try {
      var putObjectRequest = new PutObjectRequest(bucketName, filename, file.getInputStream(), md)
          .withCannedAcl(CannedAccessControlList.PublicRead);
      client.putObject(putObjectRequest);
      return client.getUrl(bucketName, filename).toExternalForm();

    } catch (IOException ioException) {
      throw new RuntimeException(ioException);
    }
  }

  public void deleteObject(String objectName) {
    client.deleteObject(bucketName, objectName);
  }
}

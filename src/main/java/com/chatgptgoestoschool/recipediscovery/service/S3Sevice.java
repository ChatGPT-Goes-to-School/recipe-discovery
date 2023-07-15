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
public class S3Sevice {
  @Value("${aws.s3.bucket.name}")
  private String bucketName;
  private final AmazonS3 client;

  public S3Sevice(AwsS3Config config) {
    client = config.amazonS3();
  }

  public void putObject(String bucketName, BucketObjectRepresentaion representation,
      boolean publicObject) throws IOException {

    String objectName = representation.getObjectName();
    String objectValue = representation.getText();

    File file = new File("." + File.separator + objectName);
    FileWriter fileWriter = new FileWriter(file, false);
    PrintWriter printWriter = new PrintWriter(fileWriter);
    printWriter.println(objectValue);
    printWriter.flush();
    printWriter.close();

    try {
      var putObjectRequest = new PutObjectRequest(bucketName, objectName, file)
          .withCannedAcl(CannedAccessControlList.PublicRead);
      client.putObject(putObjectRequest);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      file.delete();
    }

  }

  public void deleteObject(String bucketName, String objectName) {
    client.deleteObject(bucketName, objectName);
  }
}

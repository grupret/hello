package com.gaian.poc.controller;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.File;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloController {

    @Value("${config.hello}")
    public String greet;


    @GetMapping("/hello")
    public String sayHello() {
        System.out.println("*------------------------------------------" + greet);
//        try {
//            String bucketName = "gaianapp";
//            String stringObjKeyName = "content-test";
//            String fileObjKeyName = "*** File object key name ***";
//
//            Regions clientRegion = Regions.US_EAST_1;
//            File fileName = new File(
//                getClass().getClassLoader().getResource("application.properties").getFile());
//            System.out.println("******************** FILE IS " + fileName.getAbsolutePath() + "****************");
//            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
//                .withRegion(clientRegion)
//                .build();
//            System.out.println("Client got created");
//            s3Client.putObject(bucketName, stringObjKeyName, "Uploaded String Object");
//
//            System.out.println("Object was put");
//            PutObjectRequest request = new PutObjectRequest(bucketName, fileObjKeyName, fileName);
//            ObjectMetadata metadata = new ObjectMetadata();
//            metadata.setContentType("plain/text");
//            metadata.addUserMetadata("title", "someTitle");
//            request.setMetadata(metadata);
//
//            System.out.println("Meta data was there");
//
//            s3Client.putObject(request);
//
//        } catch (AmazonServiceException e) {
//            System.out.println(e.getCause());
//            e.printStackTrace();
//        } catch (SdkClientException e) {
//            // Amazon S3 couldn't be contacted for a response, or the client
//            // couldn't parse the response from Amazon S3.
//            e.printStackTrace();
//        }

        return greet;
    }


    @GetMapping("/upload")
    public String upload() throws Exception {
        try {
            String bucketName = "gaianapp";
            String stringObjKeyName = "content-test";
            String fileObjKeyName = "*** File object key name ***";

            Regions clientRegion = Regions.DEFAULT_REGION;
            File fileName = new File(getClass().getResource("application.properties").getFile());
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(clientRegion)
                .build();
            System.out.println("Client got created");
            s3Client.putObject(bucketName, stringObjKeyName, "Uploaded String Object");

            System.out.println("Object was put");
            PutObjectRequest request = new PutObjectRequest(bucketName, fileObjKeyName, fileName);
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("plain/text");
            metadata.addUserMetadata("title", "someTitle");
            request.setMetadata(metadata);

            System.out.println("Meta data was there");

            s3Client.putObject(request);

        } catch (AmazonServiceException e) {
            System.out.println(e.getCause());
            e.printStackTrace();
        } catch (SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            e.printStackTrace();
        }

        return "File Uploaded!!";
    }

}

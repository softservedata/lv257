package com.softserve.edu.Resources.util;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.IOException;
import java.io.InputStream;

public class FileUploadUtility {

    public String uploadFile(RedirectAttributes redirectAttributes, MultipartFile file, String code) {

       BasicAWSCredentials credentials = new BasicAWSCredentials("AKIAIRSMVLMUF2W4ZSAA", "guZpt9C/VXOHpi1UwAOMHR0kKeDybkWEzPbELVc9");
       AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.EU_CENTRAL_1)
                                                .withCredentials(new AWSStaticCredentialsProvider(credentials)).build();

        String bucketName = code.toLowerCase();
        s3Client.createBucket(bucketName);

        String url = "";

        try{
            InputStream is = file.getInputStream();

            ObjectMetadata objectMetadata = new ObjectMetadata();
            if(file.getContentType().equals("application/pdf")){
                objectMetadata.setContentType("application/pdf");
                objectMetadata.setContentDisposition("inline");
                code = code + ".pdf";
            } else if(file.getContentType().equals("image/jpeg")){
                code = code + ".jpg";
            }else{
                code = code + ".png";
            }

            s3Client.putObject(new PutObjectRequest(bucketName,code,is, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
            //get a reference to the image object
            S3Object s3Object = s3Client.getObject(new GetObjectRequest(bucketName,code));
            //add to model
            redirectAttributes.addAttribute("picUrl",s3Object.getObjectContent().getHttpRequest().getURI().toString());
            System.out.println(s3Object.getObjectContent().getHttpRequest().getURI().toString());
             url = s3Object.getObjectContent().getHttpRequest().getURI().toString();
        }catch(IOException e){
            e.printStackTrace();
        }
        return url;

    }

}



//    // private static final String ABS_PATH_2 = System.getenv( "PICTURES_PATH");
//    private static final String ABS_PATH = "C:\\Users\\lenovo\\IdeaProjects\\trunk\\lv257\\src\\main\\webapp\\resources\\upload\\";
//    private static String REAL_PATH = "";
//    private static final Logger logger = LoggerFactory.getLogger(FileUploadUtility.class);
//
//    public static void uploadFile(HttpServletRequest httpRequest, MultipartFile file, String code) {
//
//
//        REAL_PATH = httpRequest.getSession().getServletContext().getRealPath("/resources/upload/");
//
//        logger.info(REAL_PATH);
//
//        // to make sure all the directory exists.
//        if (!new File(ABS_PATH).exists()) {
//            // create the directories
//            new File(ABS_PATH).mkdirs();
//        }
//
//        if (!new File(REAL_PATH).exists()) {
//            // create the directories
//            new File(REAL_PATH).mkdirs();
//        }
//
//        try {
//            if(file.getContentType().equals("application/pdf")){
//
//                file.transferTo(new File(REAL_PATH + code + ".pdf"));
//                file.transferTo(new File(ABS_PATH + code + ".pdf"));
//
//            } else if(file.getContentType().equals("image/jpeg")){
//
//                file.transferTo(new File(REAL_PATH + code + ".jpg"));
//                file.transferTo(new File(ABS_PATH + code + ".jpg"));
//
//            }else{
//
//                file.transferTo(new File(REAL_PATH + code + ".png"));
//                file.transferTo(new File(ABS_PATH + code + ".png"));
//            }
//        } catch (IOException e) {
//
//            e.printStackTrace();
//        }
//
//    }
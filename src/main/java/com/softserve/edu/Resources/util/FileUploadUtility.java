package com.softserve.edu.Resources.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

public class FileUploadUtility {
   // private static final String ABS_PATH_2 = System.getenv( "PICTURES_PATH");
    private static final String ABS_PATH = "C:\\Users\\lenovo\\IdeaProjects\\trunk\\lv257\\src\\main\\webapp\\resources\\upload\\";
    private static String REAL_PATH = "";
    private static final Logger logger = LoggerFactory.getLogger(FileUploadUtility.class);

    public static void uploadFile(HttpServletRequest httpRequest, MultipartFile file, String code) {


        REAL_PATH = httpRequest.getSession().getServletContext().getRealPath("/resources/upload/");

        logger.info(REAL_PATH);

        // to make sure all the directory exists.
       if (!new File(ABS_PATH).exists()) {
           // create the directories
           new File(ABS_PATH).mkdirs();
       }

        if (!new File(REAL_PATH).exists()) {
            // create the directories
            new File(REAL_PATH).mkdirs();
        }

        try {
            if(file.getContentType().equals("application/pdf")){

                file.transferTo(new File(REAL_PATH + code + ".pdf"));
                file.transferTo(new File(ABS_PATH + code + ".pdf"));

            } else if(file.getContentType().equals("image/jpeg")){

                file.transferTo(new File(REAL_PATH + code + ".jpg"));
                file.transferTo(new File(ABS_PATH + code + ".jpg"));

            }else{

                file.transferTo(new File(REAL_PATH + code + ".png"));
                file.transferTo(new File(ABS_PATH + code + ".png"));
            }
        } catch (IOException e) {

            e.printStackTrace();
        }

    }
}

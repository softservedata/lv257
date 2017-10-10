package com.softserve.edu.Resources.util;

import org.springframework.web.multipart.MultipartFile;


public abstract class FileUpload {
     abstract String uploadFile( MultipartFile file, String code);
}

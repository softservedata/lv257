package com.softserve.edu.Resources.service.impl;


import com.softserve.edu.Resources.dao.impl.DocumentDAOImpl;
import com.softserve.edu.Resources.entity.Document;
import com.softserve.edu.Resources.util.FileUploadUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class DocumentService {
    @Autowired
    DocumentDAOImpl documentDAO;

    public void fillUpDocument(Document document) {

        if(!document.getFile().getOriginalFilename().equals("")){
            FileUploadUtility fileUploadUtility = new FileUploadUtility();

            document.setDocumentsURL(fileUploadUtility.uploadFile( document.getFile(),document.getCode()));
        }

        String [] string = document.getFile().getContentType().split("/");

        String extension = string [1];
        document.setFileExtension(extension);

        documentDAO.makePersistent(document);

    }

    public Document getDocumentById(long id) {
        Optional<Document> document = documentDAO.findById(id);
        return document.orElse(new Document());
    }
}

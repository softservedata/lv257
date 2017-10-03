package com.softserve.edu.Resources.service.impl;


import com.softserve.edu.Resources.dao.impl.DocumentDAOImpl;
import com.softserve.edu.Resources.entity.Document;
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

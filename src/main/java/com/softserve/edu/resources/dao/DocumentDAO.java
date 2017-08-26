package com.softserve.edu.resources.dao;



import com.softserve.edu.resources.entity.Document;

import java.net.URL;
import java.util.Set;

public interface DocumentDAO {

    Document getDocumentByLinks(Set<URL> documentLinks);

}

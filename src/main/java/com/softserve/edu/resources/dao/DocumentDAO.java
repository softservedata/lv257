package com.softserve.edu.resources.dao;



import com.softserve.edu.resources.entity.Document;

import java.net.URL;
import java.util.Set;

/**
 * Created by User on 26.08.2017.
 */
public interface DocumentDAO {

    Document getDocumentByLinks(Set<URL> documentLinks);

}

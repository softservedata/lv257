package com.softserve.edu.resources.dao;



import com.softserve.edu.resources.entity.Document;

import java.util.List;

/**
 * Created by User on 26.08.2017.
 */
public interface DocumentDAO {

    public Document getDocumentByLinks(List<String> documentLinks);

    public List<String> getLinks();
}

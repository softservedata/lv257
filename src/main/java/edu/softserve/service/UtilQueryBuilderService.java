package edu.softserve.service;

import java.util.Map;

public interface UtilQueryBuilderService {

    String queryForLookUpByResouceType(String tableName, Map<String,String> valuesToSearch);
    
}

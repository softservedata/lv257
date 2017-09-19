package com.softserve.edu.Resources.service;

import java.util.Map;

public interface QueryBuilderService {

    String lookUpByResouceType(String tableName, Map<String,String> valuesToSearch);
    
}

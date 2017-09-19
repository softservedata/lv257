package com.softserve.edu.Resources.dto; 

import java.util.Map;

public class GenericResourceDTO {

    private String resourceTableName;
    private Map <String, String> resourcePropertyValue;
    
    
    public GenericResourceDTO(String resourceTableName, Map<String, String> resourcePropertyValue) {
        
        this.resourceTableName = resourceTableName;
        this.resourcePropertyValue = resourcePropertyValue;
    }


    public String getResourceTableName() {
        return resourceTableName;
    }


    public Map<String, String> getResourcePropertyValue() {
        return resourcePropertyValue;
    }
    
    
    
}

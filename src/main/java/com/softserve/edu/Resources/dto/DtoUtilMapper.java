package com.softserve.edu.Resources.dto;

import com.softserve.edu.Resources.entity.ResourceType;

import java.util.ArrayList;
import java.util.List;

public class DtoUtilMapper {

    public static List<ResourceTypeDTO> resTypesToResTypesDTO (List<ResourceType> resTypes){
        
       List <ResourceTypeDTO> resTypeDTOs = new ArrayList<>();
       
       for (ResourceType resType : resTypes) {
        resTypeDTOs.add(new ResourceTypeDTO(resType.getId(),  resType.getTableName(),  resType.getTypeName()));
       }
       
       return resTypeDTOs;
    }

    public static ResourceTypeDTO resTypesToResTypesDTO (ResourceType resType){

        return new ResourceTypeDTO(resType.getId(), resType.getTableName(), resType.getTypeName());
    }
    
}

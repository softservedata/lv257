package com.softserve.edu.Resources.dto;

import com.softserve.edu.Resources.entity.ResourceType;

import java.util.ArrayList;
import java.util.List;



public class DtoUtilMapper {

    public static List<ResourceTypeDTO> resTypesToResTypesDTO (List<ResourceType> resTypes){
        
       List <ResourceTypeDTO> resTypeDTOs = new ArrayList<ResourceTypeDTO>();
       
       for (ResourceType resType : resTypes) {
           
        ResourceTypeDTO resTypeDTO = new ResourceTypeDTO();
        resTypeDTO.setId(resType.getId());
        resTypeDTO.setTableName(resType.getTableName());
        resTypeDTO.setTypeName(resType.getTypeName());
        
        resTypeDTOs.add(resTypeDTO);
       }
       
       return resTypeDTOs;
    }
    
}

package com.softserve.edu.dto;

public class RoleDTO {

    private int idDTO;
    private String nameDTO;
    private String community_restridedDTO;

    public RoleDTO(int idDTO, String nameDTO, String community_restridedDTO) {
        this.idDTO = idDTO;
        this.nameDTO = nameDTO;
        this.community_restridedDTO = community_restridedDTO;
    }

    public int getIdDTO() {
        return idDTO;
    }

    public void setIdDTO(int idDTO) {
        this.idDTO = idDTO;
    }

    public String getNameDTO() {
        return nameDTO;
    }

    public void setNameDTO(String nameDTO) {
        this.nameDTO = nameDTO;
    }

    public String getCommunity_restridedDTO() {
        return community_restridedDTO;
    }

    public void setCommunity_restridedDTO(String community_restridedDTO) {
        this.community_restridedDTO = community_restridedDTO;
    }
}

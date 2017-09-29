package com.softserve.edu.Resources.service.impl;

import com.softserve.edu.Resources.dao.OwnerDAO;
import com.softserve.edu.Resources.dto.SearchOwnerDTO;
import com.softserve.edu.Resources.dto.SelectInfoDTO;
import com.softserve.edu.Resources.entity.Owner;
import com.softserve.edu.Resources.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerDAO ownerDAO;

    @Override
    public void addOwner(Owner owner) {
        ownerDAO.addOwner(owner);
    }

    @Override
    public void updateOwner(Owner owner) {
        ownerDAO.updateOwner(owner);
    }

    @Override
    public List<Owner> getAllCompanies() {
        return ownerDAO.getAllCompanies();
    }

    @Override
    public List<Owner> getAllPersons() {
        return ownerDAO.getAllPersons();
    }

    @Override
    public List<Owner> getAllOwners() {
        return ownerDAO.getAllOwners();
    }

    @Override
    public Owner getOwnerById(long id) {
        return ownerDAO.getOwnerById(id);
    }

    @Override
    public SelectInfoDTO fromOwnerToDto(Owner owner) {
        SelectInfoDTO infoDTO = new SelectInfoDTO();
        infoDTO.setObjectId(owner.getId());
        infoDTO.setMessage(owner.customToString());

        return infoDTO;
    }

    @Override
    public List<Owner> findOwners(SearchOwnerDTO searchOwnerDTO) {
        StringBuilder stringBuilder = new StringBuilder();
        String ownerType = searchOwnerDTO.getOwnerType();
        String ownerTypeFirstChar = String.valueOf(ownerType.charAt(0)).toLowerCase();

        Map<String, String> fieldsAndValues = searchOwnerDTO.getFieldsAndValues();

        stringBuilder.append("SELECT " + ownerTypeFirstChar + " FROM " + ownerType + " " + ownerTypeFirstChar);
        stringBuilder.append(" WHERE ");

        Iterator<Map.Entry<String, String>> entries = fieldsAndValues.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();
            stringBuilder.append(entry.getKey() + "=\'" + entry.getValue() + "\' ");
            if (entries.hasNext()) {
                stringBuilder.append(" AND ");
            }
        }
        System.out.println(stringBuilder.toString());
        String readyQuery = stringBuilder.toString();

        return ownerDAO.findOwners(readyQuery);
    }
}

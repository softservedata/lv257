package com.softserve.edu.Resources.service.impl;

import com.softserve.edu.Resources.dao.OwnerDAO;
import com.softserve.edu.Resources.dto.SelectInfoDTO;
import com.softserve.edu.Resources.entity.Owner;
import com.softserve.edu.Resources.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public SelectInfoDTO fromAddressToDto(Owner owner) {
        SelectInfoDTO infoDTO = new SelectInfoDTO();
        infoDTO.setObjectId(owner.getId());
        infoDTO.setMessage(owner.customToString());

        return infoDTO;
    }
}

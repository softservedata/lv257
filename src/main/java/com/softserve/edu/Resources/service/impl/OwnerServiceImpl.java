package com.softserve.edu.Resources.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.edu.Resources.dao.OwnerDAO;
import com.softserve.edu.Resources.entity.Address;
import com.softserve.edu.Resources.entity.Company;
import com.softserve.edu.Resources.entity.Owner;
import com.softserve.edu.Resources.entity.Person;
import com.softserve.edu.Resources.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Arrays;
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
    public Owner parseOwnerWithAddress(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        Owner owner = null;
        Address ownerAddress = null;

        String[] allOwnerInfo = json.split("\\|");

        System.out.println(json);
        System.out.println(Arrays.toString(allOwnerInfo));
        String addressJson = allOwnerInfo[0];
        System.out.println("address json: "  + addressJson);
        String ownerJson = allOwnerInfo[1];
        System.out.println("owner json: "  + ownerJson);
        String ownerType = allOwnerInfo[2];
        System.out.println("owner type: "  + ownerType);
        try {
            if (Integer.parseInt(ownerType) == 2) {
                owner = objectMapper.readValue(ownerJson, Company.class);
            } else {
                owner = objectMapper.readValue(ownerJson, Person.class);
            }
            ownerAddress = objectMapper.readValue(addressJson, Address.class);

            System.out.println(owner);
            System.out.println(ownerAddress);

            ownerAddress.addOwner(owner);
            owner.setAddress(ownerAddress);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return owner;
    }
}

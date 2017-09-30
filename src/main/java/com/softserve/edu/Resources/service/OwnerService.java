package com.softserve.edu.Resources.service;

import com.softserve.edu.Resources.dto.OwnerDTO;
import com.softserve.edu.Resources.dto.SearchOwnerDTO;
import com.softserve.edu.Resources.dto.ValidationErrorDTO;
import com.softserve.edu.Resources.entity.Owner;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface OwnerService {

    Owner addOwner(Owner owner);

    void updateOwner(Owner owner);

    void deleteOwnerById(Long id);

    List<Owner> getAllCompanies();

    List<Owner> getAllPersons();

    List<Owner> getAllOwners();

    Owner getOwnerById(long id);

    List<OwnerDTO> fromOwnerToOwnerDto(List<Owner> owners);

    List<Owner> findOwners(SearchOwnerDTO searchOwnerDTO);

    ValidationErrorDTO validationDTO(BindingResult result);

}

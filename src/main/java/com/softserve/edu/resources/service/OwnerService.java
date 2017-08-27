package com.softserve.edu.resources.service;

import java.util.List;

import com.softserve.edu.entity.Owner;

public interface OwnerService <E> {

	List <E> findOwnersByName(String name);
	E findOwnerByIPNCode (Integer IPNCode);
	
}

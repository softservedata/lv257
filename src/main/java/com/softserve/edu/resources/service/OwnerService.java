package com.softserve.edu.resources.service;

import java.util.List;

public interface OwnerService <E> {

	List <E> findOwnersByName(String name);
	E findOwnerByIPNCode (Integer IPNCode);
	
}

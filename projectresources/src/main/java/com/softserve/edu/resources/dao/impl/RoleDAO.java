/*
 * 
 */
package com.softserve.edu.resources.dao.impl;

import org.hibernate.Session;

import com.softserve.edu.resources.entities.Role;

public class RoleDAO extends crudDAOImpl<Role> {
	public RoleDAO(Session session) {
		super(Role.class, session);
	}

	public void addRole(Role roleNew) {

	}
}

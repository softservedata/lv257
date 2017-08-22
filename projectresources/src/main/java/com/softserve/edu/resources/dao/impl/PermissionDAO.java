/*
 * 
 */
package com.softserve.edu.resources.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.softserve.edu.resources.entities.Permissions;

public class PermissionDAO extends crudDAOImpl<Permissions> {
	public PermissionDAO(Session session) {
		super(Permissions.class, session);
	}

	public void setPermission(Permission permission) {

	}

	public List<Permission> getPermission(int featureID) {

	}
}

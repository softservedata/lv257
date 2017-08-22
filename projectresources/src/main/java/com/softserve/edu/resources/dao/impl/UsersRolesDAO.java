/*
 * 
 */
package com.softserve.edu.resources.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.softserve.edu.resources.entities.User;
import com.softserve.edu.resources.entities.UsersRoles;

public class UsersRolesDAO extends crudDAOImpl<UsersRoles> {

	public UsersRolesDAO(Session session) {
		super(UsersRoles.class, session);
	}

	public List<User> approveUserRoleAll(List<User> communityID) {

	}

	public List<User> approveUserRoleByID(int userID) {

	}

	public List<User> editUserRoleByID(int userID) {

	}

	public List<User> answerUserRoleByID(int userID) {

	}

	public List<User> declineUserRoleAll(List<User> communityID) {

	}

	public List<User> declineUserRoleByID(int userID) {

	}
}

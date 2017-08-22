/*
 * 
 */
package com.softserve.edu.resources.dto;

/* ManageUsersAndRoles */
public class UsersRolesDTO {

	private int id;
	private int id_user;
	private int id_role;

	public UsersRolesDTO(int id, int id_user, int id_role) {
		this.id = id;
		this.id_user = id_user;
		this.id_role = id_role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public int getId_role() {
		return id_role;
	}

	public void setId_role(int id_role) {
		this.id_role = id_role;
	}
}

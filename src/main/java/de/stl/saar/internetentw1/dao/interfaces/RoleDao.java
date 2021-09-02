package de.stl.saar.internetentw1.dao.interfaces;

import java.util.List;

import de.stl.saar.internetentw1.entities.RoleEntity;


public interface RoleDao {

	void addRole(RoleEntity role);

	void removeRole(long roleId);

	List<RoleEntity> findAllRoles();

	RoleEntity findRoleByName(String roleName);

}

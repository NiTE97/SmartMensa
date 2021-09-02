package de.stl.saar.internetentw1.dao.interfaces;

import java.util.List;

import de.stl.saar.internetentw1.entities.UserEntity;

public interface UserDao {

	void addUser(UserEntity user);

	void removeUser(long userId);

	List<UserEntity> findAllUsers();

	void setRoleDao(RoleDao roleDao);

	UserEntity findUser(String username);

}

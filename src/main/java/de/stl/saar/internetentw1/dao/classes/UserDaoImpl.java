package de.stl.saar.internetentw1.dao.classes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import de.stl.saar.internetentw1.dao.interfaces.RoleDao;
import de.stl.saar.internetentw1.dao.interfaces.UserDao;
import de.stl.saar.internetentw1.entities.RoleEntity;
import de.stl.saar.internetentw1.entities.UserEntity;
import de.stl.saar.internetentw1.utils.RandomUtils;

/**
 * Simuliert die User-Tabelle einer Datenbank mithilfe
 * einer Map, wobei der Primärschlüssel der Schlüssel der
 * Map ist. 
 * @author christopher
 *
 */
public class UserDaoImpl implements UserDao {
	private Map<Long, UserEntity> userTable;
	private RoleDao roleDao;
	
	@PostConstruct
	public void initialize() {
		userTable = new HashMap<>();
		roleDao = new RoleDaoImpl();
		final RoleEntity adminRole = roleDao.findRoleByName("admin");
		final RoleEntity userRole = roleDao.findRoleByName("user");
		final UserEntity user1 = new UserEntity(1l, "colbertz", "1234", "colbertz@htwsaar.de", adminRole);
		final UserEntity user2 = new UserEntity(2l, "wpy", "qwertz", "wpy@htwsaar.de", userRole);
		final UserEntity user3 = new UserEntity(3l, "api", "5678", "api@htwsaar.de.", userRole);
		addUser(user1);
		addUser(user2);
		addUser(user3);
	}

	@Override
	public void addUser(final UserEntity user) {
		long primaryKeyValue = user.getUserId();
		
		if (primaryKeyValue > 0) {
			if (!primaryKeyValueFree(primaryKeyValue)) {
				primaryKeyValue = createPrimaryKeyValue();
			}
		} else {
			primaryKeyValue = createPrimaryKeyValue();
			user.setUserId(primaryKeyValue);
		}
		
		userTable.put(primaryKeyValue, user);
	}
	
	/**
	 * Erzeugt einen neuen Primärschlüsselwert. Dabei wird der Wert
	 * zufällig erzeugt und dann wird geprüft, ob es bereits einen
	 * Datensatz mit diesem Wert gibt. Falls nein, wird dieser
	 * Primärschlüsselwert zurückgegeben. 
	 * @return Der neu erzeugte und noch nicht vergebene Primärschlüsselwert.
 	 */
	private long createPrimaryKeyValue() {
		long primaryKey = 0;
		
		do {
			primaryKey = RandomUtils.nextLong();
		} while(!primaryKeyValueFree(primaryKey));
		
		return primaryKey;
	}
	
	/**
	 * Prüft, ob ein Primärschlüsselwert bereits vergeben ist. 
	 * @param primaryKeyValue Der zu prüfende Wert.
	 * @return True, falls der Wert bereits als Primärschlüsselwert in 
	 * der Tabelle vorkommt, sonst false. 
	 */
	private boolean primaryKeyValueFree(final long primaryKeyValue) {
		final UserEntity userWithPrimaryKey = userTable.get(primaryKeyValue);
		if (userWithPrimaryKey == null) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void removeUser(final long userId) {
		userTable.remove(userId);
	}
	
	@Override
	public List<UserEntity> findAllUsers() {
		final Collection<UserEntity> userCollection = userTable.values();
		final List<UserEntity> users = new ArrayList<>(userCollection);
		return users;
	}

	@Override
	public UserEntity findUser(String username) {
		final Collection<UserEntity> userCollection = userTable.values();
		final List<UserEntity> users = new ArrayList<>(userCollection);
		for (UserEntity user : users){
				if (user.getUserName().equals(username)){
					return user;
				}
		}
		return null;
	}
	
	@Override
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
}

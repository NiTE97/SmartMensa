package de.stl.saar.internetentw1.dao.classes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.stl.saar.internetentw1.dao.interfaces.RoleDao;
import de.stl.saar.internetentw1.entities.RoleEntity;
import de.stl.saar.internetentw1.utils.RandomUtils;
import de.stl.saar.internetentw1.utils.StringUtils;

/**
 * Simuliert die Role-Tabelle einer Datenbank mithilfe
 * einer Map, wobei der Primärschlüssel der Schlüssel der
 * Map ist. 
 * @author christopher
 *
 */
public class RoleDaoImpl implements RoleDao {
	private Map<Long, RoleEntity> roleTable;
	
	public RoleDaoImpl() {
		roleTable = new HashMap<>();
		final RoleEntity role1 = new RoleEntity(1, "admin");
		final RoleEntity role2 = new RoleEntity(2, "user");
		addRole(role1);
		addRole(role2);
	}

	@Override
	public void addRole(final RoleEntity role) {
		long primaryKeyValue = role.getRoleId();
		
		if (primaryKeyValue > 0) {
			if (!primaryKeyValueFree(primaryKeyValue)) {
				primaryKeyValue = createPrimaryKeyValue();
			}
		} else {
			primaryKeyValue = createPrimaryKeyValue();
			role.setRoleId(primaryKeyValue);
		}
		
		roleTable.put(primaryKeyValue, role);
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
		final RoleEntity roleWithPrimaryKey = roleTable.get(primaryKeyValue);
		if (roleWithPrimaryKey == null) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void removeRole(final long roleId) {
		roleTable.remove(roleId);
	}
	
	@Override
	public List<RoleEntity> findAllRoles() {
		final Collection<RoleEntity> roleCollection = roleTable.values();
		final List<RoleEntity> roles = new ArrayList<>(roleCollection);
		return roles;
	}
	
	@Override
	public RoleEntity findRoleByName(final String roleName) {
		final List<RoleEntity> roleList = findAllRoles();
		for (final RoleEntity role: roleList) {
			final boolean roleNameFound = StringUtils.areStringsEqual(roleName, role.getRoleName()); 
			if (roleNameFound) {
				return role;
			}
		}
		
		return null;
	}
}

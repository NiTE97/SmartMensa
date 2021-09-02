package de.stl.saar.internetentw1.dao.classes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.stl.saar.internetentw1.dao.interfaces.DishDao;
import de.stl.saar.internetentw1.entities.DishEntity;
import de.stl.saar.internetentw1.enums.Category;
import de.stl.saar.internetentw1.utils.RandomUtils;

/**
 * Simuliert die Dish-Tabelle einer Datenbank mithilfe
 * einer Map, wobei der Primärschlüssel der Schlüssel der
 * Map ist. 
 * @author christopher
 *
 */
public class DishDaoImpl implements DishDao {
	private Map<Long, DishEntity> dishTable;
	
	public DishDaoImpl() {
		dishTable = new HashMap<>();
		final DishEntity dish1 = new DishEntity("Baumkuchen", 2.0, Category.DESSERT, "baumkuchen");
		final DishEntity dish2 = new DishEntity("Creme Brulee", 2.5, Category.DESSERT, "cremeBrulee");
		final DishEntity dish3 = new DishEntity("Flammkuchen", 7.5, Category.MAIN_DISH, "flammkuchen");
		final DishEntity dish4 = new DishEntity("Grießnockerl-Suppe", 4, Category.SOUP, "griessnockerlsuppe");
		final DishEntity dish5 = new DishEntity("Pudding", 2, Category.DESSERT, "pudding");
		final DishEntity dish6 = new DishEntity("Rindfleischsuppe", 3.5, Category.SOUP, "rindfleischsuppe");
		final DishEntity dish7 = new DishEntity("Rumänischer Salat", 3.5, Category.SALAD, "rumaenischerSalat");
		final DishEntity dish8 = new DishEntity("Einfach nur Salat", 3.5, Category.SALAD, "salat");
		final DishEntity dish9 = new DishEntity("Wiener Schnitzel", 7.0, Category.MAIN_DISH, "schnitzel");
		final DishEntity dish10 = new DishEntity("Tomate-Mozarella", 4.0, Category.SALAD, "tomateMozarella");
		addDish(dish1);
		addDish(dish2);
		addDish(dish3);
		addDish(dish4);
		addDish(dish5);
		addDish(dish6);
		addDish(dish7);
		addDish(dish8);
		addDish(dish9);
		addDish(dish10);
	}

	@Override
	public void addDish(final DishEntity dish) {
		long primaryKeyValue = dish.getDishId();
		
		if (primaryKeyValue > 0) {
			if (!primaryKeyValueFree(primaryKeyValue)) {
				primaryKeyValue = createPrimaryKeyValue();
			}
		} else {
			primaryKeyValue = createPrimaryKeyValue();
			dish.setDishId(primaryKeyValue);
		}
		
		dishTable.put(primaryKeyValue, dish);
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
		final DishEntity dishWithPrimaryKey = dishTable.get(primaryKeyValue);
		if (dishWithPrimaryKey == null) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void removeDish(final long dishId) {
		dishTable.remove(dishId);
	}
	
	@Override
	public List<DishEntity> findAllDishes() {
		final Collection<DishEntity> dishCollection = dishTable.values();
		final List<DishEntity> dishes = new ArrayList<>(dishCollection);
		return dishes;
	}
}

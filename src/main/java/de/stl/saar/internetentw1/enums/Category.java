package de.stl.saar.internetentw1.enums;

public enum Category {
	SOUP("Suppe"), DESSERT("Nachtisch"),
	FRUIT("Obst"), SALAD("Salat"), MAIN_DISH("Hauptgericht");
	
	private String categoryName;
	
	private Category(String categoryName) {
		this.categoryName = categoryName;
	}
	
	@Override
	public String toString() {
		return categoryName;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	
	public static Category getCategoryByName(String name) {
		for (Category category: Category.values()) {
			if(category.categoryName.equals(name)) {
				return category;
			}
		}
		
		return null;
	}
}

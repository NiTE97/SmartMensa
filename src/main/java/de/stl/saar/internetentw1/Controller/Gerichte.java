package de.stl.saar.internetentw1.Controller;

import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import de.stl.saar.internetentw1.dao.classes.DishDaoImpl;
import de.stl.saar.internetentw1.entities.DishEntity;
import de.stl.saar.internetentw1.enums.Category;

@ManagedBean
@SessionScoped
public class Gerichte {
    private DishDaoImpl dishService;
    private List<DishEntity> dishList;

    private String dishName;
    private long dishId;
    private double price;
    private Category category;
    private String imageName;
    
    @PostConstruct
    public void initializeBean(){
        if(dishService == null){
        dishService = new DishDaoImpl();
        dishList = dishService.findAllDishes();
        dishName = "";
        dishId = -1;
        price = -1.0;
        imageName = "";
        }
    }

    public String changeDish(DishEntity dish){
        dishName = dish.getDishName();
        price = dish.getPrice();
        category = dish.getCategory();
        imageName = dish.getImageName();
        dishService.removeDish(dish.getDishId());
        return "createDish";
    }

    public String deleteDish(DishEntity dish){
        dishService.removeDish(dish.getDishId());
        dishList = dishService.findAllDishes();
        clearFields();
        return "verwaltungGerichte";
    }

    public String createDish(){
        DishEntity dish = new DishEntity(dishName, price, category, imageName);
        dishService.addDish(dish);
        dishList = dishService.findAllDishes();
        clearFields();
        return "verwaltungGerichte";
    }

    private void clearFields(){
        dishName = "";
        imageName = "";
        price = 0.0;
    }
    
    public String getDishName(){
        return dishName;
    }

    public long getDishId(){
        return dishId;
    }

    public double getPrice(){
        return price;
    }

    public Category getCategory(){
        return category;
    }

    public List<DishEntity> getDishList(){
        return dishList;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImageName() {
        return "Test";
        //return "resources/images/" + imageName + ".jpg";
    }
}

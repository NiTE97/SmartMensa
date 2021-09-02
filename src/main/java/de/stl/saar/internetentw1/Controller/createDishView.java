package de.stl.saar.internetentw1.Controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import de.stl.saar.internetentw1.dao.classes.DishDaoImpl;
import de.stl.saar.internetentw1.entities.DishEntity;
import de.stl.saar.internetentw1.enums.Category;

@ManagedBean
@SessionScoped
public class createDishView {
    private String dishName;
    private long dishId;
    private double price;
    private Category category;

    private DishDaoImpl dishService;
    private List<DishEntity> dishList;

    private List<Category> options = new ArrayList<Category>();
    
    @PostConstruct
    public void initializeBean(){
    }

    public void initialize(ComponentSystemEvent event){
        options.clear();
        this.options.add(Category.DESSERT);
        this.options.add(Category.MAIN_DISH);
        this.options.add(Category.SOUP);
        this.options.add(Category.SALAD);
        this.options.add(Category.FRUIT);
    }

    public String goBack(){
        return "verwaltungGerichte";
    }

    public Category getCategory() {
        return category;
    }

    public long getDishId() {
        return dishId;
    }

    public List<DishEntity> getDishList() {
        return dishList;
    }

    public String getDishName() {
        return dishName;
    }

    public DishDaoImpl getDishService() {
        return dishService;
    }

    public double getPrice() {
        return price;
    }

    public List<Category> getOptions() {
        return options;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setDishId(long dishId) {
        this.dishId = dishId;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
}

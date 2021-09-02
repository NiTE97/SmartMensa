package de.stl.saar.internetentw1.Controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import de.stl.saar.internetentw1.dao.classes.RoleDaoImpl;
import de.stl.saar.internetentw1.entities.RoleEntity;

@ManagedBean
@SessionScoped
public class createUserView {
    private List<RoleEntity> options = new ArrayList<RoleEntity>();
    
    @PostConstruct
    public void initializeBean(){
    }

    public void initialize(ComponentSystemEvent event){
        options.clear();
        RoleDaoImpl roleService = new RoleDaoImpl();
        options.addAll(roleService.findAllRoles());
    }

    public List<RoleEntity> getOptions() {
        return options;
    }
    
}

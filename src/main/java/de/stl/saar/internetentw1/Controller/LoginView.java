package de.stl.saar.internetentw1.Controller;

import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import de.stl.saar.internetentw1.dao.classes.RoleDaoImpl;
import de.stl.saar.internetentw1.dao.classes.UserDaoImpl;
import de.stl.saar.internetentw1.entities.UserEntity;

@ManagedBean
@SessionScoped
public class LoginView {
    private UserDaoImpl userService;
    private RoleDaoImpl roleService;
    private List<UserEntity> userList;
    private String username;
    private String password;
    private UserEntity currentUser;
    
    @PostConstruct
    public void initializeBean(){
        userService = new UserDaoImpl();
        userService.initialize();
        userList = userService.findAllUsers();
    }

    public void initialize(ComponentSystemEvent event){
        userService = new UserDaoImpl();
        userService.initialize();
        userList = userService.findAllUsers();
        username = "";
        password = "";
    }

    public String login(){
        if (userService.findUser(username) == null){
            return "userNotFound";
        } else {
            UserEntity user = userService.findUser(username);
            if (password.equals(user.getPassword())){
                currentUser = user;
                if (user.getRole().getRoleName().equals("admin")){
                    return "loginSuccessfulAdmin";
                } else {
                    return "loginSuccessfulUser";
                }
            } else {
                return "wrongPassword";
            }
        }
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    
}

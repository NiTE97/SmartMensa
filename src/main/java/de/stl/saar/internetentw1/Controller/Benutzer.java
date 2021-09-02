package de.stl.saar.internetentw1.Controller;

import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import de.stl.saar.internetentw1.dao.classes.UserDaoImpl;
import de.stl.saar.internetentw1.entities.RoleEntity;
import de.stl.saar.internetentw1.entities.UserEntity;

@ManagedBean
@SessionScoped
public class Benutzer {

    private UserDaoImpl userService;
    private List<UserEntity> userList;

    private String userName;
    private RoleEntity role;
    private String emailAddress;
    private String password;
    
    @PostConstruct
    public void initializeBean(){
        if(userService == null){
        userService = new UserDaoImpl();
        userService.initialize();
        userList = userService.findAllUsers();
        userName = "";
        emailAddress = "";
        password = "";    
        }
    }

    //TODO changeUser aus Hauptmenu
    public String changeUser(UserEntity user){
        userName = user.getUserName();
        role = user.getRole();
        emailAddress = user.getEmailAddress();
        password = user.getPassword();
        userService.removeUser(user.getUserId());
        return "createUser";
    }

    public String deleteUser(UserEntity user){
        userService.removeUser(user.getUserId());
        userList = userService.findAllUsers();
        clearFields();
        return "verwaltungBenutzer";
    }

    public String createUser(){
        UserEntity user = new UserEntity(userName, password, emailAddress);
        user.setRole(role);
        userService.addUser(user);
        userList = userService.findAllUsers();
        clearFields();
        return "verwaltungBenutzer";
    }

    private void clearFields(){
        userName = "";
        password = "";
        emailAddress = "";
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public RoleEntity getRole() {
        return role;
    }

    public List<UserEntity> getUserList() {
        return userList;
    }

    public UserDaoImpl getUserService() {
        return userService;
    }

    public String getUserName() {
        return userName;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setRole(RoleEntity role) {
        this.role = role;
    }
    public void setUserList(List<UserEntity> userList) {
        this.userList = userList;
    }

    public void setUserService(UserDaoImpl userService) {
        this.userService = userService;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

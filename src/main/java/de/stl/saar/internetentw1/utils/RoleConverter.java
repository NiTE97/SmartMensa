package de.stl.saar.internetentw1.utils;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import de.stl.saar.internetentw1.dao.classes.RoleDaoImpl;
import de.stl.saar.internetentw1.entities.RoleEntity;

@ManagedBean(name = "roleConverterBean") 
@FacesConverter(value = "roleConverter")
public class RoleConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        RoleDaoImpl roleService = new RoleDaoImpl();
        return (RoleEntity) roleService.findRoleByName(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((RoleEntity) value).getRoleName();
    }
    
}

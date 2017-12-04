package model;

import base.BaseModel;
import base.BaseStore;
import base.XmlStore;
import lombok.*;
import util.SerializableList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "usergroup")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserGroup extends BaseModel {
    public static final int defaultUserGroup = 3;

    @Getter @Setter private String name;

    @Setter private SerializableList<Permission> permissions;
    public SerializableList<Permission> getPermissions(){
        if (permissions == null) permissions = new SerializableList<>();
        return permissions;
    }

    public static XmlStore<UserGroup> store = new XmlStore<UserGroup>("store/usergroup.xml", UserGroup.class, Permission.class);

    @Override
    protected BaseStore getStore() {
        return UserGroup.store;
    }

    public boolean hasPermission(Permission p){
        return permissions.getList().indexOf(p) >= 0;
    }
}
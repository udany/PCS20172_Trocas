package model;

import base.BaseModel;
import base.ModelList;
import base.XmlStore;
import lombok.*;
import model.enums.Permission;

import javax.xml.bind.annotation.XmlRootElement;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Date;

@XmlRootElement(name = "user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseModel {
    @Getter @Setter private String name;
    @Getter @Setter private String password;
    @Getter @Setter private String email;
    @Getter @Setter private Date birthday;
    @Getter @Setter private double rating;
    private ModelList<UserGroup> groups;

    public ModelList<UserGroup> getGroups(){
        if (groups == null) groups = new ModelList<>(UserGroup.store);

        return groups;
    }
    public void setGroups(ModelList<UserGroup> m ){
        if (m != null ) getGroups().setList(m.getList());
    }

    public static XmlStore<User> store = new XmlStore<>("store/user.xml", User.class);

    public static class UserBuilder {
        public UserBuilder password(String password) {
            this.password = hashPassword(password);
            return this;
        }
    }

    private static String hashPassword(String password) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            String encoded = Base64.getEncoder().encodeToString(hash);

            return encoded;
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }

        return null;
    }


    public boolean hasPermission(Permission p){
        for (UserGroup g : getGroups().getModels()){
            if (g.hasPermission(p)) return true;
        }
        return false;
    }
}
package model;

import base.BaseModel;
import base.BaseStore;
import base.XmlStore;
import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "student")
@Builder (builderMethodName = "make")
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseModel {
    @Getter @Setter private String fullName;
    @Getter @Setter private String email;
    @Getter @Setter private String telephone;
    @Getter @Setter private String address;

    public static BaseStore store = new XmlStore<User>("store/user_store.xml", User.class);
}
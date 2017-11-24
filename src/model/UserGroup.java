package model;

import base.BaseModel;
import base.BaseStore;
import base.XmlStore;
import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Date;

@XmlRootElement(name = "usergroup")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserGroup extends BaseModel {
    @Getter @Setter private String name;

    public static BaseStore store = new XmlStore<UserGroup>("store/usergroup.xml", UserGroup.class);
}
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

@XmlRootElement(name = "address")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address extends BaseModel {
    @Getter @Setter private String state;
    @Getter @Setter private String city;
    @Getter @Setter private String neighborhood;
    @Getter @Setter private String complement;
    @Getter @Setter private int number;
    @Getter @Setter private String phone;

    public static XmlStore<Address> store = new XmlStore<Address>("store/address.xml", Address.class);
}
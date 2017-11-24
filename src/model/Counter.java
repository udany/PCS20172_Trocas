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

@XmlRootElement(name = "counter")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Counter extends BaseModel {
    @Getter @Setter private String name;
    @Getter @Setter private String description;

    public static XmlStore<Counter> store = new XmlStore<Counter>("store/counter.xml", Counter.class);
}
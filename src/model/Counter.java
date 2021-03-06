package model;

import base.BaseModel;
import base.BaseStore;
import base.XmlStore;
import lombok.*;
import util.SerializableList;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Date;

@XmlRootElement(name = "counter")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Counter extends BaseModel {
    @Getter @Setter private int userId;
    @Getter @Setter private String name;
    @Getter @Setter private String description;
    @Getter @Setter private SerializableList<CounterItem> items = new SerializableList<>();
    @Getter @Setter private Address address = new Address();

    public static XmlStore<Counter> store = new XmlStore<Counter>("store/counter.xml", Counter.class, CounterItem.class, Address.class);

    @XmlTransient
    public User getUser(){
        return this.userId > 0 ? User.store.GetById(this.userId) : null;
    }

    @Override
    public String toString(){
        return name;
    }
}
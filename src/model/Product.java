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

@XmlRootElement(name = "product")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseModel {
    @Getter @Setter private String name;
    @Getter @Setter private String description;
    @Getter @Setter private double price;
    @Getter @Setter private int condition;

    public static XmlStore<Product> store = new XmlStore<Product>("store/product.xml", Product.class);
}
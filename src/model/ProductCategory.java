package model;

import base.BaseModel;
import base.XmlStore;
import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "product_category")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory extends BaseModel {
    @Getter @Setter private String name;

    public static XmlStore<ProductCategory> store = new XmlStore<ProductCategory>("store/product_category.xml", ProductCategory.class);
}
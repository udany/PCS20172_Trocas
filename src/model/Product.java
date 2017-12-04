package model;

import base.BaseModel;
import base.XmlStore;
import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "product")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseModel
{
    @Getter @Setter private int userId;
    @Getter @Setter private String name;
    @Getter @Setter private String description;
    @Getter @Setter private ProductCondition condition;
    @Getter @Setter private int categoryId;

    public static XmlStore<Product> store = new XmlStore<Product>("store/product.xml", Product.class);

    @XmlTransient
    public User getUser(){
        return this.userId > 0 ? User.store.GetById(this.userId) : null;
    }

    @XmlTransient
    public ProductCategory getCategory(){
        return this.categoryId > 0 ? ProductCategory.store.GetById(this.categoryId) : null;
    }
}
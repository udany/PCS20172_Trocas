package model;

import base.BaseModel;
import base.XmlStore;
import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "counter_item")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CounterItem extends BaseModel {
    @Getter @Setter private int productId;

    public static XmlStore<CounterItem> store = new XmlStore<CounterItem>("store/counter_item.xml", CounterItem.class);

    @XmlTransient
    public Product getProduct(){
        return this.productId > 0 ? Product.store.GetById(this.productId) : null;
    }

    public CounterItem(Product p){
        productId = p.getId();
    }
}
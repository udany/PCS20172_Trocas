package model;

import base.BaseModel;
import base.ModelList;
import base.XmlStore;
import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Date;

@XmlRootElement(name = "exchange")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Exchange extends BaseModel {
    @Getter @Setter private int user1Id;
    @Getter @Setter private int user2Id;

    @Getter @Setter private Date dateStarted;

    // states
    @Getter @Setter private boolean canceled;
    @Getter @Setter private boolean user1Accepted;
    @Getter @Setter private boolean user2Accepted;
    @Getter @Setter private boolean user1Concluded;
    @Getter @Setter private boolean user2Concluded;

    // rating
    @Getter @Setter private int user1Rating;
    @Getter @Setter private int user2Rating;

    private ModelList<Product> user1Products;
    private ModelList<Product> user2Products;

    public ModelList<Product> getUser1Products(){
        if (user1Products == null) user1Products = new ModelList<>(Product.store);

        return user1Products;
    }
    public void setUser1Products(ModelList<Product> m ){
        if (m != null ) getUser1Products().setList(m.getList());
    }

    public ModelList<Product> getUser2Products(){
        if (user2Products == null) user2Products = new ModelList<>(Product.store);

        return user2Products;
    }
    public void setUser2Products(ModelList<Product> m ){
        if (m != null ) getUser2Products().setList(m.getList());
    }

    @XmlTransient
    public User getUser1(){
        return this.user1Id > 0 ? User.store.GetById(this.user1Id) : null;
    }
    @XmlTransient
    public User getUser2(){
        return this.user2Id > 0 ? User.store.GetById(this.user2Id) : null;
    }

    public static XmlStore<Exchange> store = new XmlStore<Exchange>("store/exchange.xml", Exchange.class);
}
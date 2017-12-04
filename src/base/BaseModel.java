package base;

import lombok.*;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

abstract public class BaseModel implements Serializable {
    @Getter @Setter protected int id;

    public static BaseStore store;

    protected BaseStore getStore(){
        return BaseModel.store;
    }

    public void save(){
        this.getStore().Save(this);
    }
}

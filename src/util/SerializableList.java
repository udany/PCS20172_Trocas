package util;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * An XML serializable list wrapper
 * @param <T>
 */
@XmlRootElement(name = "list")
@XmlAccessorType(XmlAccessType.FIELD)
public class SerializableList<T> {
    @XmlElement(name = "item")
    public List<T> list;

    public SerializableList(){
        list = new ArrayList<>();
    }
    public SerializableList(List<T> list){
        this.list = list;
    }
}

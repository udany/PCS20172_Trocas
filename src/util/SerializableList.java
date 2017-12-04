package util;

import lombok.Getter;
import lombok.Setter;

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
    private List<T> list = new ArrayList<>();

    public List<T> getList() {
        return this.list;
    }

    public void setList(List<T> l){
        this.list = l;
    }

    public SerializableList(){
    }
    public SerializableList(List<T> list){
        this.list = list;
    }


    public int size(){
        return list.size();
    }

    public void add(T model){
        list.add(model);
    }

    public void remove(T model){
        list.remove(model);
    }
}

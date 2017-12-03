package base;

import util.SerializableList;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * An XML serializable list wrapper
 * @param <T>
 */
@XmlRootElement(name = "list")
@XmlAccessorType(XmlAccessType.FIELD)
public class ModelList<T extends BaseModel> extends SerializableList<T> {

    private BaseStore<T> store;

    @XmlTransient
    public List<T> models;

    private List<Integer> ids;

    public List<Integer> getIds(){
        return models.stream().map(x -> x.getId()).collect(Collectors.toList());
    }

    public void setIds(List<Integer> ids){
        this.models = ids.stream().map(x -> this.store.GetById(x)).collect(Collectors.toList());
    }

    public ModelList(){
        this(null, null);
    }

    public ModelList(BaseStore<T> store){
        this(store, null);
    }

    public ModelList(BaseStore<T> store, List<Integer> list){
        this.store = store;

        if (list != null) {
            this.setIds(list);
        }else{
            list = new ArrayList<>();
        }
    }
}

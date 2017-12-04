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
@XmlRootElement(name = "model-list")
@XmlAccessorType(XmlAccessType.FIELD)
public class ModelList<T extends BaseModel> extends SerializableList<Integer> {

    private BaseStore<T> store;

    @XmlTransient
    private List<T> models = new ArrayList<>();

    public List<T> getModels(){
        return models;
    }

    @Override
    public int size(){
        return models.size();
    }

    public void add(T model){
        models.add(model);
    }

    public void remove(T model){
        models.remove(model);
    }


    @XmlElement(name = "id")
    private List<Integer> list;

    public List<Integer> getList() {
        if (list != null) return  list;
        return getModels().stream().filter(x -> x != null).map(x -> x.getId()).collect(Collectors.toList());
    }
    public void setList(List<Integer> ids){
        models = ids.stream().map(x -> this.store.GetById(x)).collect(Collectors.toList());
    }


    /// Constructors
    public ModelList(){
        this(null, null);
    }

    public ModelList(BaseStore<T> store){
        this(store, null);
    }

    public ModelList(BaseStore<T> store, List<Integer> list){
        this.store = store;

        if (list != null) {
            this.setList(list);
        }else{
            models = new ArrayList<>();
        }
    }
}

package base;

import java.util.List;

public abstract class BaseStore<T extends BaseModel> {

    abstract public void Save(T obj);
    abstract public List<T> ListAll();
}
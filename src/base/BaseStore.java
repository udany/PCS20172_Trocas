package base;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class BaseStore<T extends BaseModel> {

    abstract public void Save(T obj);
    abstract public List<T> List();
    abstract public List<T> List(Predicate<T> filter);
    abstract public T GetById(int id);
}
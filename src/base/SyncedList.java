package base;

import util.Event;
import util.EventData;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SyncedList<E extends BaseModel> extends ArrayList<E> {
    private Predicate<E> query;
    private BaseStore store;
    public Event<EventData> onLoad = new Event<>();

    public SyncedList(BaseStore<E> eStore){
        this(null, eStore);
    }

    public SyncedList(Predicate<E> predicate, BaseStore<E> eStore){
        query = predicate;
        setStore(eStore);

        load();
    }

    public void setQuery(Predicate<E> predicate){
        query = predicate;
        load();
    }

    public void setStore(BaseStore<E> s){
        store = s;
        if (store != null){
            store.onChange.addListener(e -> load());
        }
        load();
    }

    private void load(){
        if (this.store == null) return;

        List<E> results;
        if (this.query == null){
            results = store.List();
        }else{
            results = store.List(query);
        }

        clear();
        addAll(results);
        onLoad.emit();
    }

    @Override
    public boolean add(E el){
        return false;
    }

    @Override
    public E set(int index, E el){
        return null;
    }

    @Override
    public E remove(int index){
        return null;
    }
}

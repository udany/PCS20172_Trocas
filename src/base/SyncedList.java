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

    public SyncedList(Predicate<E> predicate, BaseStore<E> eStore){
        query = predicate;
        store = eStore;

        load();
        store.onChange.addListener(e -> load());
    }

    private void load(){
        List<E> results = store.List(query);
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

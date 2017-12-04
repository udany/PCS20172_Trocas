package base;

import model.Counter;

import javax.swing.*;
import java.util.function.Predicate;

public class SyncedListModel<E extends BaseModel> extends AbstractListModel<E> implements ComboBoxModel<E> {
    private SyncedList<E> list;

    public SyncedListModel(BaseStore<E> eStore){
        this(null, eStore);
    }

    public SyncedListModel(Predicate<E> predicate, BaseStore<E> eStore){
        list = new SyncedList<>(predicate, eStore);

        list.onLoad.addListener(e -> fireContentsChanged(this, 0, this.getSize()));
    }

    public void setQuery(Predicate<E> predicate){
        list.setQuery(predicate);
    }

    public void setStore(BaseStore<E> store){
        list.setStore(store);
    }

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public E getElementAt(int index) {
        return list.get(index);
    }

    private E selected;
    @Override
    public void setSelectedItem(Object anItem) {
        selected = (E)anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selected;
    }
}

package base;

import model.Counter;

import javax.swing.*;
import java.util.function.Predicate;

public class SyncedListModel<E extends BaseModel> extends AbstractListModel<E> {
    private SyncedList<E> list;

    public SyncedListModel(Predicate<E> predicate, BaseStore<E> eStore){
        list = new SyncedList<>(predicate, eStore);

        list.onLoad.addListener(e -> fireContentsChanged(this, 0, this.getSize()));
    }

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public E getElementAt(int index) {
        return list.get(index);
    }
}

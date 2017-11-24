package base;

import util.SerializableList;
import util.XmlSerializer;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class XmlStore<T extends BaseModel> extends BaseStore<T> {
    protected Class model;
    protected String file;
    protected ArrayList<Class> classes;

    protected List<T> data;

    protected long lastRead = 0;


    public XmlStore(String file, Class model, Class... classes){
        this.file = file;
        this.model = model;
        this.data = new ArrayList<>();

        this.classes = new ArrayList<>(Arrays.asList(classes));

        if (this.classes.indexOf(model) == -1){
            this.classes.add(model);
        }

        this.classes.add(SerializableList.class);
        this.classes.add(ModelList.class);
    }

    public XmlStore(String file, Class model){
        this(file, model, model);
    }

    public void Save(T obj){
        this.Load();

        if (obj.getId() == 0){
            int lastId = GetLastId();
            obj.setId(lastId+1);
            data.add(obj);
        }else{
            //TODO: Check if it exists within the list else check if id exists, if so replace, else set id to lastId
        }

        SaveAll(data);
    }

    public int GetLastId(){
        if (data.size() == 0) return 0;

        T lastObj = data.get(data.size()-1);
        return lastObj.getId();
    }

    protected void SaveAll(List<T> collection) {
        for (T item : collection){
            if (item.getId() == 0) item.setId(collection.indexOf(item)+1);
        }

        SerializableList l = new SerializableList(collection);

        XmlSerializer.save(l, file, classes.toArray(new Class[classes.size()]));

        data = collection;
        lastRead = GetLastWrite();
    }

    private void Load(){
        long lastWrite = GetLastWrite();
        if (lastWrite <= lastRead) return;

        SerializableList<T> data = XmlSerializer.load(file, classes.toArray(new Class[classes.size()]));

        if (data!=null && data.list != null) this.data = data.list;
        lastRead = GetLastWrite();
    }

    public List<T> List(){
        this.Load();

        return data;
    }

    public List<T> List(Predicate<T> filter){
        this.Load();

        return data.stream().filter(filter).collect(Collectors.toList());
    }

    public T GetById(int id) {
        List<T> r = List(x -> x.getId() == id);
        return r.size() > 0 ? r.get(0) : null;
    }

    private long GetLastWrite(){
        File store = new File(file);
        if (!store.exists()){
            try {
                store.createNewFile();
            }catch (Exception e){
                System.out.println("Error: "+e.getMessage());
                return 0;
            }
        }

        return store.lastModified();
    }
}

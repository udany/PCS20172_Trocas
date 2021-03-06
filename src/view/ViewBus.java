package view;

import util.MyFrame;

import java.lang.reflect.Constructor;
import java.util.HashMap;

public class ViewBus {
    private HashMap<String, MyFrame> screens;

    private ViewBus(){
        screens = new HashMap<>();
    }
    private static ViewBus instance;
    public static ViewBus get() {
        if (instance == null) instance = new ViewBus();
        return instance;
    }

    public MyFrame get(Class c){
        if (MyFrame.class.isAssignableFrom(c)){
            String name = c.getName();
            MyFrame screen = screens.get(name);

            if (screen==null){
                try{
                    Constructor<MyFrame> cons = c.getConstructor();
                    screen = cons.newInstance();
                    screens.put(name, screen);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }

            return screen;
        }

        return null;
    }
    public MyFrame open(Class c, Object... data){
        MyFrame screen = get(c);

        if (screen!= null) screen.open(data);

        return screen;
    }
}

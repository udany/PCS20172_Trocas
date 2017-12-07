package main;

import base.ModelList;
import model.User;
import model.UserGroup;
import model.enums.Permission;
import util.SerializableList;
import view.Login;
import view.ViewBus;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        setup();

        ViewBus.get().open(Login.class);
    }

    static void setup(){
        populateUserGrups();
        createAdmin();
    }

    private static void populateUserGrups(){
        if (UserGroup.store.List().size() == 0){
            UserGroup.store.Save(UserGroup.builder()
                    .name("Admin")
                    .permissions(new SerializableList<>(Arrays.asList(Permission.values())))
                    .build());

            UserGroup.store.Save(UserGroup.builder().name("Mod").build());
            UserGroup.store.Save(UserGroup.builder().name("User").build());
        }
    }

    private static void createAdmin(){
        if (User.store.List().size() == 0) {
            User.store.Save(User.builder()
                    .name("Admin")
                    .email("admin")
                    .password("admin")
                    .groups(new ModelList<>(
                            UserGroup.store,
                            Arrays.asList(1)))
                    .build());
        }
    }
}

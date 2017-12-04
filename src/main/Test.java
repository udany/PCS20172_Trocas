/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controller.AuthController;
import model.User;
import model.UserGroup;
import view.Home;
import view.ViewBus;

/**
 *
 * @author labccet
 */
public class Test {
    
    public static void main(String[] args) {
        //ViewBus.get().open(Signup.class);

        AuthController.auth(User.builder().email("d").password("123456").build());
        ViewBus.get().open(Home.class);
    }

    private static void createUserGroups(){
        UserGroup.store.Save(UserGroup.builder().name("Admin").build());
        UserGroup.store.Save(UserGroup.builder().name("Mod").build());
        UserGroup.store.Save(UserGroup.builder().name("User").build());
    }
}

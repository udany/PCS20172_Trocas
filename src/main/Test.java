/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import base.ModelList;
import controller.AuthController;
import javafx.stage.Screen;
import model.User;
import model.UserGroup;
import sun.rmi.runtime.Log;
import view.Home;
import view.Login;
import view.LoginScreen;
import view.ViewBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author labccet
 */
public class Test {
    
    public static void main(String[] args) {
        //ViewBus.get().open(Login.class);

        AuthController.auth(User.builder().email("d").password("123456").build());
        ViewBus.get().open(Home.class);
    }


    private static void testXml(){
        System.out.println("==Save to XML==");

//        User.store.Save(
//                User.builder()
//                        .name("Daniel Andrade")
//                        .email("daniel.oandrade@uniriotec.br")
//                        .birthday(new Date())
//                        .password("TRY")
//                        .build());

//        UserGroup.store.Save(UserGroup.builder().name("Admin").build());
//        UserGroup.store.Save(UserGroup.builder().name("User").build());

//        List<User> studentsLoaded = User.store.List(x -> x.getId() >= 2);

        ModelList<User> list = new ModelList<User>(User.store, new ArrayList<Integer>(Arrays.asList(1,3)));

        for (User s : list.models)
            System.out.println(s.getName()+" - "+s.getId());
    }
}

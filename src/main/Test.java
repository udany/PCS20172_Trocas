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
        Main.setup();

        AuthController.auth(User.builder().email("admin").password("admin").build());
        ViewBus.get().open(Home.class);
    }
}

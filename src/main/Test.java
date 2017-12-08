/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controller.AuthController;
import model.Product;
import model.User;
import view.Home;
import view.SearchResults;
import view.ViewBus;

/**
 *
 * @author labccet
 */
public class Test {
    
    public static void main(String[] args) {
        Main.setup();

        AuthController.auth(User.builder().email("admin").password("admin").build());
        //ViewBus.get().open(Home.class);
        ViewBus.get().open(SearchResults.class, Product.store.List());
    }
}

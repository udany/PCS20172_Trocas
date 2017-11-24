/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import model.User;

import java.util.List;

/**
 *
 * @author labccet
 */
public class Test {
    
    public static void main(String[] args) {
        System.out.println("==Save to XML==");

        User.store.Save(
                User.make()
                        .fullName("2Eustáquio da Rocha")
                        .telephone("246574654")
                        .address("Rua Whatever")
                        .build());

        User.store.Save(
                User.make()
                        .fullName("Paula Tejano")
                        .telephone("5646546456")
                        .build());

        User.store.Save(
                User.make()
                        .fullName("Honório Aquino Rêgo")
                        .telephone("7987851")
                        .build());

        List<User> studentsLoaded = User.store.ListAll();

        for (User s : studentsLoaded)
            System.out.println(s.getFullName()+" - "+s.getTelephone());
    }
}

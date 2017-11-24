/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import model.User;

import java.util.Date;
import java.util.List;

/**
 *
 * @author labccet
 */
public class Test {
    
    public static void main(String[] args) {
        System.out.println("==Save to XML==");

        User.store.Save(
                User.builder()
                        .name("Daniel Andrade")
                        .email("daniel.oandrade@uniriotec.br")
                        .birthday(new Date())
                        .password("TRY")
                        .build());

        List<User> studentsLoaded = User.store.ListAll();

        for (User s : studentsLoaded)
            System.out.println(s.getName()+" - "+s.getId());
    }
}

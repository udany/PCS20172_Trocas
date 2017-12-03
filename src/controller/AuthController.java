package controller;

import model.User;

import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

public class AuthController {
    public static boolean auth(User u){
        List<User> actual = User.store.List(x -> x.getEmail().equals(u.getEmail()));

        if (actual.size() > 0) {
            User actualUser = actual.get(0);
            if (actualUser.getPassword().equals(u.getPassword())) {
                return true;
            }
        }

        return false;
    }
}

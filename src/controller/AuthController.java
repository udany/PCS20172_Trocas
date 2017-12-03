package controller;

import lombok.Getter;
import model.User;

import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

public class AuthController {
    @Getter private static User currentUser;

    public static boolean auth(User u){
        List<User> actual = User.store.List(x -> x.getEmail().equals(u.getEmail()));

        if (actual.size() > 0) {
            User actualUser = actual.get(0);
            if (actualUser.getPassword().equals(u.getPassword())) {
                currentUser = actualUser;
                return true;
            }
        }

        return false;
    }
}

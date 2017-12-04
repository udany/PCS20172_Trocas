package controller;

import lombok.Getter;
import model.User;
import model.UserGroup;

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

    public static boolean register(User user) throws Exception{
        //// VALIDATION
        Boolean userExists = User.store.List(x -> x.getEmail().equals(user.getEmail())).size() > 0;
        if (userExists) {
            throw new Exception("Já existe um usuário com esse email!");
        }

        if (user.getGroups().size() == 0){
            user.getGroups().add(UserGroup.store.GetById(UserGroup.defaultUserGroup));
        }

        User.store.Save(user);

        return true;
    }
}

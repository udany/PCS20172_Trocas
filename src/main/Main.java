package main;

import view.Login;
import view.ViewBus;

public class Main {

    public static void main(String[] args) {
        ViewBus.get().open(Login.class);
    }

    private void checkStoreExists(){
    }
}

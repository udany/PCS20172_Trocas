package model;

import lombok.Getter;

public enum Permission {
    CategoryManagement("Gerenciar Categorias");

    @Getter private String label;
    Permission(String l){
        label = l;
    }
}

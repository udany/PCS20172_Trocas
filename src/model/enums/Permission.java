package model.enums;

import lombok.Getter;

public enum Permission {
    CategoryManagement("Gerenciar Categorias"),
    UserManagement("Gerenciar Usuários");

    @Getter private String label;
    Permission(String l){
        label = l;
    }
}

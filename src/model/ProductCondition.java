package model;

import lombok.Getter;

public enum  ProductCondition {
    Prime(1, "Perfeito Estado"),
    Used(2, "Usado"),
    Damaged(3, "Danificado"),
    Wrecked(4, "Destruído");

    int value;
    @Getter String label;
    ProductCondition(int val, String l){
        value = val;
        label = l;
    }
}

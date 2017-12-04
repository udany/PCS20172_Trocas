package model.enums;

public enum Region {
    North("Norte"),
    NorthEast("Nordeste"),
    CenterWest("Centroeste"),
    SouthEast("Sudeste"),
    South("Sul");

    String name;

    Region(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

package com.test.api;

public class Personnage {
    private int id;
    private String name;

    private Type type;

    private int lifePoint;

    public Personnage(int id, String name, Type type, int lifePoint) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.lifePoint = lifePoint;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getLifePoint() {
        return lifePoint;
    }

    public void setLifePoint(int lifePoint) {
        this.lifePoint = lifePoint;
    }
}

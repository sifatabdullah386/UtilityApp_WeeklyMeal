package com.smilesifat.weeklymeal.model;

public class FavouriteModel {

    public int id;
    public String meal_type_id;
    public String name;

    public FavouriteModel(int id, String meal_type_id, String name) {
        this.id = id;
        this.meal_type_id = meal_type_id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMeal_type_id() {
        return meal_type_id;
    }

    public void setMeal_type_id(String meal_type_id) {
        this.meal_type_id = meal_type_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

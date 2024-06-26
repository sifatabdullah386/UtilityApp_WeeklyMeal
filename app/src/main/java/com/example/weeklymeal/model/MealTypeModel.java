package com.example.weeklymeal.model;

public class MealTypeModel {

    public int id;
    public String week_id;
    public String name;
    public String icon;

    public MealTypeModel(int id, String week_id, String name, String icon) {
        this.id = id;
        this.week_id = week_id;
        this.name = name;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeek_id() {
        return week_id;
    }

    public void setWeek_id(String week_id) {
        this.week_id = week_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
package com.example.testapp;

public class Food {
    private String food;
    private String gm;
    private String unit;
    private String value;

    public Food() {
    }

    public Food(String food, String gm, String unit, String value) {
        this.food = food;
        this.gm = gm;
        this.unit = unit;
        this.value = value;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getGm() {
        return gm;
    }

    public void setGm(String gm) {
        this.gm = gm;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

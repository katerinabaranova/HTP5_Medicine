package com.baranova.drug.entity;


public class AdultMedicine extends  Medicine{

    private boolean alcoholAllowed;

    public boolean isAlcoholAllowed() {return alcoholAllowed;}
    public void setAlcoholAllowed(boolean alcoholAllowed) {this.alcoholAllowed = alcoholAllowed;}
}

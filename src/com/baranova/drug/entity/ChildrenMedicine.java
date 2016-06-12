package com.baranova.drug.entity;

public class ChildrenMedicine extends Medicine{

    private int ageFrom;

    public int getAgeFrom() {return ageFrom;}
    public void setAgeFrom(int ageFrom) {this.ageFrom = ageFrom;}

    @Override
    public String toString() {
        return "ChildrenMedicine{" +
                "name='" + super.getName() + '\'' +
                ", group=" + super.getGroup() +
                ", analogs=" + super.getAnalogs() +
                ", versions=" + super.getVersion() +
                ", ageFrom=" + ageFrom +
                '}';
    }
}

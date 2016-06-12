package com.baranova.drug.entity;

public class ChildrenMedicine extends Medicine{

    private int ageFrom;

    public int getAgeFrom() {return ageFrom;}
    public void setAgeFrom(int ageFrom) {this.ageFrom = ageFrom;}

    @Override
    public String toString() {
        StringBuilder childMed=new StringBuilder("ChildrenMedicine{");
        childMed.append("name='").append(super.getName());
        childMed.append(", group=").append(super.getGroup());
        childMed.append(", analogs=").append(super.getAnalogs());
        childMed.append(", ageFrom=").append(ageFrom).append(",").append("\n");
        childMed.append("\t\t versions=").append(super.getVersion());
        return childMed.toString();
    }
}

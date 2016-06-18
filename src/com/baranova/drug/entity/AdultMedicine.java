package com.baranova.drug.entity;


public class AdultMedicine extends  Medicine{

    private boolean alcoholAllowed;

    public boolean isAlcoholAllowed() {return alcoholAllowed;}
    public void setAlcoholAllowed(boolean alcoholAllowed) {this.alcoholAllowed = alcoholAllowed;}

    @Override
    public String toString() {
        StringBuilder adultMed=new StringBuilder("AdultMedicine{");
        adultMed.append("name='").append(super.getName());
        adultMed.append(", id=").append(getMedicineId());
        adultMed.append(", group=").append(super.getGroup());
        adultMed.append(", analogs=").append(super.getAnalogs());
        adultMed.append(", alcohol allowed=").append(alcoholAllowed).append(",").append("\n");
        adultMed.append("\t\t versions=").append(super.getVersion());
        return adultMed.toString();
    }

}

package com.baranova.drug.entity;


public class MedicinsPackage {

    private String packageType;
    private int quantity;
    private int price;

    @Override
    public String toString() {
        return "MedicinsPackage{" +
                "packageType='" + packageType + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}

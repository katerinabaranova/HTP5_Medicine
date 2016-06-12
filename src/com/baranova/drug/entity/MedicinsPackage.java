package com.baranova.drug.entity;


public class MedicinsPackage {

    private String packageType;
    private int quantity;
    private int price;

    public String getPackageType() {return packageType;}
    public int getQuantity() {return quantity;}
    public int getPrice() {return price;}

    public void setPackageType(String packageType) {this.packageType = packageType;}
    public void setQuantity(int quantity) {this.quantity = quantity;}
    public void setPrice(int price) {this.price = price;}

    @Override
    public String toString() {
        StringBuilder medicinsPack=new StringBuilder("MedicinsPackage{");
        medicinsPack.append("packageType=").append(packageType);
        medicinsPack.append(", quantity=").append(quantity);
        medicinsPack.append(", price=").append(price).append("}");

        return medicinsPack.toString();
    }
}

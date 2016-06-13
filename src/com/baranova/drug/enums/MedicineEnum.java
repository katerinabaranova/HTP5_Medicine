package com.baranova.drug.enums;

public enum MedicineEnum {
    ALLMEDICINE("allmedicine"),
    NAME("name"),
    ID("id"),
    CHILDREN_MEDICINE("children_medicine"),
    ADULT_MEDICINE("adult_medicine"),
    GROUP("group"),
    ANALOG("analog"),
    VERSION("version"),
    VERSION_NAME("version_name"),
    PRODUCERS("producers"),
    MEDICINS_PACKAGE("medicins_package"),
    PACKAGE_TYPE("package-type"),
    QUANTITY("quantity"),
    PRICE("price"),
    DOSAGE("dosage"),
    PRESCRIBED_AMOUNT("prescribed-amount"),
    FREQUENCY("frequency"),
    AGE_FROM("age-from"),
    ALCOHOL_ALLOWED("alcohol-allowed");

    private String value;
    private MedicineEnum(String value) {this.value = value;}
    public String getValue() {return value;}

}


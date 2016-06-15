package com.baranova.drug.enums;

public enum MedicineEnum {
    ALLMEDICINE("allmedicine"),
    VERSION("version"),
    CHILDREN_MEDICINE("children_medicine"),
    ADULT_MEDICINE("adult_medicine"),
    MEDICINS_PACKAGE("medicins_package"),
    DOSAGE("dosage"),
    NAME("name"),
    ID("id"),
    GROUP("group"),
    ANALOG("analog"),
    VERSION_NAME("version_name"),
    PRODUCERS("producers"),
    PACKAGE_TYPE("package-type"),
    QUANTITY("quantity"),
    PRICE("price"),
    PRESCRIBED_AMOUNT("prescribed-amount"),
    FREQUENCY("frequency"),
    AGE_FROM("age-from"),
    ALCOHOL_ALLOWED("alcohol-allowed");

    private String value;
    private MedicineEnum(String value) {this.value = value;}
    public String getValue() {return value;}

}


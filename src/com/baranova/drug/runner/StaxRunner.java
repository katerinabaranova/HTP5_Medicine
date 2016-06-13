package com.baranova.drug.runner;


import com.baranova.drug.entity.Medicine;
import com.baranova.drug.stax.MedicineStaxBuilder;

import java.util.Set;

public class StaxRunner {
    public static void main (String[] args) {
        MedicineStaxBuilder staxBuilder = new MedicineStaxBuilder();
        staxBuilder.buildSetMedicine("data/medicins.xml");
        Set<Medicine> allMedicine = staxBuilder.getAllMedicine();
        for (Medicine medicine : allMedicine) {
            System.out.println(medicine);
        }
    }
}

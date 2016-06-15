package com.baranova.drug.runner;


import com.baranova.drug.creation.AbstractMedicineBuilder;
import com.baranova.drug.creation.MedicineBuilderFactory;
import com.baranova.drug.dom.MedicineDomBuilder;
import com.baranova.drug.entity.Medicine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class Runner {
    static final Logger LOG= LogManager.getLogger();

    public static void main (String[] args){
        MedicineBuilderFactory medicineBuilderFactory = new MedicineBuilderFactory();
        AbstractMedicineBuilder builder = medicineBuilderFactory.createMedicineBuilder("dom");
        builder.buildSetAllMedicine("data/medicins.xml");
        Set<Medicine> allMedicine=builder.getAllMedicine();
        for (Medicine medicine:allMedicine){
            System.out.println(medicine);
        }
    }
}

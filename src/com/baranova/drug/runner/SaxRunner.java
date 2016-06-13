package com.baranova.drug.runner;


import com.baranova.drug.entity.Medicine;
import com.baranova.drug.sax.MedicineSaxBuilder;

import java.util.Set;

public class SaxRunner {
    public static void main (String[] args){
        MedicineSaxBuilder saxBuilder = new MedicineSaxBuilder();
        saxBuilder.buildSetAllmedicine("data/medicins.xml");
        Set<Medicine> allMedicine=saxBuilder.getAllmedicine();
        for (Medicine medicine:allMedicine) {
            System.out.println(medicine);
        }
    }

}


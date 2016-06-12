package com.baranova.drug.runner;


import com.baranova.drug.dom.MedicineDomBuilder;
import com.baranova.drug.entity.Medicine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class DomRunner {
    static final Logger LOG= LogManager.getLogger();

    public static void main (String[] args){
        MedicineDomBuilder domBuilder=new MedicineDomBuilder();
        domBuilder.buildSetAllmedicine("data/medicins.xml");
        Set<Medicine> allMedicine=domBuilder.getAllmedicine();
        for (Medicine medicine:allMedicine){
            System.out.println(medicine);
        }
    }
}

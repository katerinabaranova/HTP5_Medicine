package com.baranova.drug.runner;


import com.baranova.drug.sax.MedicineSaxBuilder;

public class SaxRunner {
    public static void main (String[] args){
        MedicineSaxBuilder saxBuilder = new MedicineSaxBuilder();
        saxBuilder.buildSetAllmedicine("data/medicins.xml");
        System.out.println(saxBuilder.getAllmedicine());

    }

}


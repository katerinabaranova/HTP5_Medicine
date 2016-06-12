package com.baranova.drug.runner;


import com.baranova.drug.dom.MedicineDomBuilder;

public class DomRunner {

    public static void main (String[] args){
        MedicineDomBuilder domBuilder=new MedicineDomBuilder();
        domBuilder.buildSetAllmedicine("data/medicins.xml");
        System.out.println(domBuilder.getAllmedicine());
    }
}

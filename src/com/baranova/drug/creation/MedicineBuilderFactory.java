package com.baranova.drug.creation;


import com.baranova.drug.dom.MedicineDomBuilder;
import com.baranova.drug.sax.MedicineSaxBuilder;
import com.baranova.drug.stax.MedicineStaxBuilder;

public class MedicineBuilderFactory {

    private enum TypeParser{
        DOM, STAX,SAX;
    }

    public AbstractMedicineBuilder createMedicineBuilder(String typeParser){
        TypeParser type=TypeParser.valueOf(typeParser.toUpperCase());
        switch (type){
            case DOM:
                return new MedicineDomBuilder();
            case STAX:
                return new MedicineStaxBuilder();
            case SAX:
                return new MedicineSaxBuilder();
            default:
                throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        }
    }
}

package com.baranova.drug.stax;


import com.baranova.drug.creation.AbstractMedicineBuilder;
import com.baranova.drug.entity.*;
import com.baranova.drug.enums.MedicineEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class MedicineStaxBuilder extends AbstractMedicineBuilder {
    static final Logger LOG= LogManager.getLogger();
    private HashSet <Medicine> allMedicine =new HashSet<>();
    private XMLInputFactory inputFactory;

    public MedicineStaxBuilder(){
        inputFactory=XMLInputFactory.newInstance();
    }

    @Override
    public Set<Medicine> getAllMedicine(){
        return allMedicine;
    }

    @Override
    public void buildSetAllMedicine(String filename){
        FileInputStream inputStream=null;
        XMLStreamReader reader=null;
        String name;
        try {
            inputStream=new FileInputStream(new File(filename));
            reader=inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()){
                int type=reader.next();
                if (type== XMLStreamConstants.START_ELEMENT){
                    name=reader.getLocalName();
                    if (MedicineEnum.valueOf(name.toUpperCase())==MedicineEnum.CHILDREN_MEDICINE){
                        Medicine childMedicine=buildChildMedicine(reader);
                        allMedicine.add(childMedicine);
                    }
                    if (MedicineEnum.valueOf(name.toUpperCase())==MedicineEnum.ADULT_MEDICINE){
                        Medicine adultMedicine=buildAdultMedicine(reader);
                        allMedicine.add(adultMedicine);
                    }
                }
            }
        } catch (XMLStreamException e){
            LOG.error("Stax parser error"+e);
        } catch (FileNotFoundException e){
            LOG.error("File doesn't find");
        } finally {
            try{
                if (inputStream!=null){
                    inputStream.close();
                }
            } catch (IOException e){
                LOG.error("Closing  filereader is impossible " +e);
            }
        }
    }

    private Medicine buildChildMedicine(XMLStreamReader reader) throws XMLStreamException{
        System.out.println("stax");
        ChildrenMedicine childrenMedicine=new ChildrenMedicine();
        childrenMedicine.setName(reader.getAttributeValue(null,MedicineEnum.NAME.getValue()));
        childrenMedicine.setMedicineId(reader.getAttributeValue(null,MedicineEnum.ID.getValue()));
        String name;
        while (reader.hasNext()){
            int type=reader.next();
            switch (type){
                case XMLStreamConstants.START_ELEMENT:
                    name=reader.getLocalName();
                    switch (MedicineEnum.valueOf(name.toUpperCase())){
                        case GROUP:
                            childrenMedicine.setGroup(getXMLText(reader));
                            break;
                        case ANALOG:
                            childrenMedicine.getAnalogs().add(getXMLText(reader));
                            break;
                        case VERSION:
                            childrenMedicine.setVersion(getXMLVersion(reader));
                            break;
                        case AGE_FROM:
                            childrenMedicine.setAgeFrom(Integer.parseInt(getXMLText(reader)));
                            break;
                    }
                break;
                case XMLStreamConstants.END_ELEMENT:
                    name=reader.getLocalName();
                    if (MedicineEnum.valueOf(name.toUpperCase()) == MedicineEnum.CHILDREN_MEDICINE) {
                        return childrenMedicine;
                    }
                    break;
            }
        }
        throw new  XMLStreamException("Unknown element in tag Child Medicine");
    }

    private Medicine buildAdultMedicine(XMLStreamReader reader) throws XMLStreamException{
        AdultMedicine adultMedicine=new AdultMedicine();
        adultMedicine.setName(reader.getAttributeValue(null,MedicineEnum.NAME.getValue()));
        adultMedicine.setMedicineId(reader.getAttributeValue(null,MedicineEnum.ID.getValue()));
        String name;
        while (reader.hasNext()){
            int type=reader.next();
            switch (type){
                case XMLStreamConstants.START_ELEMENT:
                    name=reader.getLocalName();
                    switch (MedicineEnum.valueOf(name.toUpperCase())){
                        case GROUP:
                            adultMedicine.setGroup(getXMLText(reader));
                            break;
                        case ANALOG:
                            adultMedicine.getAnalogs().add(getXMLText(reader));
                            break;
                        case VERSION:
                            adultMedicine.setVersion(getXMLVersion(reader));
                            break;
                        case ALCOHOL_ALLOWED:
                            adultMedicine.setAlcoholAllowed(Boolean.parseBoolean(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name=reader.getLocalName();
                    if (MedicineEnum.valueOf(name.toUpperCase()) == MedicineEnum.ADULT_MEDICINE) {
                        return adultMedicine;
                    }
                    break;
            }
        }
        throw new  XMLStreamException("Unknown element in tag Child Medicine");
    }

    private Version getXMLVersion(XMLStreamReader reader) throws XMLStreamException {
        Version version = new Version();
        int type;
        String name;
        while (reader.hasNext()) {
            type=reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name=reader.getLocalName();
                    switch (MedicineEnum.valueOf(name.toUpperCase())) {
                        case VERSION_NAME:
                            version.setVersionName(getXMLText(reader));
                            break;
                        case PRODUCERS:
                            version.getProducers().add(getXMLText(reader));
                            break;
                        case MEDICINS_PACKAGE:
                            version.setMedicinsPackage(getXMLPackage(reader));
                            break;
                        case DOSAGE:
                            version.setDosage(getXMLDosage(reader));
                            break;

                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name  = reader.getLocalName();
                    if (MedicineEnum.valueOf(name.toUpperCase()) == MedicineEnum.VERSION){
                        return version;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Version");
    }

    private MedicinsPackage getXMLPackage(XMLStreamReader reader) throws XMLStreamException {
        MedicinsPackage medicinsPackage = new MedicinsPackage();
        int type;
        String name="";
        while (reader.hasNext()) {
            type=reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name=reader.getLocalName();
                    switch (MedicineEnum.valueOf(name.toUpperCase())) {
                        case PACKAGE_TYPE:
                            medicinsPackage.setPackageType(getXMLText(reader));
                            break;
                        case QUANTITY:
                            medicinsPackage.setQuantity(Integer.parseInt(getXMLText(reader)));
                            break;
                        case PRICE:
                            medicinsPackage.setPrice(Integer.parseInt(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name  = reader.getLocalName();
                    if (MedicineEnum.valueOf(name.toUpperCase()) == MedicineEnum.MEDICINS_PACKAGE){
                        return medicinsPackage;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Medicins Package"+name);
    }

    private Dosage getXMLDosage(XMLStreamReader reader) throws XMLStreamException {
        Dosage dosage = new Dosage();
        int type;
        String name="";
        while (reader.hasNext()) {
            type=reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name=reader.getLocalName();
                    switch (MedicineEnum.valueOf(name.toUpperCase())) {
                        case PRESCRIBED_AMOUNT:
                            dosage.setPrescribedAmount(Integer.parseInt(getXMLText(reader)));
                            break;
                        case FREQUENCY:
                            dosage.setFrequency(Integer.parseInt(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name  = reader.getLocalName();
                    if (MedicineEnum.valueOf(name.toUpperCase()) == MedicineEnum.DOSAGE){
                        return dosage;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Dpsage "+name);
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text =reader.getText();
        }
        return text;
    }


}

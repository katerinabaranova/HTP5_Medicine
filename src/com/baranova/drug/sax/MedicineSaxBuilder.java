package com.baranova.drug.sax;


import com.baranova.drug.entity.Medicine;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Set;

public class MedicineSaxBuilder {

    private Set<Medicine> allmedicine;
    private MedicineHandler medicineHandler;
    private XMLReader reader;
    public MedicineSaxBuilder() {
        // создание SAX-анализатора
        medicineHandler= new MedicineHandler();
        try
        {// создание объекта-обработчика
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(medicineHandler);
        }
        catch (SAXException e) {
            System.err.print("ошибка SAX парсера: " + e);
        }
    }

    public Set<Medicine> getAllmedicine() {
        return allmedicine;
    }

    public void buildSetAllmedicine(String fileName) {
        try
        {// разбор XML-документа
            reader.parse(fileName);
        }
        catch (SAXException e) {
            System.err.print("ошибка SAX парсера: " + e);
        }
        catch (IOException e) {
            System.err.print("ошибка I/О потока: " + e);
        }
        allmedicine = medicineHandler.getAllmedicine();
    }
}


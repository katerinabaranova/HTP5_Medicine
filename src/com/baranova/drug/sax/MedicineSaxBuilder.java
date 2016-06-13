package com.baranova.drug.sax;


import com.baranova.drug.entity.Medicine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Set;

public class MedicineSaxBuilder {
    static final Logger LOG= LogManager.getLogger();
    private Set<Medicine> allmedicine;
    private MedicineHandler medicineHandler;
    private XMLReader reader;
    public MedicineSaxBuilder() {
        medicineHandler= new MedicineHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(medicineHandler);
        }
        catch (SAXException e) {
            LOG.error("Error of SAX-parser "+e);
        }
    }

    public Set<Medicine> getAllmedicine() {
        return allmedicine;
    }

    public void buildSetAllmedicine(String fileName) {
        try {
            reader.parse(fileName);
        }
        catch (SAXException e) {
            LOG.error("Error of SAX-parser "+e);
        }
        catch (IOException e) {
            LOG.error("Error IO");
        }
        allmedicine = medicineHandler.getAllmedicine();
    }
}


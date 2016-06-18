package com.baranova.drug.validation;


import com.baranova.drug.constant.FileConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class ValidatorSAXXSD {
    static final Logger LOG= LogManager.getLogger();

    public static void main(String[ ] args) {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        String fileName = FileConstant.MedicineXML_FILE;
        String schemaName = FileConstant.MedicineSchema_File;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(schemaName);
        try {
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(fileName);
            validator.validate(source);
            System.out.println(fileName + " is valid.");
        }
        catch (SAXException e) {
            LOG.error("validation "+ fileName + " is not valid because " + e.getMessage());
        }
        catch (IOException e) {
            LOG.error(fileName + " is not valid because " + e.getMessage());
        }
    }
}



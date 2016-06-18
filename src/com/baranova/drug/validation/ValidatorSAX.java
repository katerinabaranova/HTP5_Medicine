package com.baranova.drug.validation;

import com.baranova.drug.constant.FileConstant;
import com.baranova.drug.exception.MedicineErrorHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

public class ValidatorSAX {
    static final Logger LOG= LogManager.getLogger();

    public static void main(String[] args){

        String filename = FileConstant.MEDICINE_XML_FILE;
        String schemaname =FileConstant.MEDICINE_SCHEMA_FILE;
        String logname = FileConstant.LOG_FILE;
        Schema schema = null;
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);

        try {
            schema=factory.newSchema(new File(schemaname));
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setSchema(schema);
            SAXParser parser = spf.newSAXParser();
            parser.parse(filename, new MedicineErrorHandler(logname));
            System.out.println(filename + " is valid");
        } catch (ParserConfigurationException e) {
            LOG.error(filename + " config error: " + e.getMessage());
        } catch (SAXException e) {
            LOG.error(filename + " SAX error: " + e.getMessage());
        } catch (IOException e) {
            LOG.error("I/O error: " + e.getMessage());
        }
    }
}



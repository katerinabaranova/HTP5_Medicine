package com.baranova.drug.dom;

import com.baranova.drug.creation.AbstractMedicineBuilder;
import com.baranova.drug.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class MedicineDomBuilder extends AbstractMedicineBuilder{
    static final Logger LOG= LogManager.getLogger();
    private Set<Medicine> allMedicine;
    private DocumentBuilder docBuilder;

    public MedicineDomBuilder() {
        this.allMedicine = new HashSet<Medicine>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder=factory.newDocumentBuilder();
        }
        catch (ParserConfigurationException e) {
            LOG.error("Parsing configuration error: " + e);
        }
    }

    public Set<Medicine> getAllMedicine() {
            return allMedicine;
        }

    public void buildSetAllMedicine(String fileName) {
        Document doc = null;
        try {
            doc = docBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            NodeList childMedicineList = root.getElementsByTagName("ns:children_medicine");
            for (int i = 0; i < childMedicineList.getLength(); i++) {
                Element medicineElement = (Element) childMedicineList.item(i);
                ChildrenMedicine childrenMedicine=buildChildrenMedicine(medicineElement);
                allMedicine.add(childrenMedicine);
            }

            NodeList adultMedicineList = root.getElementsByTagName("ns:adult_medicine");
            for (int i = 0; i < adultMedicineList.getLength(); i++) {
                Element medicineElement = (Element) adultMedicineList.item(i);
                AdultMedicine adultMedicine=buildAdultMedicine(medicineElement);
                allMedicine.add(adultMedicine);
            }
        }
        catch (IOException e) {
            LOG.error("File error or I/O error: " + e);
        }
        catch (SAXException e) {
            LOG.error("Parsing failure: " + e);
        }
    }

    private ChildrenMedicine buildChildrenMedicine(Element medicineElement) {
        ChildrenMedicine childrenMedicine = new ChildrenMedicine();
        childrenMedicine.setName(medicineElement.getAttribute("name"));
        childrenMedicine.setMedicineId(medicineElement.getAttribute("id"));
        childrenMedicine.setGroup(getElementTextContent(medicineElement, "ns:group"));
        NodeList analogsList=medicineElement.getElementsByTagName("ns:analog");
        for (int i=0;i<analogsList.getLength();i++){
            Node node=analogsList.item(i);
            String text = node.getTextContent();
            childrenMedicine.getAnalogs().add(text);
        }
        childrenMedicine.setAgeFrom(Integer.parseInt(getElementTextContent(medicineElement,"ns:age_from")));
        Version version=childrenMedicine.getVersion();

        Element versionELement = (Element) medicineElement.getElementsByTagName("ns:version").item(0);
        version.setVersionName(getElementTextContent(versionELement, "ns:version_name"));
        NodeList prod=versionELement.getElementsByTagName("ns:producers");
        for (int i=0;i<prod.getLength();i++){
            Node node=prod.item(i);
            String text = node.getTextContent();
            version.getProducers().add(text);
        }

        Dosage dosage=version.getDosage();
        Element dosageElement=(Element) versionELement.getElementsByTagName("ns:dosage").item(0);
        dosage.setPrescribedAmount(Integer.parseInt(getElementTextContent(dosageElement,"ns:prescribed_amount")));
        dosage.setFrequency(Integer.parseInt(getElementTextContent(dosageElement,"ns:frequency")));

        MedicinsPackage medicinsPackage=version.getMedicinsPackage();
        Element packElement=(Element) versionELement.getElementsByTagName("ns:medicins_package").item(0);
        medicinsPackage.setPackageType(getElementTextContent(packElement, "ns:package_type"));
        medicinsPackage.setQuantity(Integer.parseInt(getElementTextContent(packElement, "ns:quantity")));
        medicinsPackage.setPrice(Integer.parseInt(getElementTextContent(packElement,"ns:price")));
        return childrenMedicine;
    }

    private AdultMedicine buildAdultMedicine(Element medicineElement) {
        AdultMedicine adultMedicine = new AdultMedicine();
        adultMedicine.setName(medicineElement.getAttribute("name"));
        adultMedicine.setMedicineId(medicineElement.getAttribute("id"));
        adultMedicine.setGroup(getElementTextContent(medicineElement, "ns:group"));
        NodeList analogsList=medicineElement.getElementsByTagName("ns:analog");
        for (int i=0;i<analogsList.getLength();i++){
            Node node=analogsList.item(i);
            String text = node.getTextContent();
            adultMedicine.getAnalogs().add(text);
        }        adultMedicine.setAlcoholAllowed(Boolean.parseBoolean(getElementTextContent(medicineElement,"ns:alcohol_allowed")));
        Version version=adultMedicine.getVersion();

        Element versionELement = (Element) medicineElement.getElementsByTagName("ns:version").item(0);
        version.setVersionName(getElementTextContent(versionELement, "ns:version_name"));
        NodeList prod=versionELement.getElementsByTagName("ns:producers");
        for (int i=0;i<prod.getLength();i++){
            Node node=prod.item(i);
            String text = node.getTextContent();
            version.getProducers().add(text);
        }

        Dosage dosage=version.getDosage();
        Element dosageElement=(Element) versionELement.getElementsByTagName("ns:dosage").item(0);
        dosage.setPrescribedAmount(Integer.parseInt(getElementTextContent(dosageElement,"ns:prescribed_amount")));
        dosage.setFrequency(Integer.parseInt(getElementTextContent(dosageElement,"ns:frequency")));

        MedicinsPackage medicinsPackage=version.getMedicinsPackage();
        Element packElement=(Element) versionELement.getElementsByTagName("ns:medicins_package").item(0);
        medicinsPackage.setPackageType(getElementTextContent(packElement, "ns:package_type"));
        medicinsPackage.setQuantity(Integer.parseInt(getElementTextContent(packElement, "ns:quantity")));
        medicinsPackage.setPrice(Integer.parseInt(getElementTextContent(packElement,"ns:price")));
        return adultMedicine;
    }


    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }
}

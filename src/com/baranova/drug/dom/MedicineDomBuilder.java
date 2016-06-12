package com.baranova.drug.dom;

import com.baranova.drug.entity.*;
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

public class MedicineDomBuilder {
    private Set<Medicine> allmedicine;
    private DocumentBuilder docBuilder;
    public MedicineDomBuilder() {
        this.allmedicine = new HashSet<Medicine>();
        // создание DOM-анализатора
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            try {
                docBuilder=factory.newDocumentBuilder();
            }
            catch (ParserConfigurationException e) {
                System.err.println("Ошибка конфигурации парсера: " + e);
            }
        }

        public Set<Medicine> getAllmedicine() {
            return allmedicine;
        }

    public void buildSetAllmedicine(String fileName) {
        Document doc = null;
        try {
            //parsing XML-документа и создание древовидной структуры
            doc = docBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            System.out.println(root);
            // получение списка дочерних элементов <student>
            NodeList allmedicineList = root.getElementsByTagName("ns:children-medicine");
            System.out.println(allmedicineList.getLength());
            for (int i = 0; i < allmedicineList.getLength(); i++) {
                Element medicineElement = (Element) allmedicineList.item(i);
                ChildrenMedicine childrenMedicine=buildChildrenMedicine(medicineElement);
                allmedicine.add(childrenMedicine);
            }
        }
        catch (IOException e) {
            System.err.println("File error or I/O error: " + e);
        }
        catch (SAXException e) {
            System.err.println("Parsing failure: " + e);
        }
    }

    private ChildrenMedicine buildChildrenMedicine(Element medicineElement) {
        ChildrenMedicine childrenMedicine = new ChildrenMedicine();
        childrenMedicine.setName(medicineElement.getAttribute("name"));
        childrenMedicine.setMedicineId(medicineElement.getAttribute("id"));
        childrenMedicine.setGroup(getElementTextContent(medicineElement, "ns:group"));
        childrenMedicine.getAnalogs().add(getElementTextContent(medicineElement,"ns:analog"));
        Version version=childrenMedicine.getVersion();

        Element versionELement = (Element) medicineElement.getElementsByTagName("ns:version").item(0);
        version.setVersionName(getElementTextContent(versionELement, "ns:version_name"));
        version.getProducers().add(getElementTextContent(versionELement,"ns:producers"));

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

    // получение текстового содержимого тега
    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }
}

package com.baranova.drug.sax;

import com.baranova.drug.entity.AdultMedicine;
import com.baranova.drug.entity.ChildrenMedicine;
import com.baranova.drug.entity.Medicine;
import com.baranova.drug.enums.MedicineEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class MedicineHandler extends DefaultHandler {
    static final Logger LOG= LogManager.getLogger();
    private Set<Medicine> allmedicine;
    private Medicine current;
    private MedicineEnum currentEnum;
    private EnumSet<MedicineEnum> withText;
    public MedicineHandler() {
        allmedicine = new HashSet<>();
        withText = EnumSet.range(MedicineEnum.NAME, MedicineEnum.ALCOHOL_ALLOWED);
    }
    public Set<Medicine> getAllmedicine() {return allmedicine;}

    public void startDocument() {
        LOG.info("SAX parse starts");
    }

    public void endDocument() {
        LOG.info("SAX parse ends");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if ("children_medicine".equals(localName)) {
            current = new ChildrenMedicine();
            current.setName(attrs.getValue(0));
            current.setMedicineId(attrs.getValue(1));
        } else if ("adult_medicine".equals(localName)){
            current= new AdultMedicine();
            current.setName(attrs.getValue(0));
            current.setMedicineId(attrs.getValue(1));
        }
        else
        { MedicineEnum temp = MedicineEnum.valueOf(localName.toUpperCase());
            if (withText.contains(temp)) {
                currentEnum = temp;
            }
        }
    }

    public void endElement(String uri, String localName, String qName) {
        if ("children_medicine".equals(localName)||"adult_medicine".equals(localName)) {
            allmedicine.add(current);
        }
    }

    public void characters(char[] ch, int start, int length) {
        try {
            String s = new String(ch, start, length).trim();
            if (currentEnum != null) {
                switch (currentEnum) {
                    case GROUP:
                        current.setGroup(s);
                        break;
                    case ANALOG:
                        current.getAnalogs().add(s);
                        break;
                    case VERSION_NAME:
                        current.getVersion().setVersionName(s);
                        break;
                    case PRODUCERS:
                        current.getVersion().getProducers().add(s);
                        break;
                    case PACKAGE_TYPE:
                        current.getVersion().getMedicinsPackage().setPackageType(s);
                        break;
                    case QUANTITY:
                        current.getVersion().getMedicinsPackage().setQuantity(Integer.parseInt(s));
                        break;
                    case PRICE:
                        current.getVersion().getMedicinsPackage().setPrice(Integer.parseInt(s));
                        break;
                    case PRESCRIBED_AMOUNT:
                        current.getVersion().getDosage().setPrescribedAmount(Integer.parseInt(s));
                        break;
                    case FREQUENCY:
                        current.getVersion().getDosage().setFrequency(Integer.parseInt(s));
                        break;
                    case AGE_FROM:
                        ChildrenMedicine childrenMedicine = (ChildrenMedicine) current;
                        childrenMedicine.setAgeFrom(Integer.parseInt(s));
                        break;
                    case ALCOHOL_ALLOWED:
                        AdultMedicine adultMedicine = (AdultMedicine) current;
                        adultMedicine.setAlcoholAllowed(Boolean.parseBoolean(s));
                        break;
                    default:
                        throw new EnumConstantNotPresentException(currentEnum.getDeclaringClass(), currentEnum.name());
                }
            }
            currentEnum = null;
        } catch (EnumConstantNotPresentException e) {
            LOG.error("Wrong case"+e.getMessage());
        }
    }
}


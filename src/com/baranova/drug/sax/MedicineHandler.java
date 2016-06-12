package com.baranova.drug.sax;

import com.baranova.drug.entity.AdultMedicine;
import com.baranova.drug.entity.ChildrenMedicine;
import com.baranova.drug.entity.Medicine;
import com.baranova.drug.enums_class.MedicineEnum;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class MedicineHandler extends DefaultHandler {

    private Set<Medicine> allmedicine;
    private Medicine current = null;
    private MedicineEnum currentEnum = null;
    private EnumSet<MedicineEnum> withText;
    public MedicineHandler() {
        allmedicine = new HashSet<Medicine>();
        withText = EnumSet.range(MedicineEnum.NAME, MedicineEnum.ALCOHOL_ALLOWED);
    }
    public Set<Medicine> getAllmedicine() {return allmedicine;}

    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if ("children-medicine".equals(localName)) {
            current = new ChildrenMedicine();
            current.setName(attrs.getValue(0));
            if (attrs.getLength() == 2) {
                current.setMedicineId(attrs.getValue(1));
            }
        }
        else
        { MedicineEnum temp = MedicineEnum.valueOf(localName.toUpperCase());
            if (withText.contains(temp)) {
                currentEnum = temp;
            }
        }
    }

    public void endElement(String uri, String localName, String qName) {
        if ("children-medicine".equals(localName)) {
            allmedicine.add(current);
        }
    }

    public void characters(char[] ch, int start, int length) {
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
                    ChildrenMedicine childrenMedicine=(ChildrenMedicine) current;
                    childrenMedicine.setAgeFrom(Integer.parseInt(s));
                    break;
                case ALCOHOL_ALLOWED:
                    AdultMedicine adultMedicine=(AdultMedicine) current;
                    adultMedicine.setAlcoholAllowed(Boolean.parseBoolean(s));
                    break;
                case VERSION:
                    break;
                case MEDICINS_PACKAGE:
                    break;
                case DOSAGE:
                    break;
                default:
                    throw new EnumConstantNotPresentException(currentEnum.getDeclaringClass(),currentEnum.name());
                }
        }
        currentEnum= null;
    }
}


package com.baranova.drug.sorting;


import com.baranova.drug.entity.Medicine;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class MedicineSorting {

    public static List<Medicine> sortMedicine(Set<Medicine> allMedicine){
        List<Medicine> allMedicineList=new ArrayList<>(allMedicine);
        Comparator<Medicine> byGroup=(o1, o2) -> o1.getGroup().compareToIgnoreCase(o2.getGroup());
        Comparator<Medicine> byPrice=(o1, o2)-> Integer.compare(o1.getVersion().getMedicinsPackage().getPrice(),o2.getVersion().getMedicinsPackage().getPrice());
        allMedicineList.sort(byGroup.thenComparing(byPrice));
        return allMedicineList;
    }
}

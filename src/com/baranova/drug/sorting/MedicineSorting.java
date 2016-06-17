package com.baranova.drug.sorting;


import com.baranova.drug.entity.Medicine;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class MedicineSorting {

    public static void sortMedicine(Set<Medicine> allMedicine){
        List<Medicine> allMedicineList=new ArrayList<>(allMedicine);
        System.out.println("---Sorted Medicine(first by group, then by price)---");
        Comparator<Medicine> byGroup=(o1, o2) -> o1.getGroup().compareToIgnoreCase(o2.getGroup());
        Comparator<Medicine> byPrice=(o1, o2)-> Integer.compare(o1.getVersion().getMedicinsPackage().getPrice(),o2.getVersion().getMedicinsPackage().getPrice());
        allMedicineList.sort(byGroup.thenComparing(byPrice));
        allMedicineList.forEach(System.out::println);

    }
}

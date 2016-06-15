package com.baranova.drug.creation;

import com.baranova.drug.entity.Medicine;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractMedicineBuilder {
    protected Set<Medicine> allMedicine;

    public AbstractMedicineBuilder() {
        allMedicine= new HashSet<Medicine>();
    }

    public AbstractMedicineBuilder(Set<Medicine> allMedicine) {
        this.allMedicine = allMedicine;
    }

    public Set<Medicine> getAllMedicine() {
        return allMedicine;
    }

    abstract public void buildSetAllMedicine(String fileName);
}


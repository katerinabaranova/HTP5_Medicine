package com.baranova.drug.creation;

import com.baranova.drug.entity.Medicine;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractMedicineBuilder {
    protected Set<Medicine> allMedicine;
    public AbstractMedicineBuilder() {
        allMedicine= new HashSet<Medicine>();
    }

    public AbstractMedicineBuilder(Set<Medicine> students) {
        this.allMedicine = students;
    }

    public Set<Medicine> getAllMedicine() {
        return allMedicine;
    }

    abstract public void buildSetStudents(String fileName);
}


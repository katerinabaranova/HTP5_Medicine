package com.baranova.drug.creation;

import com.baranova.drug.entity.Medicine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractMedicineBuilder {
    static final Logger LOG= LogManager.getLogger();
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


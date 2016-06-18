package com.baranova.drug.runner;


import com.baranova.drug.constant.FileConstant;
import com.baranova.drug.creation.AbstractMedicineBuilder;
import com.baranova.drug.creation.MedicineBuilderFactory;
import com.baranova.drug.entity.Medicine;
import com.baranova.drug.export.MedicineReportWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class Runner {
    static final Logger LOG= LogManager.getLogger();

    public static void main (String[] args){
        try {
            MedicineBuilderFactory medicineBuilderFactory = new MedicineBuilderFactory();
            AbstractMedicineBuilder builder = medicineBuilderFactory.createMedicineBuilder("dom");
            builder.buildSetAllMedicine(FileConstant.MEDICINE_XML_FILE);
            Set<Medicine> allMedicine = builder.getAllMedicine();
            MedicineReportWriter.writeMedicinetoFile(FileConstant.REPORT_FILE,allMedicine);
        }catch (IllegalArgumentException e){
            LOG.error(e.getMessage());
        }

    }
}

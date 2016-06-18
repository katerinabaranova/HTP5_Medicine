package com.baranova.drug.export;


import com.baranova.drug.entity.Medicine;
import com.baranova.drug.sorting.MedicineSorting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

public class MedicineReportWriter {
    static final Logger LOG= LogManager.getLogger();
    public static void writeMedicinetoFile(String filename, Set<Medicine> allMedicine){
        File file=new File(filename);
        PrintWriter printWriter=null;
        try {
            printWriter=new PrintWriter(file);
            allMedicine.forEach(printWriter::println);
            List<Medicine> sortedMedicine=MedicineSorting.sortMedicine(allMedicine);
            printWriter.println("---Sorted Medicine(first by group, then by price)---");
            sortedMedicine.forEach(printWriter::println);
        }catch (IOException e){
            LOG.error("Error while writing file:" + filename);
        } finally {
            if (printWriter!=null){
                printWriter.close();
            }
        }


    }
}

package com.baranova.drug.entity;

import java.util.ArrayList;
import java.util.List;

public class Version {

    private String versionName;
    private List<String> producers=new ArrayList<>();
    private MedicinsPackage medicinsPackage=new MedicinsPackage();
    private Dosage dosage=new Dosage();

    public String getVersionName() {return versionName;}
    public List<String> getProducers() {return producers;}
    public MedicinsPackage getMedicinsPackage() {return medicinsPackage;}
    public Dosage getDosage() {return dosage;}

    public void setVersionName(String versionName) {this.versionName = versionName;}
    public void setProducers(List<String> producers) {this.producers = producers;}
    public void setMedicinsPackage(MedicinsPackage medicinsPackage) {this.medicinsPackage = medicinsPackage;}
    public void setDosage(Dosage dosage) {this.dosage = dosage;}

    public void addProducer(String poducer){
        producers.add(poducer);
    }

    @Override
    public String toString() {
        StringBuilder version=new StringBuilder("Version{");
        version.append("versionName='").append(versionName);
        version.append(", producers=").append(producers);
        version.append(", medicinsPackage=").append(medicinsPackage);
        version.append(", dosage=").append(dosage);
        return version.toString();
    }
}

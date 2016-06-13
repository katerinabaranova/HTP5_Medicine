package com.baranova.drug.entity;

import java.util.ArrayList;
import java.util.List;

public abstract class Medicine {

    private String name;
    private String medicineId;
    private String group;
    private List<String> analogs=new ArrayList<>();
    private Version version=new Version();

    public String getName() {return name;}
    public String getMedicineId(){return medicineId;}
    public String getGroup() {return group;}
    public List<String> getAnalogs() {return analogs;}
    public Version getVersion() {return version;}

    public void setName(String name) {this.name = name;}
    public void setMedicineId(String medicineId){this.medicineId=medicineId;}
    public void setGroup(String group) {this.group = group;}
    public void setAnalogs(List<String> analogs) {this.analogs = analogs;}
    public void setVersion(Version version) {this.version = version;}

    public void addAnalog(String analog){ analogs.add(analog);}
    @Override
    public String toString() {
        return "Medicins{" +
                "name='" + name + '\'' +
                ", group=" + group +
                ", analogs=" + analogs +
                ", version=" + version +
                '}';
    }
}


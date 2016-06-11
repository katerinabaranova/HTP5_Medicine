package com.baranova.drug.entity;

import com.baranova.drug.enums_class.MedicineGroup;

import java.util.List;

public class Medicine {

    private String name;
    private String pharm;
    private MedicineGroup group;
    private List<String> analogs;
    private Version version;

    public String getName() {return name;}
    public String getPharm() {return pharm;}
    public MedicineGroup getGroup() {return group;}
    public List<String> getAnalogs() {return analogs;}
    public Version getVersion() {return version;}

    public void setName(String name) {this.name = name;}
    public void setPharm(String pharm) {this.pharm = pharm;}
    public void setGroup(MedicineGroup group) {this.group = group;}
    public void setAnalogs(List<String> analogs) {this.analogs = analogs;}
    public void setVersions(Version version) {this.version = version;}

    @Override
    public String toString() {
        return "Medicins{" +
                "name='" + name + '\'' +
                ", pharm='" + pharm + '\'' +
                ", group=" + group +
                ", analogs=" + analogs +
                ", version=" + version +
                '}';
    }
}


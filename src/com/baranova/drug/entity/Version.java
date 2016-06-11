package com.baranova.drug.entity;

import com.baranova.drug.enums_class.VersionName;

import java.util.List;

public class Version {

    private VersionName name;
    private List<String> producers;
    private MedicinsPackage medicinsPackage;
    private Dosage dosage;

}

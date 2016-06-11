package com.baranova.drug.entity;


public class Dosage {


    private int prescribedAmount;
    private int frequency;

    public int getPrescribedAmount() {return prescribedAmount;}
    public int getFrequency() {return frequency;}

    public void setPrescribedAmount(int prescribedAmount) {this.prescribedAmount = prescribedAmount;}
    public void setFrequency(int frequency) {this.frequency = frequency;}

    @Override
    public String toString() {
        StringBuilder dosage=new StringBuilder("Dosage {");
        dosage=dosage.append("Prescribed Amount=").append(prescribedAmount).append(",");
        dosage=dosage.append("frequency=").append(frequency).append("}");
        return dosage.toString();
    }
}

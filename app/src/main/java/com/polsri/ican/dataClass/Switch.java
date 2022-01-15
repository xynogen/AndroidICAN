package com.polsri.ican.dataClass;

public class Switch {

    public String blower;
    public String pompa;
    public String pompa2;

    public Switch() {
    }

    public Switch(String blower, String pompa, String pompa2) {
        this.blower = blower;
        this.pompa = pompa;
        this.pompa2 = pompa2;
    }

    public void setBlower(String blower) {
        this.blower = blower;
    }

    public void setPompa(String pompa) {
        this.pompa = pompa;
    }

    public String getBlower() {
        return blower;
    }

    public String getPompa() {
        return pompa;
    }

    public String getPompa2() {
        return pompa2;
    }

    public void setPompa2(String pompa2) {
        this.pompa2 = pompa2;
    }
}

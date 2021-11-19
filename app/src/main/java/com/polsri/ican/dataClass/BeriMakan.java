package com.polsri.ican.dataClass;

public class BeriMakan {
    public String beratPakan, timerPakan;

    public BeriMakan() {
        this.beratPakan = "1";
        this.timerPakan = "1";
    }

    public BeriMakan(String beratPakan, String timerPakan) {
        this.beratPakan = beratPakan;
        this.timerPakan = timerPakan;
    }
    public void setBeratPakan(String beratPakan) {
        this.beratPakan = beratPakan;
    }

    public void setTimerPakan(String timerPakan) {
        this.timerPakan = timerPakan;
    }

    public String getBeratPakan() {
        return beratPakan;
    }

    public String getTimerPakan() {
        return timerPakan;
    }
}

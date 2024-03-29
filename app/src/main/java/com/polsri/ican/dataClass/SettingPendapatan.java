package com.polsri.ican.dataClass;

public class SettingPendapatan {
    String jumlahPanen, hargaJualProduk;

    public SettingPendapatan() {
    }

    public SettingPendapatan(String jumlahPanen, String hargaJualProduk) {
        this.jumlahPanen = jumlahPanen;
        this.hargaJualProduk = hargaJualProduk;
    }

    public String getJumlahPanen() {
        return jumlahPanen;
    }

    public void setJumlahPanen(String jumlahPanen) {
        this.jumlahPanen = jumlahPanen;
    }

    public String getHargaJualProduk() {
        return hargaJualProduk;
    }

    public void setHargaJualProduk(String hargaJualProduk) {
        this.hargaJualProduk = hargaJualProduk;
    }

    public int getTotalKeuntungan() {
        if (this.jumlahPanen.isEmpty() || hargaJualProduk.isEmpty()) {
            return 1;
        }
        else {
            return Integer.parseInt(this.jumlahPanen) * Integer.parseInt(this.hargaJualProduk);
        }
    }
}

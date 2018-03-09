package main.survey.com.model;

/**
 * Created by user on 1/5/2018.
 */

public class OrderModel {

    private String nama;
    private String alamat;
    private String pekerjaan;
    private String jeniskelamin;
    private String keterangan;

    public OrderModel(String nama, String alamat, String pekerjaan, String jeniskelamin, String keterangan) {
        this.nama = nama;
        this.alamat = alamat;
        this.pekerjaan = pekerjaan;
        this.jeniskelamin = jeniskelamin;
        this.keterangan = keterangan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public String getJeniskelamin() {
        return jeniskelamin;
    }

    public void setJeniskelamin(String jeniskelamin) {
        this.jeniskelamin = jeniskelamin;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }



}

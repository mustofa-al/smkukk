/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author A
 */
public class Tanggapan {
    private int tanggapanId;
    private int pengaduanId;
    private String date;
    private String isiTanggapan;
    private int petugasId;
    private Petugas petugas;
    private Pengaduan pengaduan;

    public int getTanggapanId() {
        return tanggapanId;
    }

    public int getPengaduanId() {
        return pengaduanId;
    }

    public String getDate() {
        return date;
    }

    public String getIsiTanggapan() {
        return isiTanggapan;
    }

    public int getPetugasId() {
        return petugasId;
    }

    public void setTanggapanId(int tanggapanId) {
        this.tanggapanId = tanggapanId;
    }

    public void setPengaduanId(int pengaduanId) {
        this.pengaduanId = pengaduanId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setIsiTanggapan(String isiTanggapan) {
        this.isiTanggapan = isiTanggapan;
    }

    public void setPetugasId(int petugasId) {
        this.petugasId = petugasId;
    }

    public Petugas getPetugas() {
        return petugas;
    }

    public void setPetugas(Petugas petugas) {
        this.petugas = petugas;
    }

    public Pengaduan getPengaduan() {
        return pengaduan;
    }

    public void setPengaduan(Pengaduan pengaduan) {
        this.pengaduan = pengaduan;
    }
    
    
    
}

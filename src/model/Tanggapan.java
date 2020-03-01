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
    int tanggapanId;
    int pengaduanId;
    String date;
    String isiTanggapan;
    int petugasId;

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
    
    
    
}

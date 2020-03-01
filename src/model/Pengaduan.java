/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;

/**
 *
 * @author A
 */
public class Pengaduan {
    private int id;
    private String date;
    private String nik;
    private Pelapor pelapor;
    private String isiLaporan;
    private File foto;
    private byte[] photo;
    private StatusPengaduan status;

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getNik() {
        return nik;
    }

    public Pelapor getPelapor() {
        return pelapor;
    }

    public String getIsiLaporan() {
        return isiLaporan;
    }

    public File getFoto() {
        return foto;
    }

    public StatusPengaduan getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public void setPelapor(Pelapor pelapor) {
        this.pelapor = pelapor;
    }

    public void setIsiLaporan(String isiLaporan) {
        this.isiLaporan = isiLaporan;
    }

    public void setFoto(File foto) {
        this.foto = foto;
    }

    public void setStatus(StatusPengaduan status) {
        this.status = status;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
    
}

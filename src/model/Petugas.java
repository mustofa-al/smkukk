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
public class Petugas {
    private int id;
    private String nama;
    private String username;
    private String password;
    private String telp;
    private StatusPetugas status;

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getTelp() {
        return telp;
    }

    public StatusPetugas getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public void setStatus(StatusPetugas status) {
        this.status = status;
    }
    
    
}

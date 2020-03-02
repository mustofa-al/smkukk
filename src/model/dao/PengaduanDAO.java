/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import config.FileHelper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Pelapor;
import model.Pengaduan;
import model.StatusPengaduan;
import model.db.DBConnection;

/**
 *
 * @author A
 */
public class PengaduanDAO {
    public void insert(Pengaduan data, ResultListener callback) {
        String query = null;
        InputStream stream = null;
        if (data.getFoto() != null) {
            stream = new FileHelper().byteToInputStream(data.getFoto());
            query = "INSERT INTO pengaduan (tgl_pengaduan, nik, isi_laporan, foto) "
                +"VALUES (?, ?, ?, ?)";
        } else {
            query = "INSERT INTO pengaduan (tgl_pengaduan, nik, isi_laporan) "
                +"VALUES (?, ?, ?)";
        }
        
        try {
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
            statement.setString(1, data.getDate());
            statement.setString(2, data.getNik());
            statement.setString(3, data.getIsiLaporan());
            if (stream != null) {
                statement.setBlob(4, stream);
            }
            statement.execute();
            statement.close();
            callback.onSuccess();
        } catch (SQLException e) {
            Logger.getLogger(PengaduanDAO.class.getName()).log(Level.SEVERE, null, e);
            callback.onFailure(e);
        }
    }

    public void getData(ResultDataListener<List<Pengaduan>> callback) {
        String query = "SELECT pengaduan.id_pengaduan, pengaduan.tgl_pengaduan, pengaduan.isi_laporan, masyarakat.nama FROM pengaduan, masyarakat WHERE pengaduan.status = 'terkirim'";
        List<Pengaduan> listPengaduan = new ArrayList<Pengaduan>();
        try {
            Statement statement = DBConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery(query);
            if (result != null) {
                while (result.next()) {                    
                    Pengaduan pengaduan = new Pengaduan();
                    pengaduan.setId(result.getInt("id_pengaduan"));
                    pengaduan.setDate(result.getString("tgl_pengaduan"));
                    pengaduan.setIsiLaporan(result.getString("isi_laporan"));
                    pengaduan.setPelapor(new Pelapor());
                    pengaduan.getPelapor().setNama(result.getString("nama"));
                    listPengaduan.add(pengaduan);
                    callback.onSuccess(listPengaduan);
                }
            } else {
                callback.onFailure(null);
            }
        } catch (SQLException e) {
            Logger.getLogger(PetugasDAO.class.getName()).log(Level.SEVERE, null, e);
            callback.onFailure(e);
        }
    }
    
    public void getDetailPengaduan(int pengaduanId, ResultDataListener<Pengaduan> callback) {
        String query = "SELECT masyarakat.nama, masyarakat.telp, pengaduan.id_pengaduan, pengaduan.tgl_pengaduan, "
                + "pengaduan.isi_laporan, pengaduan.foto FROM pengaduan, masyarakat "
                + "WHERE pengaduan.id_pengaduan = "+pengaduanId;
        try {
            Statement statement = DBConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery(query);
            if (result.next()) {
                Pengaduan pengaduan = new Pengaduan();
                pengaduan.setId(result.getInt("id_pengaduan"));
                pengaduan.setPelapor(new Pelapor());
                pengaduan.setDate(result.getString("tgl_pengaduan"));
                pengaduan.getPelapor().setTelp(result.getString("telp"));
                pengaduan.setIsiLaporan(result.getString("isi_laporan"));
                pengaduan.getPelapor().setNama(result.getString("nama"));
                if (result.getBlob("foto") != null) {
                    pengaduan.setFoto(result.getBlob("foto").getBytes(1, (int) result.getBlob("foto").length()));
                }
                callback.onSuccess(pengaduan);
            } else {
                callback.onFailure(null);
            }
        } catch (SQLException e) {
            Logger.getLogger(PetugasDAO.class.getName()).log(Level.SEVERE, null, e);
            callback.onFailure(e);
        }
    }
    
    public void setStatusPengaduan(int pengaduanId, StatusPengaduan status, ResultListener callback) {
        String query = "UPDATE pengaduan SET status = ? WHERE id_pengaduan=?";
        try {
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
            statement.setString(1, status.name());
            statement.setInt(2, pengaduanId);
            statement.executeUpdate();
            statement.close();
            callback.onSuccess();
        } catch (SQLException ex) {
            Logger.getLogger(TanggapanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getData(String nik, ResultDataListener<List<Pengaduan>> callback) {
        String query = "SELECT pengaduan.id_pengaduan, pengaduan.tgl_pengaduan, pengaduan.isi_laporan, pengaduan.status"
                + " FROM pengaduan, masyarakat WHERE pengaduan.nik = '"+nik+"'";
        List<Pengaduan> listPengaduan = new ArrayList<Pengaduan>();
        try {
            Statement statement = DBConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery(query);
            if (result != null) {
                while (result.next()) {
                    Pengaduan pengaduan = new Pengaduan();
                    pengaduan.setId(result.getInt("id_pengaduan"));
                    pengaduan.setDate(result.getString("tgl_pengaduan"));
                    pengaduan.setIsiLaporan(result.getString("isi_laporan"));
                    pengaduan.setStatus(StatusPengaduan.valueOf(result.getString("status")));
                    listPengaduan.add(pengaduan);
                    callback.onSuccess(listPengaduan);
                }
            } else {
                callback.onFailure(null);
            }
        } catch (SQLException e) {
            Logger.getLogger(PetugasDAO.class.getName()).log(Level.SEVERE, null, e);
            callback.onFailure(e);
        }
    }

    public void update(Pengaduan pengaduan, ResultListener callback) {
        String query;
        InputStream stream = null;
        if (pengaduan.getFoto() != null) {
            stream = new FileHelper().byteToInputStream(pengaduan.getFoto());
            query = "UPDATE pengaduan SET isi_laporan = ?, foto=? WHERE id_pengaduan=?";
        } else {
            query = "UPDATE pengaduan SET isi_laporan = ? WHERE id_pengaduan=?";
        }
        Logger.getLogger(TanggapanDAO.class.getName()).log(Level.SEVERE, query);
        try {
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
            if (pengaduan.getFoto() != null) {
                statement.setString(1, pengaduan.getIsiLaporan());
                statement.setBlob(2, stream);
                statement.setInt(3, pengaduan.getId());
            } else {
                statement.setString(1, pengaduan.getIsiLaporan());
                statement.setInt(2, pengaduan.getId());
            }
            statement.executeUpdate();
            statement.close();
            callback.onSuccess();
        } catch (SQLException ex) {
            callback.onFailure(ex);
            Logger.getLogger(TanggapanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

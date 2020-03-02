/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import config.FileHelper;
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
import model.Petugas;
import model.StatusPengaduan;
import model.Tanggapan;
import model.db.DBConnection;

/**
 *
 * @author A
 */
public class TanggapanDAO {

    public void insert(Tanggapan tanggapan, ResultListener callback) {
        String query = "INSERT INTO tanggapan (id_pengaduan, tgl_tanggapan, tanggapan, id_petugas) "
                + "VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
            statement.setInt(1, tanggapan.getPengaduanId());
            statement.setString(2, tanggapan.getDate());
            statement.setString(3, tanggapan.getIsiTanggapan());
            statement.setInt(4, tanggapan.getPetugasId());
            statement.execute();
            statement.close();
            callback.onSuccess();
        } catch (SQLException e) {
            Logger.getLogger(PengaduanDAO.class.getName()).log(Level.SEVERE, null, e);
            callback.onFailure(e);
        }
    }

    public void getData(Pelapor pelapor, ResultDataListener<List<Tanggapan>> callback) {
        String query = "SELECT tanggapan.id_tanggapan, tanggapan.tgl_tanggapan, tanggapan.tanggapan, pengaduan.isi_laporan,"
                + " petugas.nama_petugas FROM pengaduan, tanggapan, petugas, masyarakat "
                + "WHERE tanggapan.id_pengaduan = pengaduan.id_pengaduan "
                + "AND masyarakat.nik = '" + pelapor.getNik() + "'" + " AND pengaduan.status = 'diproses'"
                + " ORDER BY tanggapan.id_tanggapan DESC";
        List<Tanggapan> listTanggapan = new ArrayList<Tanggapan>();
        try {
            Statement statement = DBConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery(query);
            if (result.next() == false) {
                callback.onFailure(null);
            } else {
                do {
                    Tanggapan tanggapan = new Tanggapan();
                    tanggapan.setTanggapanId(result.getInt("id_tanggapan"));
                    tanggapan.setDate(result.getString("tgl_tanggapan"));
                    tanggapan.setIsiTanggapan(result.getString("tanggapan"));
                    tanggapan.setPengaduan(new Pengaduan());
                    tanggapan.getPengaduan().setIsiLaporan(result.getString("isi_laporan"));
                    tanggapan.setPetugas(new Petugas());
                    tanggapan.getPetugas().setNama(result.getString("nama_petugas"));
                    listTanggapan.add(tanggapan);
                    callback.onSuccess(listTanggapan);
                } while (result.next());
            }
        } catch (SQLException e) {
            Logger.getLogger(PetugasDAO.class.getName()).log(Level.SEVERE, null, e);
            callback.onFailure(e);
        }
    }

    public void getDetailTanggapan(int tanggapanId, Pelapor pelapor, ResultDataListener<Tanggapan> callback) {
        String query = "SELECT tanggapan.id_tanggapan, tanggapan.tgl_tanggapan, tanggapan.tanggapan, pengaduan.id_pengaduan, pengaduan.isi_laporan,"
                + " petugas.nama_petugas FROM pengaduan, tanggapan, petugas, masyarakat "
                + "WHERE tanggapan.id_tanggapan = " + tanggapanId + " AND tanggapan.id_pengaduan = pengaduan.id_pengaduan "
                + " AND pengaduan.status = 'diproses'";
        try {
            Statement statement = DBConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery(query);
            if (result.next()) {
                Tanggapan tanggapan = new Tanggapan();
                tanggapan.setTanggapanId(result.getInt("id_tanggapan"));
                tanggapan.setDate(result.getString("tgl_tanggapan"));
                tanggapan.setIsiTanggapan(result.getString("tanggapan"));
                tanggapan.setPengaduan(new Pengaduan());
                tanggapan.getPengaduan().setIsiLaporan(result.getString("isi_laporan"));
                tanggapan.setPengaduanId(result.getInt("id_pengaduan"));
                tanggapan.setPetugas(new Petugas());
                tanggapan.getPetugas().setNama(result.getString("nama_petugas"));
                callback.onSuccess(tanggapan);
            } else {
                callback.onFailure(null);
            }
        } catch (SQLException e) {
            Logger.getLogger(PetugasDAO.class.getName()).log(Level.SEVERE, null, e);
            callback.onFailure(e);
        }
    }

    public void getTanggapanPetugas(Petugas petugas, ResultDataListener<List<Tanggapan>> callback) {
        String query = "SELECT tanggapan.id_tanggapan, tanggapan.tgl_tanggapan, tanggapan.tanggapan, pengaduan.isi_laporan, pengaduan.status "
                + " FROM pengaduan, tanggapan, petugas "
                + "WHERE tanggapan.id_pengaduan = pengaduan.id_pengaduan "
                + "AND tanggapan.id_petugas = "+petugas.getId()
                + " AND pengaduan.status = 'diproses'  GROUP BY tanggapan.id_pengaduan ORDER BY tanggapan.id_tanggapan DESC";
        List<Tanggapan> listTanggapan = new ArrayList<Tanggapan>();
        try {
            Statement statement = DBConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery(query);
            if (result.next() == false) {
                callback.onFailure(null);
            } else {
                do {
                    Tanggapan tanggapan = new Tanggapan();
                    tanggapan.setTanggapanId(result.getInt("id_tanggapan"));
                    tanggapan.setDate(result.getString("tgl_tanggapan"));
                    tanggapan.setIsiTanggapan(result.getString("tanggapan"));
                    tanggapan.setPengaduan(new Pengaduan());
                    tanggapan.getPengaduan().setIsiLaporan(result.getString("isi_laporan"));
                    tanggapan.getPengaduan().setStatus(StatusPengaduan.valueOf(result.getString("status")));
                    listTanggapan.add(tanggapan);
                    callback.onSuccess(listTanggapan);
                } while (result.next());
            }
        } catch (SQLException e) {
            Logger.getLogger(PetugasDAO.class.getName()).log(Level.SEVERE, null, e);
            callback.onFailure(e);
        }
    }

    public void delete(Tanggapan selected, ResultListener callback) {
        String query = "DELETE FROM tanggapan WHERE id_tanggapan=?";
        try {
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
            statement.setInt(1, selected.getTanggapanId());
            statement.executeUpdate();
            statement.close();
            callback.onSuccess();
        } catch (SQLException ex) {
            callback.onFailure(ex);
            Logger.getLogger(TanggapanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

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
import model.StatusPetugas;
import model.Tanggapan;
import model.db.DBConnection;

/**
 *
 * @author A
 */
public class PetugasDAO {
    public void login(String username, String password, ResultDataListener<Petugas> callback) {
        String query = "SELECT * FROM petugas WHERE username = '"+username+"' AND password = "+password;
        try {
            Statement statement = DBConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery(query);
            if (result.next()) {
                Petugas petugas = new Petugas();
                petugas.setId(result.getInt("id_petugas"));
                petugas.setNama(result.getString("nama_petugas"));
                petugas.setUsername(result.getString("username"));
                petugas.setTelp(result.getString("telp"));
                petugas.setStatus(StatusPetugas.valueOf(result.getString("level")));
                callback.onSuccess(petugas);
            } else {
                callback.onFailure(null);
            }
        } catch (SQLException e) {
            Logger.getLogger(PetugasDAO.class.getName()).log(Level.SEVERE, null, e);
            callback.onFailure(e);
        }
    }

    public void insert(Petugas petugas, ResultListener callback) {
        String query = "INSERT INTO petugas (nama_petugas, username, password, telp, level) "
                +"VALUES (?, ?, MD5(?), ?, ?)";
        try {
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
            statement.setString(1, petugas.getNama());
            statement.setString(2, petugas.getUsername());
            statement.setString(3, petugas.getPassword());
            statement.setString(4, petugas.getTelp());
            statement.setString(5, StatusPetugas.petugas.name());
            statement.execute();
            statement.close();
            callback.onSuccess();
        } catch (SQLException e) {
            Logger.getLogger(PelaporDAO.class.getName()).log(Level.SEVERE, null, e);
            callback.onFailure(e);
        }
    }

    public void getPetugas(ResultDataListener<List<Petugas>> callback) {
        String query = "SELECT * FROM petugas";
        List<Petugas> listPetugas = new ArrayList<Petugas>();
        try {
            Statement statement = DBConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery(query);
            if (result.next() == false) {
                callback.onFailure(null);
            } else {
                do {
                    Petugas petugas = new Petugas();
                    petugas.setId(result.getInt("id_petugas"));
                    petugas.setNama(result.getString("nama_petugas"));
                    petugas.setUsername(result.getString("username"));
                    petugas.setTelp(result.getString("telp"));
                    petugas.setStatus(StatusPetugas.valueOf(result.getString("level")));
                    listPetugas.add(petugas);
                    callback.onSuccess(listPetugas);
                } while (result.next());
            }
        } catch (SQLException e) {
            Logger.getLogger(PetugasDAO.class.getName()).log(Level.SEVERE, null, e);
            callback.onFailure(e);
        }
    }

    public void delete(int id, ResultListener callback) {
        String query = "DELETE FROM petugas WHERE id_petugas=?";
        try {
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
            callback.onSuccess();
        } catch (SQLException ex) {
            callback.onFailure(ex);
            Logger.getLogger(TanggapanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(Petugas petugas, ResultListener callback) {
        String query = "UPDATE petugas SET nama_petugas = ?, username = ?, password = MD5(?), "
                + "telp =?, level = ? WHERE id_petugas=?";
        try {
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
            statement.setString(1, petugas.getNama());
            statement.setString(2, petugas.getUsername());
            statement.setString(3, petugas.getPassword());
            statement.setString(4, petugas.getTelp());
            statement.setString(5, petugas.getStatus().name());
            statement.setInt(6, petugas.getId());
            statement.executeUpdate();
            statement.close();
            callback.onSuccess();
        } catch (SQLException ex) {
            callback.onFailure(ex);
            Logger.getLogger(TanggapanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

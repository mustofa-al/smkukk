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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Pelapor;
import model.Petugas;
import model.StatusPetugas;
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
}

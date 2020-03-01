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
                Logger.getLogger(PetugasDAO.class.getName()).log(Level.SEVERE, result.getString("level"));
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
}

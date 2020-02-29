/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Pelapor;
import model.db.DBConnection;

/**
 *
 * @author A
 */
public class PelaporDAO {
    public void insert(Pelapor data, ResultListener callback) {
        String query = "INSERT INTO masyarakat (nik, nama, username, password, telp) "
                +"VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
            statement.setString(1, data.getNik());
            statement.setString(2, data.getNama());
            statement.setString(3, data.getUsername());
            statement.setString(4, data.getPassword());
            statement.setString(5, data.getTelp());
            statement.execute();
            statement.close();
            callback.onSuccess();
        } catch (SQLException e) {
            Logger.getLogger(PelaporDAO.class.getName()).log(Level.SEVERE, null, e);
            callback.onFailure(e);
        }
    }
}

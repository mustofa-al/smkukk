/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Pelapor;
import model.Pengaduan;
import model.db.DBConnection;

/**
 *
 * @author A
 */
public class PengaduanDAO {
    public void insert(Pengaduan data, ResultListener callback) {
        String query = null;
        FileInputStream stream = null;
        if (data.getFoto() != null) {
            try {
                stream = new FileInputStream(data.getFoto());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PengaduanDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
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
}

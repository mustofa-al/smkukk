/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import config.FileHelper;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Pengaduan;
import model.Tanggapan;
import model.db.DBConnection;

/**
 *
 * @author A
 */
public class TanggapanDAO {
    public void insert(Tanggapan tanggapan, ResultListener callback) {
        String query = "INSERT INTO tanggapan (id_pengaduan, tgl_tanggapan, tanggapan, id_petugas) "
                +"VALUES (?, ?, ?, ?)";
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
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import config.DateTools;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Pelapor;
import model.Pengaduan;
import model.dao.PengaduanDAO;
import model.dao.ResultListener;
import view.PengaduanBaru;

/**
 *
 * @author A
 */
public class PengaduanBaruController {
    private PengaduanBaru pengaduanBaruView;
    private Pelapor pelapor;
    private PengaduanDAO pengaduanDAO;

    public PengaduanBaruController(PengaduanBaru pengaduanBaruView, Pelapor pelapor) {
        this.pengaduanBaruView = pengaduanBaruView;
        this.pelapor = pelapor;
        initView();
        initListeners();
        initDAO();
    }

    private void initView() {
        pengaduanBaruView.setVisible(true);
    }

    private void initListeners() {
        pengaduanBaruView.getButtonKirim().addActionListener((ae) -> {
            if (isValidForm()) {
                Pengaduan pengaduan = new Pengaduan();
                pengaduan.setDate(new DateTools().getMySqlDateNow());
                pengaduan.setNik(pelapor.getNik());
                pengaduan.setIsiLaporan(pengaduanBaruView.getFieldLaporan().getText());
                if (pengaduanBaruView.getFileToUpload() != null) {
                    FileInputStream fis = null;
                    try {
                        fis = new FileInputStream(pengaduanBaruView.getFileToUpload());
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(PengaduanBaruController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //System.out.println(file.exists() + "!!");
                    //InputStream in = resource.openStream();
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    byte[] buf = new byte[1024];
                    try {
                        for (int readNum; (readNum = fis.read(buf)) != -1;) {
                            bos.write(buf, 0, readNum); //no doubt here is 0
                            //Writes len bytes from the specified byte array starting at offset off to this byte array output stream.
                            System.out.println("read " + readNum + " bytes,");
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(PengaduanBaruController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    byte[] bytes = bos.toByteArray();
                    pengaduan.setFoto(bytes);
                }
                pengaduanDAO.insert(pengaduan, new ResultListener() {
                    @Override
                    public void onSuccess() {
                        pengaduanBaruView.showAlert("Berhasil membuat pengaduan baru!");
                        pengaduanBaruView.dispose();
                    }

                    @Override
                    public void onFailure(SQLException e) {
                        pengaduanBaruView.showErrorAlert("Gagal membuat pengaduan baru!");
                    }
                    
                });
            }
        });
    }

    private boolean isValidForm() {
        boolean isValid = false;
        if (pengaduanBaruView.getFieldLaporan().getText().length() == 0) {
            pengaduanBaruView.showAlert("Isi laporan tidak boleh kosong!");
        } else {
            isValid = true;
        }
        return isValid;
    }

    private void initDAO() {
        pengaduanDAO = new PengaduanDAO();
    }
}

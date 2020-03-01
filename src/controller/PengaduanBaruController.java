/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import config.DateConverter;
import java.sql.SQLException;
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
                pengaduan.setDate(new DateConverter().getMySqlDateNow());
                pengaduan.setNik(pelapor.getNik());
                pengaduan.setIsiLaporan(pengaduanBaruView.getFieldLaporan().getText());
                if (pengaduanBaruView.getFileToUpload() != null) {
                    pengaduan.setFoto(pengaduanBaruView.getFileToUpload());
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

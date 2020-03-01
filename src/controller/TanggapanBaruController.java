/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import config.DateTools;
import java.sql.SQLException;
import model.Petugas;
import model.StatusPengaduan;
import model.Tanggapan;
import model.dao.PengaduanDAO;
import model.dao.ResultListener;
import model.dao.TanggapanDAO;
import view.DetailPengaduan;
import view.TanggapanBaru;

/**
 *
 * @author A
 */
public class TanggapanBaruController {
    private TanggapanBaru tanggapanBaruView;
    private int pengaduanId;
    private Petugas petugas;
    private TanggapanDAO tanggapanDAO;
    private PengaduanDAO pengaduanDAO;

    public TanggapanBaruController(TanggapanBaru tanggapanBaruView, int pengaduanId, Petugas petugas) {
        this.tanggapanBaruView = tanggapanBaruView;
        this.pengaduanId = pengaduanId;
        this.petugas = petugas;
        initView();
        initListener();
        initDAO();
    }

    private void initView() {
        tanggapanBaruView.setVisible(true);
    }

    private void initListener() {
        tanggapanBaruView.getButtonKirimTanggapan().addActionListener((ae) -> {
            if (isValidInput()) {
                Tanggapan tanggapan = new Tanggapan();
                tanggapan.setPengaduanId(pengaduanId);
                tanggapan.setDate(new DateTools().getMySqlDateNow());
                tanggapan.setIsiTanggapan(tanggapanBaruView.getFieldTanggapan().getText());
                tanggapan.setPetugasId(petugas.getId());
                tanggapanDAO.insert(tanggapan, new ResultListener() {
                    @Override
                    public void onSuccess() {
                        pengaduanDAO.setStatusPengaduan(pengaduanId, StatusPengaduan.diproses, new ResultListener() {
                            @Override
                            public void onSuccess() {
                                tanggapanBaruView.showAlert("Berhasil mengirim tanggapan!");
                                tanggapanBaruView.dispose();
                            }

                            @Override
                            public void onFailure(SQLException e) {
                                tanggapanBaruView.showErrorAlert("Gagal mengirim tanggapan!");
                            }
                        });
                    }

                    @Override
                    public void onFailure(SQLException e) {
                        tanggapanBaruView.showErrorAlert("Gagal mengirim tanggapan!");
                    }
                });
            }
        });
    }

    private void initDAO() {
        tanggapanDAO = new TanggapanDAO();
        pengaduanDAO = new PengaduanDAO();
    }

    private boolean isValidInput() {
        boolean isValid = false;
        if (tanggapanBaruView.getFieldTanggapan().getText().length() == 0) {
            tanggapanBaruView.showAlert("Tanggapan tidak boleh kosong!");
        } else {
            isValid = true;
        }
        return isValid;
    }
    
    
}

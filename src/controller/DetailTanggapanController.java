/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import model.Pelapor;
import model.StatusPengaduan;
import model.Tanggapan;
import model.dao.PengaduanDAO;
import model.dao.ResultDataListener;
import model.dao.ResultListener;
import model.dao.TanggapanDAO;
import view.DetailTanggapan;

/**
 *
 * @author A
 */
public class DetailTanggapanController {
    private DetailTanggapan detailTanggapanView;
    private Pelapor pelapor;
    private Tanggapan tanggapan;
    private TanggapanDAO tanggapanDAO;
    private PengaduanDAO pengaduanDAO;

    public DetailTanggapanController(DetailTanggapan detailTanggapanView, Pelapor pelapor, Tanggapan tanggapan) {
        this.detailTanggapanView = detailTanggapanView;
        this.pelapor = pelapor;
        this.tanggapan = tanggapan;
        initDAO();
        initView();
        initListener();
        initData();
    }

    private void initView() {
        detailTanggapanView.setVisible(true);
    }

    private void initDAO() {
        tanggapanDAO = new TanggapanDAO();
        pengaduanDAO = new PengaduanDAO();
    }

    private void initListener() {
        detailTanggapanView.getButtonSelesai().addActionListener((ae) -> {
            pengaduanDAO.setStatusPengaduan(tanggapan.getPengaduanId(), StatusPengaduan.selesai, new ResultListener() {
                @Override
                public void onSuccess() {
                    detailTanggapanView.showAlert("Pengaduan selesai!");
                    detailTanggapanView.dispose();
                }

                @Override
                public void onFailure(SQLException e) {
                    detailTanggapanView.showAlert("Gagal menyelesaikan pengaduan!");
                }
            });
        });
    }

    private void initData() {
        tanggapanDAO.getDetailTanggapan(tanggapan.getTanggapanId(), pelapor, new ResultDataListener<Tanggapan>() {
            @Override
            public void onSuccess(Tanggapan data) {
                detailTanggapanView.setTitle("Tanggapan oleh Petugas: "+data.getPetugas().getNama());
                detailTanggapanView.getLabelPetugas().setText(data.getPetugas().getNama());
                detailTanggapanView.getLabelTanggal().setText(data.getDate());
                detailTanggapanView.getLabelIsiTanggapan().setText(data.getIsiTanggapan());
                detailTanggapanView.getLabelIsiLaporan().setText(data.getPengaduan().getIsiLaporan());
            }

            @Override
            public void onFailure(SQLException e) {
                detailTanggapanView.showErrorAlert("Gagal memuat detail tanggapan!");
            }
        });
    }
    
    
}

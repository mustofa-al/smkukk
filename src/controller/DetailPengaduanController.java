/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import model.Pengaduan;
import model.dao.PengaduanDAO;
import model.dao.ResultDataListener;
import view.DetailPengaduan;

/**
 *
 * @author A
 */
public class DetailPengaduanController {
    private DetailPengaduan detailPengaduanView;
    private int pengajuanId;
    private PengaduanDAO pengaduanDAO;

    public DetailPengaduanController(DetailPengaduan detailPengaduanView, int pengajuanId) {
        this.detailPengaduanView = detailPengaduanView;
        this.pengajuanId = pengajuanId;
        initDao();
        initView();
        initData();
    }

    private void initView() {
        detailPengaduanView.setVisible(true);
    }

    private void initData() {
        pengaduanDAO.getDetailPengaduan(pengajuanId, new ResultDataListener<Pengaduan>(){
            @Override
            public void onSuccess(Pengaduan data) {
                detailPengaduanView.setTitle("Pengaduan oleh "+data.getPelapor().getNama());
                detailPengaduanView.getLabelNama().setText(data.getPelapor().getNama());
                detailPengaduanView.getLabelTanggal().setText(data.getDate());
                detailPengaduanView.getLabelNoTelp().setText(data.getPelapor().getTelp());
                detailPengaduanView.getLabelIsiLaporan().setText(data.getIsiLaporan());
            }

            @Override
            public void onFailure(SQLException e) {
                detailPengaduanView.showErrorAlert("Gagal memuat data pengaduan!");
            }
            
        });
    }

    private void initDao() {
        pengaduanDAO = new PengaduanDAO();
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import model.Pelapor;
import model.Tanggapan;
import model.dao.ResultDataListener;
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
    }

    private void initListener() {
        
    }

    private void initData() {
        tanggapanDAO.getDetailTanggapan(tanggapan.getTanggapanId(), pelapor, new ResultDataListener<Tanggapan>() {
            @Override
            public void onSuccess(Tanggapan data) {
                detailTanggapanView.getLabelPetugas().setText(data.getPetugas().getNama());
                detailTanggapanView.getLabelTanggal().setText(data.getDate());
                detailTanggapanView.getLabelIsiTanggapan().setText(data.getIsiTanggapan());
                detailTanggapanView.getLabelIsiLaporan().setText(data.getPengaduan().getIsiLaporan());
            }

            @Override
            public void onFailure(SQLException e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
    
    
}

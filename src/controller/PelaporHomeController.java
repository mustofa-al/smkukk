/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
import model.Pelapor;
import model.TabelModelTanggapan;
import model.Tanggapan;
import model.dao.ResultDataListener;
import model.dao.TanggapanDAO;
import view.PelaporHome;
import view.PengaduanBaru;

/**
 *
 * @author A
 */
public class PelaporHomeController {
    private PelaporHome pelaporHomeView;
    private Pelapor pelapor;
    private TanggapanDAO tanggapanDAO;

    public PelaporHomeController(PelaporHome pelaporHome, Pelapor pelapor) {
        this.pelaporHomeView = pelaporHome;
        this.pelapor = pelapor;
        initDAO();
        initView();
        initListeners();
        initData();
    }

    private void initView() {
        pelaporHomeView.setVisible(true);
        pelaporHomeView.getLabelInfo().setText("Login sebagai: "+pelapor.getNama());
    }

    private void initListeners() {
        pelaporHomeView.getMenuPengaduanBaru().addActionListener((ae) -> {
            PengaduanBaru pengaduanBaru = new PengaduanBaru();
            new PengaduanBaruController(pengaduanBaru, pelapor);
        });
    }

    private void initDAO() {
        tanggapanDAO = new TanggapanDAO();
    }

    private void initData() {
        tanggapanDAO.getData(pelapor, new ResultDataListener<List<Tanggapan>>() {
            @Override
            public void onSuccess(List<Tanggapan> data) {
                pelaporHomeView.getTabelTanggapan().setModel(new TabelModelTanggapan(data));
            }

            @Override
            public void onFailure(SQLException e) {
                pelaporHomeView.showAlert("Gagal memuat tanggapan!");
            }
        });
    }
}

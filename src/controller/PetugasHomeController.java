/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Pengaduan;
import model.Petugas;
import model.StatusPetugas;
import model.TabelModelPengaduan;
import model.dao.PengaduanDAO;
import model.dao.PetugasDAO;
import model.dao.ResultDataListener;
import view.PetugasHome;

/**
 *
 * @author A
 */
public class PetugasHomeController {
    private PetugasHome petugasHomeView;
    private Petugas petugas;
    private PengaduanDAO pengaduanDAO;

    public PetugasHomeController(PetugasHome petugasHomeView, Petugas petugas) {
        this.petugasHomeView = petugasHomeView;
        this.petugas = petugas;
        initDao();
        initView();
        initData();
    }

    private void initView() {
        petugasHomeView.setVisible(true);
        String status = null;
        if (petugas.getStatus() == StatusPetugas.admin) {
            status = "Admin";
        } else if (petugas.getStatus() == StatusPetugas.petugas) {
            status = "Petugas";
        }
        petugasHomeView.getLabelInfo().setText("Login sebagai: "+petugas.getNama()+" ("+status+")");
    }

    private void initData() {
        pengaduanDAO.getData(new ResultDataListener<List<Pengaduan>>() {
            @Override
            public void onSuccess(List<Pengaduan> data) {
                petugasHomeView.getTabelPengaduan().setModel(new TabelModelPengaduan(data));
            }

            @Override
            public void onFailure(SQLException e) {
                petugasHomeView.showErrorAlert("Gagal memuat data Pengaduan!");
            }
        });
    }

    private void initDao() {
        pengaduanDAO = new PengaduanDAO();
    }
    
    
}

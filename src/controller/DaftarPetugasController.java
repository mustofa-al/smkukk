/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import java.util.List;
import model.Petugas;
import model.TabelModelPetugas;
import model.dao.PetugasDAO;
import model.dao.ResultDataListener;
import view.DaftarPetugas;

/**
 *
 * @author A
 */
public class DaftarPetugasController {
    private DaftarPetugas daftarPetugasView;
    private PetugasDAO petugasDAO;

    public DaftarPetugasController(DaftarPetugas daftarPetugasView) {
        this.daftarPetugasView = daftarPetugasView;
        initDAO();
        initView();
        initData();
    }

    private void initView() {
        daftarPetugasView.setVisible(true);
    }

    private void initDAO() {
        petugasDAO = new PetugasDAO();
    }

    private void initData() {
        petugasDAO.getPetugas(new ResultDataListener<List<Petugas>>() {
            @Override
            public void onSuccess(List<Petugas> data) {
                daftarPetugasView.getTabelPetugas().setModel(new TabelModelPetugas(data));
            }

            @Override
            public void onFailure(SQLException e) {
                if (e == null) {
                    daftarPetugasView.showAlert("Belum ada data untuk ditampilkan!");
                } else {
                    daftarPetugasView.showErrorAlert("Gagal memuat data petugas!");
                }
            }
        });
    }
    
    
}

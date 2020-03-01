/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Petugas;
import model.dao.PengaduanDAO;
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
        initView();
        initData();
        initDao();
    }

    private void initView() {
        petugasHomeView.setVisible(true);
        petugasHomeView.getLabelInfo().setText("Login sebagai: "+petugas.getNama());
    }

    private void initData() {
        
    }

    private void initDao() {
        pengaduanDAO = new PengaduanDAO();
    }
    
    
}

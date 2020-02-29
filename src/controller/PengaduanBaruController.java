/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Pelapor;
import view.PengaduanBaru;

/**
 *
 * @author A
 */
public class PengaduanBaruController {
    private PengaduanBaru pengaduanBaruView;
    private Pelapor laporan;

    public PengaduanBaruController(PengaduanBaru pengaduanBaruView, Pelapor laporan) {
        this.pengaduanBaruView = pengaduanBaruView;
        this.laporan = laporan;
        initView();
        initListeners();
    }

    private void initView() {
        pengaduanBaruView.setVisible(true);
    }

    private void initListeners() {
        
    }
}

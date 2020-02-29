/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Pelapor;
import view.PelaporHome;
import view.PengaduanBaru;

/**
 *
 * @author A
 */
public class PelaporHomeController {
    private PelaporHome pelaporHomeView;
    private Pelapor pelapor;

    public PelaporHomeController(PelaporHome pelaporHome, Pelapor pelapor) {
        this.pelaporHomeView = pelaporHome;
        this.pelapor = pelapor;
        initView();
        initListeners();
    }

    private void initView() {
        pelaporHomeView.setVisible(true);
    }

    private void initListeners() {
        pelaporHomeView.getButtonLaporanBaru().addActionListener((ae) -> {
            new PengaduanBaruController(new PengaduanBaru(), pelapor);
        });
    }
}

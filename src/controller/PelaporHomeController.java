/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.logging.Logger;
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
        pelaporHomeView.getLabelInfo().setText("Login sebagai: "+pelapor.getNama());
    }

    private void initListeners() {
        pelaporHomeView.getMenuPengaduanBaru().addActionListener((ae) -> {
            PengaduanBaru pengaduanBaru = new PengaduanBaru();
            pengaduanBaru.listener = new PengaduanBaru.Listener() {
                @Override
                public void onDisposed() {
                    // reload list
                }
            };
            new PengaduanBaruController(pengaduanBaru, pelapor);
        });
    }
}

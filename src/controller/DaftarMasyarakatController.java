/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import view.DaftarMasyarakat;
import view.LoginMasyarakat;

/**
 *
 * @author A
 */
public class DaftarMasyarakatController {
    private DaftarMasyarakat daftarMasyarakatView;

    public DaftarMasyarakatController(DaftarMasyarakat daftarMasyarakatView) {
        this.daftarMasyarakatView = daftarMasyarakatView;
        initView();
    }

    private void initView() {
        daftarMasyarakatView.setVisible(true);
    }
}

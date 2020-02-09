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
public class LoginMasyarakatController {
    private LoginMasyarakat loginMasyarakatView;

    public LoginMasyarakatController(LoginMasyarakat loginMasyarakatView) {
        this.loginMasyarakatView = loginMasyarakatView;
        initView();
        initListener();
    }

    private void initView() {
        loginMasyarakatView.setVisible(true);
    }

    private void initListener() {
       loginMasyarakatView.getButtonDaftar().addActionListener((ae) -> {
           openDaftarMasyarakat();
       });
    }

    private void openDaftarMasyarakat() {
        new DaftarMasyarakatController(new DaftarMasyarakat());
    }
}

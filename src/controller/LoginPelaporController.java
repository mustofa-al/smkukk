/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import view.RegisterPelapor;
import view.LoginPelapor;

/**
 *
 * @author A
 */
public class LoginPelaporController {
    private LoginPelapor loginPelaporView;

    public LoginPelaporController(LoginPelapor loginMasyarakatView) {
        this.loginPelaporView = loginMasyarakatView;
        initView();
        initListener();
    }

    private void initView() {
        loginPelaporView.setVisible(true);
    }

    private void initListener() {
       loginPelaporView.getButtonDaftar().addActionListener((ae) -> {
           openDaftarMasyarakat();
       });
    }

    private void openDaftarMasyarakat() {
        new RegisterPelaporController(new RegisterPelapor());
    }
}

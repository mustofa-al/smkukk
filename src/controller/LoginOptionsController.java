/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.LoginMasyarakat;
import view.LoginOptions;
import view.LoginPetugas;

/**
 *
 * @author A
 */
public class LoginOptionsController {
   private LoginOptions loginOptionsView;

    public LoginOptionsController(LoginOptions loginOptionsView) {
        this.loginOptionsView = loginOptionsView;
        initView();
        initListener();
    }

    private void initView() {
        loginOptionsView.setVisible(true);
    }

    private void initListener() {
        loginOptionsView.getLoginMasyarakatButton().addActionListener((ActionEvent ae) -> {
            openLoginMasyarakat();
        });
        loginOptionsView.getLoginPetugasButton().addActionListener((ActionEvent ae) -> {
            openLoginPetugas();
        });
    }

    private void openLoginMasyarakat() {
        new LoginMasyarakatController(new LoginMasyarakat());
        loginOptionsView.dispose();
    }

    private void openLoginPetugas() {
        new LoginPetugasController(new LoginPetugas());
        loginOptionsView.dispose();
    }
}

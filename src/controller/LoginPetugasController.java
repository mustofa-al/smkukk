/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import view.LoginPetugas;

/**
 *
 * @author A
 */
public class LoginPetugasController {
    private LoginPetugas loginPetugasView;

    public LoginPetugasController(LoginPetugas loginPetugasView) {
        this.loginPetugasView = loginPetugasView;
        initView();
    }

    private void initView() {
        loginPetugasView.setVisible(true);
    }
}

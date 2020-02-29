/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.RegisterPelaporController.isNumeric;
import java.sql.SQLException;
import model.dao.PelaporDAO;
import model.dao.ResultListener;
import view.RegisterPelapor;
import view.LoginPelapor;

/**
 *
 * @author A
 */
public class LoginPelaporController {
    private LoginPelapor loginPelaporView;
    private PelaporDAO pelaporDAO;

    public LoginPelaporController(LoginPelapor loginPelaporView) {
        this.loginPelaporView = loginPelaporView;
        initView();
        initListener();
        initDAO();
    }

    private void initView() {
        loginPelaporView.setVisible(true);
    }

    private void initListener() {
       loginPelaporView.getButtonDaftar().addActionListener((ae) -> {
           openDaftarPelapor();
       });
       loginPelaporView.getButtonMasuk().addActionListener((ae) -> {
           if (isInputValid()) {
               pelaporDAO.login(
                       loginPelaporView.getFieldNik().getText().toString(),
                       loginPelaporView.getFieldPassword().getText().toString(),
                       new ResultListener() {
                   @Override
                   public void onSuccess() {
                       loginPelaporView.showAlert("success");
                   }

                   @Override
                   public void onFailure(SQLException e) {
                       loginPelaporView.showErrorAlert("Gagal login. Cek kembali kombinasi NIK & password Anda!");
                   }
               });
           }
       });
    }

    private void openDaftarPelapor() {
        new RegisterPelaporController(new RegisterPelapor());
    }

    private boolean isInputValid() {
        boolean isValid = false;
        if (loginPelaporView.getFieldNik().getText().length() == 0) {
            loginPelaporView.showAlert("NIK tidak boleh kosong");
        } else {
            if (loginPelaporView.getFieldNik().getText().length() < 16) {
                loginPelaporView.showAlert("Format NIK tidak sesuai");
            } else {
                if (!isNumeric(loginPelaporView.getFieldNik().getText())) {
                    loginPelaporView.showAlert("Format NIK tidak sesuai");
                } else {
                    if (loginPelaporView.getFieldPassword().getText().length() == 0) {
                        loginPelaporView.showAlert("Password tidak boleh kosong");
                    } else {
                        isValid = true;
                    }
                }
            }
        }
        return isValid;
    }
    
    private void initDAO() {
        pelaporDAO = new PelaporDAO();
    }
}

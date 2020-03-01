/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.sql.SQLException;
import model.Petugas;
import model.dao.PelaporDAO;
import model.dao.PetugasDAO;
import model.dao.ResultDataListener;
import model.dao.ResultListener;
import view.LoginPetugas;
import view.PetugasHome;

/**
 *
 * @author A
 */
public class LoginPetugasController {
    private LoginPetugas loginPetugasView;
    private PetugasDAO petugasDAO;

    public LoginPetugasController(LoginPetugas loginPetugasView) {
        this.loginPetugasView = loginPetugasView;
        initView();
        initListeners();
        initDAO();
    }

    private void initView() {
        loginPetugasView.setVisible(true);
    }
    
    private void initDAO() {
        petugasDAO = new PetugasDAO();
    }

    private void initListeners() {
        loginPetugasView.getButtonMasuk().addActionListener((ae) -> {
            if (isInputValid()) {
                petugasDAO.login(
                        loginPetugasView.getFieldUsername().getText().toString(),
                        loginPetugasView.getFieldPassword().getText().toString(), 
                        new ResultDataListener<Petugas>() {
                    @Override
                    public void onSuccess(Petugas petugas) {
                        new PetugasHomeController(new PetugasHome(), petugas);
                        loginPetugasView.dispose();
                    }

                    @Override
                    public void onFailure(SQLException e) {
                        loginPetugasView.showErrorAlert("Gagal login. Cek kembali kombinasi NIK & password Anda!");
                    }
                });
            }
        });
    }

    private boolean isInputValid() {
        boolean isValid = false;
        if (loginPetugasView.getFieldUsername().getText().length() == 0) {
            loginPetugasView.showAlert("Username tidak boleh kosong");
        } else {
            if (loginPetugasView.getFieldPassword().getText().length() == 0) {
                loginPetugasView.showAlert("Password tidak boleh kosong");
            } else {
                isValid = true;
            }
        }
        return isValid;
    }
}

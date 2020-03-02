/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Petugas;
import model.dao.PelaporDAO;
import model.dao.PetugasDAO;
import model.dao.ResultListener;
import view.TambahPetugas;

/**
 *
 * @author A
 */
public class TambahPetugasController {
    private TambahPetugas tambahPetugasView;
    private PetugasDAO petugasDAO;

    public TambahPetugasController(TambahPetugas tambahPetugasView) {
        this.tambahPetugasView = tambahPetugasView;
        initView();
        initDAO();
        initListener();
    }

    private void initView() {
        tambahPetugasView.setVisible(true);
    }

    private void initListener() {
        tambahPetugasView.getButtonTambah().addActionListener((ae) -> {
            if (isValidInput()) {
                Petugas petugas = new Petugas();
                petugas.setNama(tambahPetugasView.getFieldNamaPetugas().getText());
                petugas.setUsername(tambahPetugasView.getFieldUsername().getText());
                petugas.setTelp(tambahPetugasView.getFieldTelp().getText());
                petugas.setPassword(tambahPetugasView.getFieldPassword().getText());
                petugasDAO.insert(petugas, new ResultListener() {
                    @Override
                    public void onSuccess() {
                        tambahPetugasView.showAlert("Petugas baru ditambahkan!");
                        tambahPetugasView.dispose();
                    }

                    @Override
                    public void onFailure(SQLException e) {
                        tambahPetugasView.showErrorAlert("Gagal menambahkan petugas baru!");
                    }
                });
            }
        });
    }

    private boolean isValidInput() {
        boolean isValid = false;
        if (tambahPetugasView.getFieldNamaPetugas().getText().length() == 0) {
            tambahPetugasView.showAlert("Nama tidak boleh kosong!");
        } else {
            if (tambahPetugasView.getFieldUsername().getText().length() == 0) {
                tambahPetugasView.showAlert("Username tidak boleh kosong!");
            } else {
                if (tambahPetugasView.getFieldTelp().getText().length() == 0) {
                    tambahPetugasView.showAlert("Nomor telpon tidak boleh kosong!");
                } else {
                    if (tambahPetugasView.getFieldPassword().getText().length() == 0) {
                        tambahPetugasView.showAlert("Password tidak boleh kosong!");
                    } else {
                        isValid = true;
                    }
                }
            }
        }
        return isValid;
    }

    private void initDAO() {
        petugasDAO = new PetugasDAO();
    }
    
    
}

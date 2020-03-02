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
import model.StatusPetugas;
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
    private Petugas petugas;
    Listener listener;

    public TambahPetugasController(TambahPetugas tambahPetugasView) {
        this.tambahPetugasView = tambahPetugasView;
        initView();
        initDAO();
        initListener();
    }
    
    public TambahPetugasController(TambahPetugas tambahPetugasView, Petugas petugas) {
        this.tambahPetugasView = tambahPetugasView;
        this.petugas = petugas;
        initView();
        initDAO();
        initListener();
    }

    private void initView() {
        tambahPetugasView.setVisible(true);
        if (petugas != null) {
            bindPetugas(petugas);
        }
    }

    private void initListener() {
        tambahPetugasView.getButtonTambah().addActionListener((ae) -> {
            if (isValidInput()) {
                if (petugas != null) {
                    updatePetugas(petugas);
                } else {
                    createNew();
                }
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

    private void bindPetugas(Petugas petugas) {
        tambahPetugasView.getButtonTambah().setText("Simpan");
        tambahPetugasView.getFieldNamaPetugas().setText(petugas.getNama());
        tambahPetugasView.getFieldUsername().setText(petugas.getUsername());
        tambahPetugasView.getFieldTelp().setText(petugas.getTelp());
        if (petugas.getStatus() == StatusPetugas.admin) {
            tambahPetugasView.getComboBoxLevel().setSelectedIndex(0);
        } else if (petugas.getStatus() == StatusPetugas.petugas) {
            tambahPetugasView.getComboBoxLevel().setSelectedIndex(1);
        }
    }

    private void createNew() {
        Petugas petugas = new Petugas();
        petugas.setNama(tambahPetugasView.getFieldNamaPetugas().getText());
        petugas.setUsername(tambahPetugasView.getFieldUsername().getText());
        petugas.setTelp(tambahPetugasView.getFieldTelp().getText());
        petugas.setPassword(tambahPetugasView.getFieldPassword().getText());
        if (tambahPetugasView.getComboBoxLevel().getSelectedItem().toString() == "Admin") {
            petugas.setStatus(StatusPetugas.admin);
        } else if (tambahPetugasView.getComboBoxLevel().getSelectedItem().toString() == "Petugas"){
            petugas.setStatus(StatusPetugas.petugas);
        }
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

    private void updatePetugas(Petugas petugas) {
        petugas.setNama(tambahPetugasView.getFieldNamaPetugas().getText());
        petugas.setUsername(tambahPetugasView.getFieldUsername().getText());
        petugas.setTelp(tambahPetugasView.getFieldTelp().getText());
        petugas.setPassword(tambahPetugasView.getFieldPassword().getText());
        if (tambahPetugasView.getComboBoxLevel().getSelectedItem().toString() == "Admin") {
            petugas.setStatus(StatusPetugas.admin);
        } else if (tambahPetugasView.getComboBoxLevel().getSelectedItem().toString() == "Petugas"){
            petugas.setStatus(StatusPetugas.petugas);
        }
        petugasDAO.update(petugas, new ResultListener() {
            @Override
            public void onSuccess() {
                tambahPetugasView.showAlert("Data petugas berhasil diperbarui!");
                tambahPetugasView.dispose();
                listener.onDisposed();
            }

            @Override
            public void onFailure(SQLException e) {
                tambahPetugasView.showErrorAlert("Gagal memperbarui data petugas!");
            }
        });
    }
    
    public interface Listener {

        public void onDisposed();
        
    }
}

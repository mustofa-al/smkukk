/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static com.sun.org.apache.xerces.internal.util.FeatureState.is;
import com.sun.xml.internal.ws.util.StringUtils;
import model.dao.PelaporDAO;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import jdk.nashorn.internal.runtime.JSType;
import model.Pelapor;
import model.dao.ResultListener;
import view.RegisterPelapor;
import view.LoginPelapor;
import view.PelaporHome;

/**
 *
 * @author A
 */
public class RegisterPelaporController {
    private RegisterPelapor registerPelaporView;
    private PelaporDAO pelaporDAO;

    public RegisterPelaporController(RegisterPelapor daftarMasyarakatView) {
        this.registerPelaporView = daftarMasyarakatView;
        initView();
        initListeners();
        initDAO();
    }

    private void initView() {
        registerPelaporView.setVisible(true);
    }

    private void initListeners() {
        registerPelaporView.getBtnDaftar().addActionListener((ActionEvent ae) -> {
            if(isInputValid()) {
                Pelapor pelapor = new Pelapor();
                pelapor.setNik(registerPelaporView.getFieldNik().getText());
                pelapor.setNama(registerPelaporView.getFieldNama().getText());
                pelapor.setUsername(registerPelaporView.getFieldUsername().getText());
                pelapor.setPassword(registerPelaporView.getFieldPassword().getText());
                pelapor.setTelp(registerPelaporView.getFieldTelp().getText());
                pelaporDAO.insert(pelapor, new ResultListener() {
                    @Override
                    public void onSuccess() {
                        registerPelaporView.showAlert("Berhasil mendaftar");
                        new PelaporHomeController(new PelaporHome(), pelapor);
                        registerPelaporView.dispose();
                    }

                    @Override
                    public void onFailure(SQLException e) {
                        registerPelaporView.showErrorAlert("Gagal mendaftar. Cek kembali data yang Anda masukkan!");
                    }
                });
            }
        });
    }

    private boolean isInputValid() {
        boolean isValid = false;
        if (registerPelaporView.getFieldNik().getText().length() == 0) {
            registerPelaporView.showAlert("NIK tidak boleh kosong");
        } else {
            if (registerPelaporView.getFieldNik().getText().length() < 16) {
                registerPelaporView.showAlert("Format NIK tidak sesuai");
            } else {
                if (!isNumeric(registerPelaporView.getFieldNik().getText())) {
                    registerPelaporView.showAlert("Format NIK tidak sesuai");
                } else {
                    if (registerPelaporView.getFieldNama().getText().length() == 0) {
                        registerPelaporView.showAlert("Nama tidak boleh kosong");
                    } else {
                        if (registerPelaporView.getFieldUsername().getText().length() == 0) {
                            registerPelaporView.showAlert("Username tidak boleh kosong");
                        } else {
                            if (registerPelaporView.getFieldPassword().getText().length() == 0) {
                                registerPelaporView.showAlert("Password tidak boleh kosong");
                            } else {
                                if (registerPelaporView.getFieldTelp().getText().length() == 0) {
                                    registerPelaporView.showAlert("Nomor telp. tidak boleh kosong");
                                } else {
                                    isValid = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return isValid;
    }
    
    public static boolean isNumeric(String str) { 
        try {  
          Double.parseDouble(str);  
          return true;
        } catch(NumberFormatException e){  
          return false;  
        }  
     }

    private void initDAO() {
        pelaporDAO = new PelaporDAO();
    }
}

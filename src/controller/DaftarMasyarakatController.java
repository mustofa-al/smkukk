/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.sun.xml.internal.ws.util.StringUtils;
import db.MasyarakatDAO;
import java.awt.event.ActionEvent;
import jdk.nashorn.internal.runtime.JSType;
import model.Masyarakat;
import view.DaftarMasyarakat;
import view.LoginMasyarakat;

/**
 *
 * @author A
 */
public class DaftarMasyarakatController {
    private DaftarMasyarakat daftarMasyarakatView;
    private MasyarakatDAO masyarakatDAO;

    public DaftarMasyarakatController(DaftarMasyarakat daftarMasyarakatView) {
        this.daftarMasyarakatView = daftarMasyarakatView;
        initView();
        initListeners();
//        initDAO();
    }

    private void initView() {
        daftarMasyarakatView.setVisible(true);
    }

    private void initListeners() {
        daftarMasyarakatView.getBtnDaftar().addActionListener((ActionEvent ae) -> {
            if(isInputValid()) {
                Masyarakat masyarakat = new Masyarakat();
                masyarakat.setNik(daftarMasyarakatView.getFieldNik().getText());
                masyarakat.setNama(daftarMasyarakatView.getFieldNama().getText());
                masyarakat.setUsername(daftarMasyarakatView.getFieldUsername().getText());
                masyarakat.setPassword(daftarMasyarakatView.getFieldPassword().getText());
                masyarakat.setTelp(daftarMasyarakatView.getFieldTelp().getText());
            }
        });
    }

    private boolean isInputValid() {
        boolean isValid = false;
        if (daftarMasyarakatView.getFieldNik().getText().length() == 0) {
            daftarMasyarakatView.showAlert("NIK tidak boleh kosong");
        } else {
            if (daftarMasyarakatView.getFieldNik().getText().length() < 16) {
                daftarMasyarakatView.showAlert("Format NIK tidak sesuai");
            } else {
                if (!isNumeric(daftarMasyarakatView.getFieldNik().getText())) {
                    daftarMasyarakatView.showAlert("Format NIK tidak sesuai");
                } else {
                    if (daftarMasyarakatView.getFieldNama().getText().length() == 0) {
                        daftarMasyarakatView.showAlert("Nama tidak boleh kosong");
                    } else {
                        if (daftarMasyarakatView.getFieldUsername().getText().length() == 0) {
                            daftarMasyarakatView.showAlert("Username tidak boleh kosong");
                        } else {
                            if (daftarMasyarakatView.getFieldPassword().getText().length() == 0) {
                                daftarMasyarakatView.showAlert("Password tidak boleh kosong");
                            } else {
                                if (daftarMasyarakatView.getFieldTelp().getText().length() == 0) {
                                    daftarMasyarakatView.showAlert("Nomor telp. tidak boleh kosong");
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

//    private void initDAO() {
//        masyarakatDAO = new MasyarakatDAO();
//    }
}

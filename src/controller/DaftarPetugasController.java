/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import model.Petugas;
import model.TabelModelPetugas;
import model.dao.PetugasDAO;
import model.dao.ResultDataListener;
import model.dao.ResultListener;
import view.DaftarPetugas;

/**
 *
 * @author A
 */
public class DaftarPetugasController {
    private DaftarPetugas daftarPetugasView;
    private PetugasDAO petugasDAO;
    private List<Petugas> listPetugas;
    private Petugas selected;

    public DaftarPetugasController(DaftarPetugas daftarPetugasView) {
        this.daftarPetugasView = daftarPetugasView;
        initDAO();
        initView();
        initData();
        initListener();
    }

    private void initView() {
        daftarPetugasView.setVisible(true);
    }

    private void initDAO() {
        petugasDAO = new PetugasDAO();
    }

    private void initData() {
        petugasDAO.getPetugas(new ResultDataListener<List<Petugas>>() {
            @Override
            public void onSuccess(List<Petugas> data) {
                listPetugas = data;
                daftarPetugasView.getTabelPetugas().setModel(new TabelModelPetugas(data));
            }

            @Override
            public void onFailure(SQLException e) {
                if (e == null) {
                    daftarPetugasView.showAlert("Belum ada data untuk ditampilkan!");
                } else {
                    daftarPetugasView.showErrorAlert("Gagal memuat data petugas!");
                }
            }
        });
    }

    private void initListener() {
        daftarPetugasView.getTabelPetugas().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (table.getSelectedRow() != -1) {
                    selected = listPetugas.get(row);
                        enableButtons(true);
                }
            }
        });
        
        daftarPetugasView.getButtonHapus().addActionListener((ae) -> {
            if (daftarPetugasView.showDeleteConfirmation() == JOptionPane.YES_OPTION) {
                petugasDAO.delete(selected.getId(), new ResultListener() {
                    @Override
                    public void onSuccess() {
                        daftarPetugasView.showAlert(selected.getNama()+" dihapus dari daftar petugas!");
                        initData();
                    }

                    @Override
                    public void onFailure(SQLException e) {
                        daftarPetugasView.showErrorAlert("Gagal menghapus petugas!");
                    }
                });
            }
        });
    }
    
    private void enableButtons(boolean b) {
        daftarPetugasView.getButtonUbah().setEnabled(b);
        daftarPetugasView.getButtonHapus().setEnabled(b);
    }
    
    
}

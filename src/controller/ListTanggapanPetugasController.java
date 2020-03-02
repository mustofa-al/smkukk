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
import model.StatusPetugas;
import model.TabelModelListTanggapanPetugas;
import model.Tanggapan;
import model.dao.ResultDataListener;
import model.dao.ResultListener;
import model.dao.TanggapanDAO;
import view.DetailPengaduan;
import view.DetailTanggapan;
import view.ListTanggapanPetugas;
import view.TanggapanBaru;

/**
 *
 * @author A
 */
public class ListTanggapanPetugasController {
    private ListTanggapanPetugas listTanggapanPetugasView;
    private Petugas petugas;
    private TanggapanDAO tanggapanDAO;
    private List<Tanggapan> listTanggapan;
    private Tanggapan selected;

    public ListTanggapanPetugasController(ListTanggapanPetugas listTanggapanPetugasView, Petugas petugas) {
        this.listTanggapanPetugasView = listTanggapanPetugasView;
        this.petugas = petugas;
        initDAO();
        initView();
        initData();
        initListener();
    }

    private void initView() {
        listTanggapanPetugasView.setVisible(true);
        String status = null;
        if (petugas.getStatus() == StatusPetugas.admin) {
            status = "Admin";
        } else if (petugas.getStatus() == StatusPetugas.petugas) {
            status = "Petugas";
        }
        listTanggapanPetugasView.getLabelInfo().setText("Login sebagai: " + petugas.getNama() + " (" + status + ")");
    }

    private void initDAO() {
        tanggapanDAO = new TanggapanDAO();
    }

    private void initData() {
        tanggapanDAO.getTanggapanPetugas(petugas, new ResultDataListener<List<Tanggapan>>() {
            @Override
            public void onSuccess(List<Tanggapan> data) {
                listTanggapan = data;
                listTanggapanPetugasView.getTabelTanggapan().setModel(new TabelModelListTanggapanPetugas(data));
            }

            @Override
            public void onFailure(SQLException e) {
                if (e==null) {
                    listTanggapanPetugasView.showAlert("Tidak ada tanggapan untuk ditampilkan!");
                } else {
                    listTanggapanPetugasView.showErrorAlert("Gagal memuat tanggapan!");
                }
            }
        });
    }

    private void initListener() {
        listTanggapanPetugasView.getTabelTanggapan().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (table.getSelectedRow() != -1) {
                    selected = listTanggapan.get(row);
                        enableButtons(true);
                }
            }
        });
        
        listTanggapanPetugasView.getButtonLihatDetail().addActionListener((ae) -> {
            new DetailTanggapanController(new DetailTanggapan(), null, selected);
        });
        
        listTanggapanPetugasView.getButtonUbah().addActionListener((ae) -> {
            new TanggapanBaruController(new TanggapanBaru(), selected, petugas).listener = new TanggapanBaruController.Listener() {
                @Override
                public void onDisposed() {
                    initData();
                }
            };
        });
        
        listTanggapanPetugasView.getButtonHapus().addActionListener((ae) -> {
            int selection = listTanggapanPetugasView.showDeleteConfirmation();
            if (selection==JOptionPane.YES_OPTION) {
                tanggapanDAO.delete(selected, new ResultListener() {
                    @Override
                    public void onSuccess() {
                        listTanggapanPetugasView.showAlert("Pengaduan terhapus!");
                        initData();
                    }

                    @Override
                    public void onFailure(SQLException e) {
                        listTanggapanPetugasView.showErrorAlert("Gagal menghapus pengaduan!");
                    }
                });
            }
        });
    }
    
    private void enableButtons(boolean b) {
        listTanggapanPetugasView.getButtonUbah().setEnabled(b);
        listTanggapanPetugasView.getButtonHapus().setEnabled(b);
        listTanggapanPetugasView.getButtonLihatDetail().setEnabled(b);
    }
}

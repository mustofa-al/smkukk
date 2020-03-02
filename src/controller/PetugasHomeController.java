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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import model.Pengaduan;
import model.Petugas;
import model.StatusPetugas;
import model.TabelModelPengaduan;
import model.dao.PengaduanDAO;
import model.dao.PetugasDAO;
import model.dao.ResultDataListener;
import view.DaftarPetugas;
import view.DetailPengaduan;
import view.ListTanggapanPetugas;
import view.PengaduanBaru;
import view.PengaduanSelesai;
import view.PetugasHome;
import view.TambahPetugas;

/**
 *
 * @author A
 */
public class PetugasHomeController {

    private PetugasHome petugasHomeView;
    private Petugas petugas;
    private PengaduanDAO pengaduanDAO;
    private List<Pengaduan> listPengaduan = new ArrayList<>();

    public PetugasHomeController(PetugasHome petugasHomeView, Petugas petugas) {
        this.petugasHomeView = petugasHomeView;
        this.petugas = petugas;
        initDao();
        initView();
        initData();
        initListener();
    }

    private void initView() {
        petugasHomeView.setVisible(true);
        String status = null;
        if (petugas.getStatus() == StatusPetugas.admin) {
            status = "Admin";
            petugasHomeView.getMenuPetugas().setVisible(true);
        } else if (petugas.getStatus() == StatusPetugas.petugas) {
            status = "Petugas";
            petugasHomeView.getMenuPetugas().setVisible(false);
        }
        petugasHomeView.getLabelInfo().setText("Login sebagai: " + petugas.getNama() + " (" + status + ")");
    }

    private void initData() {
        pengaduanDAO.getData(new ResultDataListener<List<Pengaduan>>() {
            @Override
            public void onSuccess(List<Pengaduan> data) {
                listPengaduan = data;
                petugasHomeView.getTabelPengaduan().setModel(new TabelModelPengaduan(data));
            }

            @Override
            public void onFailure(SQLException e) {
                if (e != null) {
                    petugasHomeView.showErrorAlert("Gagal memuat data Pengaduan!");
                } else {
                    petugasHomeView.showAlert("Belum ada data untuk ditampilkan!");
                }
            }
        });
    }

    private void initDao() {
        pengaduanDAO = new PengaduanDAO();
    }

    private void initListener() {
        petugasHomeView.getTabelPengaduan().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    new DetailPengaduanController(new DetailPengaduan(), listPengaduan.get(row).getId(), petugas).listener 
                            = new DetailPengaduanController.Listener() {
                        @Override
                        public void onDisposed() {
                            initData();
                        }
                    };
                }
            }
        });
        
        petugasHomeView.getMenuPengaduanSelesai().addActionListener((ae) -> {
            new PengaduanSelesaiController(new PengaduanSelesai(), petugas);
        });
        
        petugasHomeView.getMenuTambahPetugas().addActionListener((ae) -> {
            new TambahPetugasController(new TambahPetugas());
        });
        
        petugasHomeView.getMenuTanggapanSaya().addActionListener((ae) -> {
            new ListTanggapanPetugasController(new ListTanggapanPetugas(), petugas);
        });
        
        petugasHomeView.getMenuDaftarPetugas().addActionListener((ae) -> {
            new DaftarPetugasController(new DaftarPetugas());
        });
    }

}

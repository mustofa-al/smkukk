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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import model.Pengaduan;
import model.Petugas;
import model.StatusPengaduan;
import model.TabelModelPengaduanSelesai;
import model.dao.PengaduanDAO;
import model.dao.PetugasDAO;
import model.dao.ResultDataListener;
import view.DetailPengaduan;
import view.PengaduanSelesai;

/**
 *
 * @author A
 */
public class PengaduanSelesaiController {
    private PengaduanSelesai pengaduanSelesaiView;
    private Petugas petugas;
    private PengaduanDAO pengaduanDAO;
    private List<Pengaduan> listPengaduan;
    private Pengaduan selected;

    public PengaduanSelesaiController(PengaduanSelesai pengaduanSelesaiView, Petugas petugas) {
        this.pengaduanSelesaiView = pengaduanSelesaiView;
        this.petugas = petugas;
        initDao();
        initView();
        initData();
        initListener();
    }

    private void initView() {
        pengaduanSelesaiView.setVisible(true);
    }

    private void initDao() {
        pengaduanDAO = new PengaduanDAO();
    }

    private void initData() {
        pengaduanDAO.getPengaduanSelesai(new ResultDataListener<List<Pengaduan>>() {
            @Override
            public void onSuccess(List<Pengaduan> data) {
                listPengaduan = data;
                pengaduanSelesaiView.getTabelPengaduan().setModel(new TabelModelPengaduanSelesai(data));
            }

            @Override
            public void onFailure(SQLException e) {
                pengaduanSelesaiView.showErrorAlert("Gagal memuat data pengaduan!");
            }
        });
    }

    private void initListener() {
        pengaduanSelesaiView.getTabelPengaduan().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (table.getSelectedRow() != -1) {
                    if (listPengaduan.get(row).getStatus() == StatusPengaduan.terkirim) {
                        selected = listPengaduan.get(row);
                        pengaduanSelesaiView.getButtonLihatDetail().setEnabled(true);
                    } else {
                        selected = listPengaduan.get(row);
                        pengaduanSelesaiView.getButtonLihatDetail().setEnabled(true);
                    }
                }
            }
        });
        pengaduanSelesaiView.getButtonLihatDetail().addActionListener((ae) -> {
            new DetailPengaduanController(new DetailPengaduan(), selected.getId(), null);
        });
    }
    
    
}

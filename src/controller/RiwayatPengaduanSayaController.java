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
import javax.swing.JTable;
import model.Pelapor;
import model.Pengaduan;
import model.StatusPengaduan;
import model.TabelModelPengaduanSaya;
import model.dao.PengaduanDAO;
import model.dao.ResultDataListener;
import view.DetailPengaduan;
import view.RiwayatPengaduanSaya;

/**
 *
 * @author A
 */
public class RiwayatPengaduanSayaController {
    private RiwayatPengaduanSaya pengaduanSayaView;
    private Pelapor pelapor;
    private PengaduanDAO pengaduanDAO;
    private List<Pengaduan> listPengaduan;

    public RiwayatPengaduanSayaController(RiwayatPengaduanSaya pengaduanSayaView, Pelapor pelapor) {
        this.pengaduanSayaView = pengaduanSayaView;
        this.pelapor = pelapor;
        initDAO();
        initView();
        initData();
        initListener();
    }

    private void initDAO() {
        pengaduanDAO = new PengaduanDAO();
    }

    private void initView() {
        pengaduanSayaView.setVisible(true);
        pengaduanSayaView.getLabelInfo().setText("Pengaduan oleh: "+pelapor.getNama());
    }

    private void initData() {
        pengaduanDAO.getData(pelapor.getNik(), new ResultDataListener<List<Pengaduan>>() {
            @Override
            public void onSuccess(List<Pengaduan> data) {
                listPengaduan = data;
                pengaduanSayaView.getTabelPengaduan().setModel(new TabelModelPengaduanSaya(data));
            }

            @Override
            public void onFailure(SQLException e) {
                if (e != null) {
                    pengaduanSayaView.showErrorAlert("Gagal memuat riwayat pengaduan saya!");
                } else {
                    pengaduanSayaView.showAlert("Belum ada data untuk ditampilkan");
                }
            }
        });
    }

    private void initListener() {
        pengaduanSayaView.getTabelPengaduan().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (table.getSelectedRow() != -1) {
                    if (listPengaduan.get(row).getStatus() == StatusPengaduan.terkirim) {
                        enableButtons(true);
                    } else {
                        enableButtons(false);
                    }
                }
            }
        });
        pengaduanSayaView.getButtonDelete().addActionListener((ae) -> {
            // delete
        });
        pengaduanSayaView.getButtonEdit().addActionListener((ae) -> {
            // update
        });
    }
    
    private void enableButtons(boolean b) {
        pengaduanSayaView.getButtonEdit().setEnabled(b);
        pengaduanSayaView.getButtonDelete().setEnabled(b);
    }
}

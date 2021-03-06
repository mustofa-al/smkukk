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
import model.Pelapor;
import model.Pengaduan;
import model.StatusPengaduan;
import model.TabelModelPengaduanSaya;
import model.dao.PengaduanDAO;
import model.dao.ResultDataListener;
import model.dao.ResultListener;
import view.DetailPengaduan;
import view.PengaduanBaru;
import view.RiwayatPengaduanSaya;

/**
 *
 * @author A
 */
public class RiwayatPengaduanSayaController {
    private RiwayatPengaduanSaya pengaduanSayaView;
    private Pelapor pelapor;
    private Pengaduan selected;
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
        pengaduanDAO.getDataByPelapor(pelapor.getNik(), new ResultDataListener<List<Pengaduan>>() {
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
                        selected = listPengaduan.get(row);
                        enableButtons(true);
                        pengaduanSayaView.getButtonLihatDetail().setEnabled(true);
                    } else {
                        selected = listPengaduan.get(row);
                        enableButtons(false);
                        pengaduanSayaView.getButtonLihatDetail().setEnabled(true);
                    }
                }
            }
        });
        pengaduanSayaView.getButtonLihatDetail().addActionListener((ae) -> {
            new DetailPengaduanController(new DetailPengaduan(), selected.getId(), null);
        });
        pengaduanSayaView.getButtonDelete().addActionListener((ae) -> {
            int selection = pengaduanSayaView.showDeleteConfirmation();
            if (selection==JOptionPane.YES_OPTION) {
                pengaduanDAO.delete(selected, new ResultListener() {
                    @Override
                    public void onSuccess() {
                        pengaduanSayaView.showAlert("Pengaduan terhapus!");
                        initData();
                    }

                    @Override
                    public void onFailure(SQLException e) {
                        pengaduanSayaView.showErrorAlert("Gagal menghapus pengaduan!");
                    }
                });
            }
        });
        pengaduanSayaView.getButtonEdit().addActionListener((ae) -> {
            new PengaduanBaruController(new PengaduanBaru(), pelapor, selected).listener = new PengaduanBaruController.Listener() {
                @Override
                public void onDisposed() {
                    initData();
                }
            };
        });
    }
    
    private void enableButtons(boolean b) {
        pengaduanSayaView.getButtonEdit().setEnabled(b);
        pengaduanSayaView.getButtonDelete().setEnabled(b);
    }
}

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
import java.util.logging.Logger;
import javax.swing.JTable;
import model.Pelapor;
import model.TabelModelTanggapan;
import model.Tanggapan;
import model.dao.ResultDataListener;
import model.dao.TanggapanDAO;
import view.DetailPengaduan;
import view.DetailTanggapan;
import view.PelaporHome;
import view.PengaduanBaru;

/**
 *
 * @author A
 */
public class PelaporHomeController {
    private PelaporHome pelaporHomeView;
    private Pelapor pelapor;
    private TanggapanDAO tanggapanDAO;
    private List<Tanggapan> listTanggapan = new ArrayList<Tanggapan>();

    public PelaporHomeController(PelaporHome pelaporHome, Pelapor pelapor) {
        this.pelaporHomeView = pelaporHome;
        this.pelapor = pelapor;
        initDAO();
        initView();
        initListeners();
        initData();
    }

    private void initView() {
        pelaporHomeView.setVisible(true);
        pelaporHomeView.getLabelInfo().setText("Login sebagai: "+pelapor.getNama());
    }

    private void initListeners() {
        pelaporHomeView.getMenuPengaduanBaru().addActionListener((ae) -> {
            PengaduanBaru pengaduanBaru = new PengaduanBaru();
            new PengaduanBaruController(pengaduanBaru, pelapor);
        });
        pelaporHomeView.getTabelTanggapan().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    new DetailTanggapanController(new DetailTanggapan(),  pelapor, listTanggapan.get(row))
                            .listener = new DetailTanggapanController.Listener() {
                        @Override
                        public void onDisposed() {
                            initData();
                        }
                    };
                }
            }
        });
    }

    private void initDAO() {
        tanggapanDAO = new TanggapanDAO();
    }

    private void initData() {
        tanggapanDAO.getData(pelapor, new ResultDataListener<List<Tanggapan>>() {
            @Override
            public void onSuccess(List<Tanggapan> data) {
                listTanggapan = data;
                pelaporHomeView.getTabelTanggapan().setModel(new TabelModelTanggapan(data));
            }

            @Override
            public void onFailure(SQLException e) {
                if (e!= null) {
                    pelaporHomeView.showAlert("Gagal memuat tanggapan!");
                } else {
                    pelaporHomeView.showAlert("Belum ada data untuk ditampilkan!");
                }
            }
        });
    }
}

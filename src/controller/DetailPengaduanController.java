/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.sun.istack.internal.Nullable;
import config.DateTools;
import config.FileHelper;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import model.Pengaduan;
import model.Petugas;
import model.dao.PengaduanDAO;
import model.dao.ResultDataListener;
import view.DetailPengaduan;
import view.TanggapanBaru;

/**
 *
 * @author A
 */
public class DetailPengaduanController {
    private DetailPengaduan detailPengaduanView;
    private int pengaduanId;
    private Petugas petugas;
    private PengaduanDAO pengaduanDAO;
    Listener listener;

    public DetailPengaduanController(DetailPengaduan detailPengaduanView, int pengaduanId,@Nullable Petugas petugas) {
        this.detailPengaduanView = detailPengaduanView;
        this.pengaduanId = pengaduanId;
        this.petugas = petugas;
        initDao();
        initView();
        initData();
        initListener();
    }

    private void initView() {
        detailPengaduanView.setVisible(true);
        if (petugas == null) {
            detailPengaduanView.getButtonTanggapan().setVisible(false);
        } else {
            detailPengaduanView.getButtonTanggapan().setVisible(true);
        }
    }

    private void initData() {
        pengaduanDAO.getDetailPengaduan(pengaduanId, new ResultDataListener<Pengaduan>(){
            @Override
            public void onSuccess(Pengaduan data) {
                detailPengaduanView.setTitle("Pengaduan oleh "+data.getPelapor().getNama());
                detailPengaduanView.getLabelNama().setText(data.getPelapor().getNama());
                detailPengaduanView.getLabelTanggal().setText(data.getDate());
                detailPengaduanView.getLabelNoTelp().setText(data.getPelapor().getTelp());
                detailPengaduanView.getLabelIsiLaporan().setText(data.getIsiLaporan());
                if (data.getFoto() != null) {
                    BufferedImage bi = null;
                    try {
                        bi = ImageIO.read(new FileHelper().byteToInputStream(data.getFoto()));
                    } catch (IOException ex) {
                        Logger.getLogger(DetailPengaduanController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Image imagedResized = bi.getScaledInstance(120, 120, Image.SCALE_DEFAULT);
                    ImageIcon icon = new ImageIcon(imagedResized);
                    detailPengaduanView.getLabelImage().setIcon(icon);
                    detailPengaduanView.getLabelImage().setText("");
                } else {
                    detailPengaduanView.getLabelImage().setText("Tidak menyertakan foto");
                }
            }

            @Override
            public void onFailure(SQLException e) {
                detailPengaduanView.showErrorAlert("Gagal memuat data pengaduan!");
            }
            
        });
    }

    private void initDao() {
        pengaduanDAO = new PengaduanDAO();
    }

    private void initListener() {
        detailPengaduanView.getButtonTanggapan().addActionListener((ae) -> {
            new TanggapanBaruController(new TanggapanBaru(), pengaduanId, petugas).listener = new TanggapanBaruController.Listener() {
                @Override
                public void onDisposed() {
                    detailPengaduanView.dispose();
                    listener.onDisposed();
                }
            };
        });
    }
    
    public interface Listener {

        public void onDisposed();
        
    }
}

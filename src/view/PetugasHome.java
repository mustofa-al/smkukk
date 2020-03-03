/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author A
 */
public class PetugasHome extends javax.swing.JFrame {

    /**
     * Creates new form PetugasHome
     */
    public PetugasHome() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelPengaduan = new javax.swing.JTable();
        lbInfo = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuTanggapanSaya = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        menuPengaduanSelesai = new javax.swing.JMenuItem();
        menuPetugas = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        menuPengaduanMasuk = new javax.swing.JMenuItem();
        menuDitanggapi = new javax.swing.JMenuItem();
        menuTambahPetugas = new javax.swing.JMenuItem();
        menuDaftarPetugas = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Petugas | Pelaporan Pengaduan Masyarakat");

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel1.setText("Pengaduan untuk Ditanggapi");

        tabelPengaduan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tanggal Pengaduan", "Isi Pengaduan", "Oleh"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelPengaduan);

        lbInfo.setFont(new java.awt.Font("SansSerif", 0, 10)); // NOI18N
        lbInfo.setText("Login Sebagai:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbInfo))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbInfo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE))
        );

        jMenu1.setText("Tanggapan");

        menuTanggapanSaya.setText("Tanggapan oleh Saya");
        jMenu1.add(menuTanggapanSaya);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Pengaduan");

        menuPengaduanSelesai.setText("Pengaduan Selesai");
        jMenu3.add(menuPengaduanSelesai);

        jMenuBar1.add(jMenu3);

        menuPetugas.setText("Lain-lain");

        jMenu2.setText("Buat Laporan");

        menuPengaduanMasuk.setText("Laporan Pengaduan Masuk");
        jMenu2.add(menuPengaduanMasuk);

        menuDitanggapi.setText("Laporan Pengaduan Ditanggapi");
        jMenu2.add(menuDitanggapi);

        menuPetugas.add(jMenu2);

        menuTambahPetugas.setText("Tambah Petugas");
        menuPetugas.add(menuTambahPetugas);

        menuDaftarPetugas.setText("Daftar Petugas");
        menuPetugas.add(menuDaftarPetugas);

        jMenuBar1.add(menuPetugas);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PetugasHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PetugasHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PetugasHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PetugasHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PetugasHome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbInfo;
    private javax.swing.JMenuItem menuDaftarPetugas;
    private javax.swing.JMenuItem menuDitanggapi;
    private javax.swing.JMenuItem menuPengaduanMasuk;
    private javax.swing.JMenuItem menuPengaduanSelesai;
    private javax.swing.JMenu menuPetugas;
    private javax.swing.JMenuItem menuTambahPetugas;
    private javax.swing.JMenuItem menuTanggapanSaya;
    private javax.swing.JTable tabelPengaduan;
    // End of variables declaration//GEN-END:variables

    public JLabel getLabelInfo() {
        return lbInfo;
    }
    
    public void showAlert(String alert) {
        JOptionPane.showMessageDialog(this, alert);
    }
    
    public void showErrorAlert(String alert) {
        JOptionPane.showMessageDialog(this, alert, "Error!", JOptionPane.ERROR_MESSAGE);
    }
    
    public JTable getTabelPengaduan() {
        return tabelPengaduan;
    }
    
    public JMenuItem getMenuPengaduanSelesai() {
        return menuPengaduanSelesai;
    }
    
    public JMenu getMenuPetugas() {
        return menuPetugas;
    }
    
    public JMenuItem getMenuTambahPetugas() {
        return menuTambahPetugas;
    }
    
    public JMenuItem getMenuTanggapanSaya() {
        return menuTanggapanSaya;
    }
    
    public JMenuItem getMenuDaftarPetugas() {
        return menuDaftarPetugas;
    }
    
    public JMenuItem getMenuPengaduanMasuk() {
        return menuPengaduanMasuk;
    }
    
    public JMenuItem getMenuDitanggapi() {
        return menuDitanggapi;
    }
}

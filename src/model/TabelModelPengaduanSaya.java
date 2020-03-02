/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author A
 */
public class TabelModelPengaduanSaya extends AbstractTableModel{
    List<Pengaduan> list ;

    public TabelModelPengaduanSaya(List<Pengaduan> list) {
        this.list = list;
    }
    
    

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 : return list.get(rowIndex).getDate();
            case 1 : return list.get(rowIndex).getIsiLaporan();
            case 2 : {
                if (list.get(rowIndex).getStatus() == StatusPengaduan.terkirim) {
                    return "Diproses";
                } else if (list.get(rowIndex).getStatus() == StatusPengaduan.diproses) {
                    return "Terkirim";
                } else {
                    return "Selesai";
                }
            }
            default:return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0 : return "Tanggal Pengaduan";
            case 1 : return "Isi Pengaduan";
            case 2 : return "Status";
                default:return null;
        }
    }
}

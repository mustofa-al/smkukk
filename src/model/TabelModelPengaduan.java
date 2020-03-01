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
public class TabelModelPengaduan extends AbstractTableModel{
    List<Pengaduan> list ;

    public TabelModelPengaduan(List<Pengaduan> list) {
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
            case 2 : return list.get(rowIndex).getPelapor().getNama();
                default:return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0 : return "Tanggal Pengaduan";
            case 1 : return "Isi Pengaduan";
            case 2 : return "Pelapor";
                default:return null;
        }
    }
}

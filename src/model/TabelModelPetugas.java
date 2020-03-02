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
public class TabelModelPetugas extends AbstractTableModel{
    List<Petugas> list ;

    public TabelModelPetugas(List<Petugas> list) {
        this.list = list;
    }
    
    

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 : return list.get(rowIndex).getNama();
            case 1 : return list.get(rowIndex).getUsername();
            case 2 : return list.get(rowIndex).getTelp();
            case 3 : {
                if (list.get(rowIndex).getStatus() == StatusPetugas.admin) {
                    return "Admin";
                } else if (list.get(rowIndex).getStatus() == StatusPetugas.petugas) {
                    return "Petugas";
                }
            }
                default:return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0 : return "Nama";
            case 1 : return "Username";
            case 2 : return "No. Telp";
            case 3 : return "Status";
                default:return null;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import model.Masyarakat;

/**
 *
 * @author A
 */
public class MasyarakatDAO {
    public void insert(Masyarakat data) {
        String query = "INSERT INTO masyarakat (nik, nama, username, password, telp) "
                +"VALUE ("
                +"'"+data.getNik()+"'"
                +"'"+data.getNama()+"'"
                +"'"+data.getUsername()+"'"
                +"'"+data.getPassword()+"'"
                +"'"+data.getTelp()+"'"
                +")";
        
    }
}

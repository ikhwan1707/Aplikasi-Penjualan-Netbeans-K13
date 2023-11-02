package koneksi;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author smkiupro1
 */
public class koneksi {
    
    public static Connection koneksi;
//    public static void main (String[] args){
    public static Connection getKoneksi(){
        if (koneksi == null)
        {
            try{
                String url = "jdbc:mysql://localhost:3306/db_penjualan_barang_pas_xiib";
                String user = "root";
                String password = "";
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                koneksi = DriverManager.getConnection(url, user, password);
                System.out.print("Berhasil Membuat Koneksi");
            } catch (SQLException t) {
                System.out.println("Erorr Membuat Koneksi");
           }
        }
        return koneksi;
     }
    public static void main(String args[]){
        getKoneksi();
    }
}

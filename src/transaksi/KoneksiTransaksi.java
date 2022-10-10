
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transaksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 *
 * @author ASUS
 */
public class KoneksiTransaksi {
    public Connection dbKoneksiTransaksi;
    public Statement statement;
    public PreparedStatement preparedStatement;
    public KoneksiTransaksi() {        this.dbKoneksiTransaksi = null;    }
    public void bukaKoneksiTransaksi() {
        try {    Class.forName("com.mysql.jdbc.Driver");
            dbKoneksiTransaksi = DriverManager.getConnection(
"jdbc:mysql://localhost:3306/dbtransaksi?user=root&password=12345");
        } catch (Exception e) {
            e.printStackTrace();        }    }
    public void tutupKoneksi() {
        try { if (statement != null) {    statement.close();           }
            if (preparedStatement != null) {     preparedStatement.close();            }
            if (dbKoneksiTransaksi != null) {            dbKoneksiTransaksi.close();            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());        }    }
}

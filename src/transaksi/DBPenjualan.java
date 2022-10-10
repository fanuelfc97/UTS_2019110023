/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transaksi;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ASUS
 */
public class DBPenjualan {
    private PenjualanModel dp = new PenjualanModel();    
    public PenjualanModel getPenjualanModel(){ return(dp);}
    public void setPenjualanModel(PenjualanModel p){ dp=p;}    

    public ObservableList<PenjualanModel>  Load() {
        try {   ObservableList<PenjualanModel> TableData=FXCollections.observableArrayList();
            KoneksiTransaksi con = new KoneksiTransaksi(); 
            con.bukaKoneksiTransaksi();
            con.statement = con.dbKoneksiTransaksi.createStatement();
            ResultSet rs = con.statement.executeQuery(
            "Select penjualan.NoFaktur, penjualan.IdCust, penjualan.TanggalBeli, "
                    + "penjualan.IdBarang, barang.Stok,  penjualan.Qty, barang.Harga,"
                    + "penjualan.Total from penjualan"
                    + "INNER JOIN barang ON penjualan.IdBarang=barang.IdBarang\n" +
"ORDER BY penjualan.NoFaktur;");
            
            int i = 1;
            while (rs.next()) {
                PenjualanModel p=new PenjualanModel();
                p.setNoFaktur(rs.getString("NoFaktur")); 
                p.setIdCust(rs.getString("IdCust"));
                p.setIdBarang(rs.getString("IdBarang"));
                p.setTglBeli(rs.getDate("TglBeli"));
                p.setQty(rs.getInt("Qty"));
                p.setStok(rs.getInt("Stok"));
                p.setHarga(rs.getInt("Harga"));
                p.setTotal(rs.getInt("Total"));
            TableData.add(p);
                i++;
            }
            con.tutupKoneksi();
            return TableData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public int validasi(String id) {
        int val = 0;
        try {  KoneksiTransaksi con = new KoneksiTransaksi();     con.bukaKoneksiTransaksi();   con.statement = con.dbKoneksiTransaksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from penjualan where NoFaktur= '" + id + "'");
            while (rs.next()) {   val = rs.getInt("jml");            }
            con.tutupKoneksi();
        } catch (SQLException e) {            e.printStackTrace();        }
        return val;
    }   
    
    public boolean delete(String id) {
        boolean berhasil = false;
        KoneksiTransaksi con = new KoneksiTransaksi();
        try {
            con.bukaKoneksiTransaksi();
            con.preparedStatement = con.dbKoneksiTransaksi.prepareStatement("delete from penjualan where NoFaktur  = ? ");
            con.preparedStatement.setString(1, id);
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }
public boolean update() {
        boolean berhasil = false;
        KoneksiTransaksi con = new KoneksiTransaksi();
        try {
            
            con.bukaKoneksiTransaksi();
            con.preparedStatement = con.dbKoneksiTransaksi.prepareStatement(
                    "update penjualan inner join barang on penjualan.IdBarang=barang.IdBarang "
                            + "set penjualan.IdCust = ?, penjualan.TanggalBeli = ?,"
                            + "penjualan.IdBarang = ?, penjualan.Qty = ?, "
                            + "penjualan.Total = ?, barang.Stok = ?  where  penjualan.NoFaktur = ? ; ");
            con.preparedStatement.setString(1, getPenjualanModel().getNoFaktur());
            con.preparedStatement.setString(2, getPenjualanModel().getIdCust());
            con.preparedStatement.setDate(3, getPenjualanModel().getTglBeli());
            con.preparedStatement.setString(4, getPenjualanModel().getIdBarang());
            con.preparedStatement.setInt(5, getPenjualanModel().getQty());
            con.preparedStatement.setInt(6, getPenjualanModel().getTotal());
            con.preparedStatement.setInt(7, getPenjualanModel().getStok());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil = false;
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }


     public boolean insert() {
        boolean berhasil = false;    KoneksiTransaksi con = new KoneksiTransaksi();
        try {         con.bukaKoneksiTransaksi();
            con.preparedStatement = con.dbKoneksiTransaksi.prepareStatement("insert into penjualan (NoFaktur, IdCust, TanggalBeli, IdBarang, Qty, Total) values (?,?,?,?,?,?)");
            con.preparedStatement.setString(1, getPenjualanModel().getNoFaktur());
            con.preparedStatement.setString(2, getPenjualanModel().getIdCust());
            con.preparedStatement.setDate(3, getPenjualanModel().getTglBeli());
            con.preparedStatement.setString(4, getPenjualanModel().getIdBarang());
            con.preparedStatement.setInt(5, getPenjualanModel().getQty());
            con.preparedStatement.setInt(6, getPenjualanModel().getTotal());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {            e.printStackTrace();            berhasil = false;
        } finally {            con.tutupKoneksi();            return berhasil;        }
    }
}

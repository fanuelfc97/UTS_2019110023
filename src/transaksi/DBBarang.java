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
public class DBBarang {

    private BarangModel db = new BarangModel();    
    public BarangModel getBarangModel(){ return(db);}
    public void setBarangModel(BarangModel b){ db=b;}    

    public ObservableList<BarangModel> LookUp(String fld,String dt){
   try{
       ObservableList<BarangModel> tableData=FXCollections.observableArrayList();
       KoneksiTransaksi con=new KoneksiTransaksi();
       con.bukaKoneksiTransaksi();
       con.statement = con.dbKoneksiTransaksi.createStatement();
       ResultSet rs = con.statement.executeQuery("Select IdBarang, NamaBarang, Stok, Harga from barang where "+fld+" like '%"+dt+"%'");
       int i=1;
       while (rs.next()){
                BarangModel b=new BarangModel();
                b.setIdBarang(rs.getString("IdBarang"));
                b.setNamaBarang(rs.getString("NamaBarang"));
                b.setStok(rs.getInt("Stok"));
                b.setHarga(rs.getInt("Harga"));
                tableData.add(b);
                i++;
       }
       con.tutupKoneksi();
       return tableData;
   } catch (SQLException ex){       ex.printStackTrace();       return null;      }
}

    
    public ObservableList<BarangModel>  Load() {
        try {   ObservableList<BarangModel> TableData=FXCollections.observableArrayList();
            KoneksiTransaksi con = new KoneksiTransaksi(); 
            con.bukaKoneksiTransaksi();
            con.statement = con.dbKoneksiTransaksi.createStatement();
            ResultSet rs = con.statement.executeQuery(
            "Select IdBarang, NamaBarang, Stok, Harga from barang");
            
            int i = 1;
            while (rs.next()) {
                BarangModel m=new BarangModel();
                m.setIdBarang(rs.getString("IdBarang")); 
                m.setNamaBarang(rs.getString("NamaBarang"));
                m.setStok(rs.getInt("Stok"));
                m.setHarga(rs.getInt("Harga"));
            TableData.add(m);
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
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from barang where IdBarang= '" + id + "'");
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
            con.preparedStatement = con.dbKoneksiTransaksi.prepareStatement("delete from barang where IdBarang  = ? ");
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
                    "update barang set NamaBarang = ?, Stok = ?, Harga = ?  where  IdBarang = ? ; ");
            con.preparedStatement.setString(1, getBarangModel().getIdBarang());
            con.preparedStatement.setString(2, getBarangModel().getNamaBarang());
            con.preparedStatement.setInt(3, getBarangModel().getStok());
            con.preparedStatement.setInt(4, getBarangModel().getHarga());
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
            con.preparedStatement = con.dbKoneksiTransaksi.prepareStatement("insert into barang (IdBarang, NamaBarang, Stok, Harga) values (?,?,?,?)");
            con.preparedStatement.setString(1, getBarangModel().getIdBarang());
            con.preparedStatement.setString(2, getBarangModel().getNamaBarang());
            con.preparedStatement.setInt(3, getBarangModel().getStok());
            con.preparedStatement.setInt(4, getBarangModel().getHarga());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {            e.printStackTrace();            berhasil = false;
        } finally {            con.tutupKoneksi();            return berhasil;        }
    }
    
}
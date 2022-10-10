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
public class DBKonsumen {
    private KonsumenModel dk = new KonsumenModel();    
    public KonsumenModel getKonsumenModel(){ return(dk);}
    public void setKonsumenModel(KonsumenModel k){ dk=k;}    

    public ObservableList<KonsumenModel>  Load() {
        try {   ObservableList<KonsumenModel> TableData=FXCollections.observableArrayList();
            KoneksiTransaksi con = new KoneksiTransaksi(); 
            con.bukaKoneksiTransaksi();
            con.statement = con.dbKoneksiTransaksi.createStatement();
            ResultSet rs = con.statement.executeQuery(
            "Select IdCust, NamaCust, Alamat, NoHp  from konsumen");
            
            int i = 1;
            while (rs.next()) {
                KonsumenModel m=new KonsumenModel();
                m.setIdCust(rs.getString("IdCust")); 
                m.setNamaCust(rs.getString("NamaCust"));
                m.setAlamat(rs.getString("Alamat"));
                m.setNoHp(rs.getString("NoHp"));
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
    
    
    public ObservableList<KonsumenModel> LookUp(String fld,String dt){
   try{
       ObservableList<KonsumenModel> tableData=FXCollections.observableArrayList();
       KoneksiTransaksi con=new KoneksiTransaksi();
       con.bukaKoneksiTransaksi();
       con.statement = con.dbKoneksiTransaksi.createStatement();
       ResultSet rs = con.statement.executeQuery("Select IdCust, NamaCust, Alamat, NoHp from konsumen where "+fld+" like '%"+dt+"%'");
       int i=1;
       while (rs.next()){
                KonsumenModel d=new KonsumenModel();
                d.setIdCust(rs.getString("IdCust"));
                d.setNamaCust(rs.getString("NamaCust"));
                d.setAlamat(rs.getString("Alamat"));
                d.setNoHp(rs.getString("NoHp"));
                tableData.add(d);
                i++;
       }
       con.tutupKoneksi();
       return tableData;
   } catch (SQLException ex){       ex.printStackTrace();       return null;      }
}

    
    public int validasi(String nomor) {
        int val = 0;
        try {  KoneksiTransaksi con = new KoneksiTransaksi();     con.bukaKoneksiTransaksi();   con.statement = con.dbKoneksiTransaksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from konsumen where IdCust= '" + nomor + "'");
            while (rs.next()) {   val = rs.getInt("jml");            }
            con.tutupKoneksi();
        } catch (SQLException e) {            e.printStackTrace();        }
        return val;
    }   
    
    public boolean delete(String nomor) {
        boolean berhasil = false;
        KoneksiTransaksi con = new KoneksiTransaksi();
        try {
            con.bukaKoneksiTransaksi();
            con.preparedStatement = con.dbKoneksiTransaksi.prepareStatement("delete from konsumen where IdCust  = ? ");
            con.preparedStatement.setString(1, nomor);
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
                    "update konsumen set NamaCust = ?, Alamat = ?, NoHp = ?  where  IdCust = ? ; ");
            con.preparedStatement.setString(1, getKonsumenModel().getIdCust());
            con.preparedStatement.setString(2, getKonsumenModel().getNamaCust());
            con.preparedStatement.setString(3, getKonsumenModel().getAlamat());
            con.preparedStatement.setString(4, getKonsumenModel().getNoHp());
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
            con.preparedStatement = con.dbKoneksiTransaksi.prepareStatement("insert into konsumen (IdCust, NamaCust, Alamat, NoHp) values (?,?,?,?)");
            con.preparedStatement.setString(1, getKonsumenModel().getIdCust());
            con.preparedStatement.setString(2, getKonsumenModel().getNamaCust());
            con.preparedStatement.setString(3, getKonsumenModel().getAlamat());
            con.preparedStatement.setString(4, getKonsumenModel().getNoHp());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {            e.printStackTrace();            berhasil = false;
        } finally {            con.tutupKoneksi();            return berhasil;        }
    }
    
}

      
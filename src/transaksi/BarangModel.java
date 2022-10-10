/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transaksi;

/**
 *
 * @author ASUS
 */
public class BarangModel {
    private String IdBarang,NamaBarang;
    private int Stok,Harga;
    public String getIdBarang() {        return IdBarang;    }
    public void setIdBarang(String IdBarang) {        this.IdBarang = IdBarang;    }
    
    public String getNamaBarang() {        return NamaBarang;    }
    public void setNamaBarang(String NamaBarang) {        this.NamaBarang = NamaBarang;    }
    
    public int getStok() {        return Stok;    }
    public void setStok(int Stok) {        this.Stok = Stok;    }
    
    public int getHarga() {        return Harga;    }
    public void setHarga(int Harga) {        this.Harga = Harga;    }
}

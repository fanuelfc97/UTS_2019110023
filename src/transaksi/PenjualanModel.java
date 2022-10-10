/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transaksi;

import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class PenjualanModel {
   private String NoFaktur, IdCust, IdBarang;
   private Date TglBeli;
   private int Qty, Harga, Total, Stok;
   
    public String getNoFaktur() {        return NoFaktur;    }
    public void setNoFaktur(String NoFaktur) {        this.NoFaktur = NoFaktur;    }
    
    public String getIdCust() {        return IdCust;    }
    public void setIdCust(String IdCust) {        this.IdCust = IdCust;    }
   
    public String getIdBarang() {        return IdBarang;    }
    public void setIdBarang(String IdBarang) {        this.IdBarang = IdBarang;    }
    
    public Date getTglBeli() {        return TglBeli;    }
    public void setTglBeli(Date TglBeli) {        this.TglBeli = TglBeli;    }
    
    public int getQty() {        return Qty;    }
    public void setQty(int Qty) {        this.Qty = Qty;    }
    
    public int getStok() {        return Stok;    }
    public void setStok(int Stok) {        this.Stok = Stok;    }
    
    public int getHarga() {        return Harga;    }
    public void setHarga(int Harga) {        this.Harga = Harga;    } 

    public int getTotal() {        return Total;    }
    public void setTotal(int Total) {        this.Total = Total;    } 
}

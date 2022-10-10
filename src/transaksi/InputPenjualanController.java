/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package transaksi;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sun.util.calendar.BaseCalendar.Date;
  





/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class InputPenjualanController implements Initializable {

    boolean editdata = false;
    @FXML
    private TextField txtidbrg;
    @FXML
    private TextField txtharga;
    @FXML
    private Button btnsimpan;
    @FXML
    private Button btnbatal;
    @FXML
    private Button btnkeluar;
    @FXML
    private TextField txtfaktur;
    @FXML
    private TextField txtidcust;
    @FXML
    private DatePicker dtpbeli;
    @FXML
    private Slider sldjmlbrg;
    @FXML
    private Button btnhitung;
    @FXML
    private Label lbljml;
    @FXML
    private Button pilihbarang;
    @FXML
    private TextField txttotal;
    @FXML
    private Button pilihcust;
    @FXML
    private TextField txtstok;
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sldjmlbrg.valueProperty().addListener(new ChangeListener<Number>(){
            public void changed(ObservableValue<? extends Number> changed, 
                    Number oldVal, Number newVal){
                lbljml.setText(String.valueOf(newVal.intValue()));                       
            }
        }
        );

        // TODO
        
    }
    public void execute(PenjualanModel p){
        if(!p.getNoFaktur().isEmpty()){
          editdata=true;
          txtfaktur.setText(p.getNoFaktur());
          txtidcust.setText(p.getIdCust());
          dtpbeli.setValue(p.getTglBeli().toLocalDate());
          txtidbrg.setText(p.getIdBarang());
          sldjmlbrg.setValue(p.getQty());
          txttotal.setText(Integer.toString(p.getTotal()));
          txtfaktur.setEditable(false);
          txtidcust.requestFocus();
        
        } 
    }

    @FXML
    private void simpanklik(ActionEvent event) throws ParseException {
  
    PenjualanModel m=new PenjualanModel();
    
        m.setNoFaktur(txtfaktur.getText());
        m.setIdCust(txtidcust.getText());
 //       m.setTglBeli(dtpbeli.getEditor().Date);
        m.setIdBarang(txtidbrg.getText());
        m.setQty((int)sldjmlbrg.getValue()) ;
        m.setHarga(Integer.parseInt(txtharga.getText()));
        m.setTotal(Integer.parseInt(txttotal.getText()));
        m.setStok((Integer.parseInt(txtstok.getText()))-((int)sldjmlbrg.getValue()));
        FXMLDocumentController.dpenjualan.setPenjualanModel(m);
        if(editdata){
            if(FXMLDocumentController.dpenjualan.update()){
        
                      Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil diubah",ButtonType.OK);
               a.showAndWait();    batalklik(event);  
               
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal diubah",ButtonType.OK);
               a.showAndWait();                
               
            }
        }else if(FXMLDocumentController.dpenjualan.validasi(m.getNoFaktur())<=0){
            if(FXMLDocumentController.dpenjualan.insert()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil disimpan",ButtonType.OK);
               a.showAndWait();            batalklik(event);
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal disimpan",ButtonType.OK);
               a.showAndWait();            
            }
        }else{
            Alert a=new Alert(Alert.AlertType.ERROR,"Data sudah ada",ButtonType.OK);
            a.showAndWait();
            txtfaktur.requestFocus();
        }
    }

    @FXML
    private void batalklik(ActionEvent event) {
        dtpbeli.getEditor().clear();
        sldjmlbrg.setValue(0);
        txtfaktur.setText("");
        txtidcust.setText("");
        txtidbrg.setText("");
        txtharga.setText("");
        txtstok.setText("");
        txttotal.setText("");
        txtfaktur.requestFocus();

    }

    @FXML
    private void keluarklik(ActionEvent event) {
    btnkeluar.getScene().getWindow().hide();
    }

    @FXML
    private void hitungklik(ActionEvent event) {
        
        PenjualanModel p = new PenjualanModel();
  
        p.setHarga(Integer.parseInt(txtharga.getText()));
        p.setQty((int)Math.round(sldjmlbrg.getValue()));
        
        int tot=(int)Math.round(p.getHarga()*p.getQty());
        
        txttotal.setText(Integer.toString(tot));
        
    }
    
    
    
    @FXML
    private void pilihbarangklik(ActionEvent event) {
    try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("PilihBarang.fxml"));    
        Parent root = (Parent)loader.load();
        PilihBarangController isidt=(PilihBarangController)loader.getController();
        Scene scene = new Scene(root);
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);
        stg.setIconified(false);
        stg.setScene(scene);
        stg.showAndWait();
        if(isidt.getHasil()==1){
            txtidbrg.setText(isidt.getIdhasil());
            txtharga.setText(Integer.toString(isidt.getHargaHasil()));
            txtstok.setText(Integer.toString(isidt.getStokHasil()));
        }
        } catch (IOException e){   e.printStackTrace();   }
    }

    @FXML
    private void pilihcustklik(ActionEvent event) {
    try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("PilihCustomer.fxml"));    
        Parent root = (Parent)loader.load();
        PilihCustomerController isidt=(PilihCustomerController)loader.getController();
        Scene scene = new Scene(root);
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);
        stg.setIconified(false);
        stg.setScene(scene);
        stg.showAndWait();
        if(isidt.getHasil()==1){
            txtidcust.setText(isidt.getIdhasil());
        }
        } catch (IOException e){   e.printStackTrace();   }
    }
    
}
    


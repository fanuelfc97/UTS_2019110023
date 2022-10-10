/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package transaksi;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class InputBarangController implements Initializable {
    boolean editdata = false;
    @FXML
    private Button btnsimpan;
    @FXML
    private Button btnbatal;
    @FXML
    private Button btnkeluar;
    @FXML
    private TextField txtidbrg;
    @FXML
    private TextField txtnamabrg;
    @FXML
    private TextField txtstok;
    @FXML
    private TextField txtharga;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    txtstok.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, 
                    String oldVal, String newVal){
                if(!newVal.matches("\\d*"))  txtstok.setText(oldVal);            }       });

    
    
    txtharga.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, 
                    String oldVal, String newVal){
                if(!newVal.matches("\\d*"))  txtharga.setText(oldVal);            }       });

    }
    public void execute(BarangModel b){
        if(!b.getIdBarang().isEmpty()){
          editdata=true;
          txtidbrg.setText(b.getIdBarang());
          txtnamabrg.setText(b.getNamaBarang());
          txtstok.setText(Integer.toString(b.getStok()));
          txtharga.setText(Integer.toString(b.getHarga()));
          txtidbrg.setEditable(false);
          txtnamabrg.requestFocus();
        }
    
    }    

    @FXML
    private void simpanklik(ActionEvent event) {
        
        BarangModel b=new BarangModel();
        b.setIdBarang(txtidbrg.getText());
        b.setNamaBarang(txtnamabrg.getText());
        b.setStok(Integer.parseInt(txtstok.getText()));
        b.setHarga(Integer.parseInt(txtharga.getText()));
        
        FXMLDocumentController.dbarang.setBarangModel(b);
        if(editdata){
            if(FXMLDocumentController.dbarang.update()){
        
                      Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil diubah",ButtonType.OK);
               a.showAndWait();   txtidbrg.setEditable(true);        batalklik(event);                
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal diubah",ButtonType.OK);
               a.showAndWait();                
               
            }
        }else if(FXMLDocumentController.dbarang.validasi(b.getIdBarang())<=0){
            if(FXMLDocumentController.dbarang.insert()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil disimpan",ButtonType.OK);
               a.showAndWait();            batalklik(event);
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal disimpan",ButtonType.OK);
               a.showAndWait();            
            }
        }else{
            Alert a=new Alert(Alert.AlertType.ERROR,"Data sudah ada",ButtonType.OK);
            a.showAndWait();
            txtidbrg.requestFocus();
        }
    }

    @FXML
    private void batalklik(ActionEvent event) {
    txtidbrg.setText("");
    txtnamabrg.setText("");
    txtstok.setText("");
    txtharga.setText("");
    }

    @FXML
    private void keluarklik(ActionEvent event) {
        btnkeluar.getScene().getWindow().hide();
    }
    
}

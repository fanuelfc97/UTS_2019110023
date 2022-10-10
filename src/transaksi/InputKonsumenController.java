 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package transaksi;

import java.net.URL;
import java.util.ResourceBundle;
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
public class InputKonsumenController implements Initializable {
    
    boolean editdata=false;
    
    @FXML
    private TextField txtidcust;
    @FXML
    private TextField txtnama;
    @FXML
    private TextField txtalamat;
    @FXML
    private TextField txthp;
    @FXML
    private Button btnsimpan;
    @FXML
    private Button btnbatal;
    @FXML
    private Button btnkeluar;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void execute(KonsumenModel k){
        if(!k.getIdCust().isEmpty()){
          editdata=true;
          txtidcust.setText(k.getIdCust());
          txtnama.setText(k.getNamaCust());
          txtalamat.setText(k.getAlamat());
          txthp.setText(k.getNoHp());
          txtidcust.setEditable(false);
          txtnama.requestFocus();
        }
    }
    
    
    @FXML
    private void simpanklik(ActionEvent event) {
    KonsumenModel m=new KonsumenModel();
        m.setIdCust(txtidcust.getText());
        m.setNamaCust(txtnama.getText());
        m.setAlamat(txtalamat.getText());
        m.setNoHp(txthp.getText());
        
        FXMLDocumentController.dkonsumen.setKonsumenModel(m);
        if(editdata){
            if(FXMLDocumentController.dkonsumen.update()){
        
                      Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil diubah",ButtonType.OK);
               a.showAndWait();   txtidcust.setEditable(true);        batalklik(event);                
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal diubah",ButtonType.OK);
               a.showAndWait();                
               
            }
        }else if(FXMLDocumentController.dkonsumen.validasi(m.getIdCust())<=0){
            if(FXMLDocumentController.dkonsumen.insert()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil disimpan",ButtonType.OK);
               a.showAndWait();            batalklik(event);
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal disimpan",ButtonType.OK);
               a.showAndWait();            
            }
        }else{
            Alert a=new Alert(Alert.AlertType.ERROR,"Data sudah ada",ButtonType.OK);
            a.showAndWait();
            txtidcust.requestFocus();
        }
    }

    @FXML
    private void batalklik(ActionEvent event) {
    txtidcust.setText("");
    txtnama.setText("");
    txtalamat.setText("");
    txthp.setText("");
    }

    @FXML
    private void keluarklik(ActionEvent event) {
    btnkeluar.getScene().getWindow().hide();
    }
    
}

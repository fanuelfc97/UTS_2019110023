/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package transaksi;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class PilihCustomerController implements Initializable {

    private int hasil=0;
    private String custhasil="";
    


 
    @FXML
    private Button btncari;
    @FXML
    private Button btnpilih;
    private TextField txtbarang;
    @FXML
    private Button btnbatal;
    @FXML
    private TableView<KonsumenModel> tbvpbarang;
    @FXML
    private Button btnawal;
    @FXML
    private Button btnsebelum;
    @FXML
    private Button btnsesudah;
    @FXML
    private Button btnakhir;
    @FXML
    private ComboBox<String> cmbcust;
    @FXML
    private TextField txtcust;
    
    
       
    public int getHasil(){return(hasil);}
    public String getIdhasil(){return(custhasil);}
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cmbcust.setItems(FXCollections.observableArrayList(
        "IdCust","NamaCust","Alamat", "NoHp"));
        cmbcust.getSelectionModel().select(0);
        showdata("IdCust","");

    }    
    
    public void showdata(String f,String i){
         ObservableList<KonsumenModel> data= FXMLDocumentController.dkonsumen.LookUp(f, i);
        if(data.isEmpty()){    data=FXMLDocumentController.dkonsumen.Load();
                                          txtcust.setText("");       }
        if(data!=null){   tbvpbarang.getColumns().clear();
              tbvpbarang.getItems().clear();   TableColumn col=new TableColumn("IdCust");
              col.setCellValueFactory(new PropertyValueFactory<KonsumenModel, String>("IdCust"));
              tbvpbarang.getColumns().addAll(col);  col=new TableColumn("NamaCust");
              col.setCellValueFactory(new PropertyValueFactory<KonsumenModel, String>("NamaCust"));
              tbvpbarang.getColumns().addAll(col); col=new TableColumn("Alamat");
              col.setCellValueFactory(new PropertyValueFactory<KonsumenModel, String>("Alamat"));
              tbvpbarang.getColumns().addAll(col); col=new TableColumn("NoHp");
              col.setCellValueFactory(new PropertyValueFactory<KonsumenModel, String>("NoHp"));
              tbvpbarang.getColumns().addAll(col);
              tbvpbarang.setItems(data);
        }else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();   tbvpbarang.getScene().getWindow().hide();
        }                
        awalklik(null);  txtcust.requestFocus();   }


    @FXML
    private void cariklik(ActionEvent event) {
    showdata(cmbcust.getSelectionModel().getSelectedItem(), txtcust.getText());

    }

    @FXML
    private void pilihklik(ActionEvent event) {
        hasil=1;
        int pilihan=tbvpbarang.getSelectionModel().getSelectedCells().get(0).getRow();
        custhasil=tbvpbarang.getItems().get(pilihan).getIdCust();
        btnpilih.getScene().getWindow().hide();
    }

    @FXML
    private void batalklik(ActionEvent event) {
        hasil=0;
        btnbatal.getScene().getWindow().hide();    
    }
    

    @FXML
    private void awalklik(ActionEvent event) {
        tbvpbarang.getSelectionModel().selectFirst();
        tbvpbarang.requestFocus();
    }

    @FXML
    private void sebelumklik(ActionEvent event) {
        tbvpbarang.getSelectionModel().selectAboveCell();
        tbvpbarang.requestFocus();
    }

    @FXML
    private void sesudahklik(ActionEvent event) {
        tbvpbarang.getSelectionModel().selectBelowCell();
        tbvpbarang.requestFocus();
    }

    @FXML
    private void akhirklik(ActionEvent event) {
        tbvpbarang.getSelectionModel().selectLast();
        tbvpbarang.requestFocus();
    }

    
    
}

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
public class PilihBarangController implements Initializable {

   private int hasil=0;
    private String baranghasil="";
    private int hargahasil=0;
    private int stokhasil=0;
    


    @FXML
    private ComboBox<String> cmbbarang;
    @FXML
    private Button btncari;
    @FXML
    private Button btnpilih;
    @FXML
    private TextField txtbarang;
    @FXML
    private Button btnbatal;
    @FXML
    private TableView<BarangModel> tbvpbarang;
    @FXML
    private Button btnawal;
    @FXML
    private Button btnsebelum;
    @FXML
    private Button btnsesudah;
    @FXML
    private Button btnakhir;
    
    
       
    public int getHasil(){return(hasil);}
    public String getIdhasil(){return(baranghasil);}
    public int getHargaHasil(){return(hargahasil);}
    public int getStokHasil(){return(stokhasil);}
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cmbbarang.setItems(FXCollections.observableArrayList(
        "IdBarang","NamaBarang","Harga"));
        cmbbarang.getSelectionModel().select(0);
        showdata("IdBarang","");

    }    
    
    public void showdata(String f,String i){
         ObservableList<BarangModel> data= FXMLDocumentController.dbarang.LookUp(f, i);
        if(data.isEmpty()){    data=FXMLDocumentController.dbarang.Load();
                                          txtbarang.setText("");       }
        if(data!=null){   tbvpbarang.getColumns().clear();
              tbvpbarang.getItems().clear();   TableColumn col=new TableColumn("IdBarang");
              col.setCellValueFactory(new PropertyValueFactory<BarangModel, String>("IdBarang"));
              tbvpbarang.getColumns().addAll(col);  col=new TableColumn("NamaBarang");
              col.setCellValueFactory(new PropertyValueFactory<BarangModel, String>("NamaBarang"));
              tbvpbarang.getColumns().addAll(col); col=new TableColumn("Harga");
              col.setCellValueFactory(new PropertyValueFactory<BarangModel, String>("Harga"));
              tbvpbarang.getColumns().addAll(col);col=new TableColumn("Stok");
              col.setCellValueFactory(new PropertyValueFactory<BarangModel, String>("Stok"));
              tbvpbarang.getColumns().addAll(col);
              
              tbvpbarang.setItems(data);
        }else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();   tbvpbarang.getScene().getWindow().hide();;
        }                
        awalklik(null);  txtbarang.requestFocus();   }


    @FXML
    private void cariklik(ActionEvent event) {
    showdata(cmbbarang.getSelectionModel().getSelectedItem(), txtbarang.getText());

    }

    @FXML
    private void pilihklik(ActionEvent event) {
        hasil=1;
        int pilihan=tbvpbarang.getSelectionModel().getSelectedCells().get(0).getRow();
        baranghasil=tbvpbarang.getItems().get(pilihan).getIdBarang(); 
        hargahasil=tbvpbarang.getItems().get(pilihan).getHarga(); 
        stokhasil=tbvpbarang.getItems().get(pilihan).getStok(); 
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

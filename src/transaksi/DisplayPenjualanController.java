/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package transaksi;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DisplayPenjualanController implements Initializable {

    @FXML
    private TableView<PenjualanModel> tbvpenjualan;
    @FXML
    private Button btnawal;
    @FXML
    private Button btnsebelum;
    @FXML
    private Button btnsesudah;
    @FXML
    private Button btnakhir;
    @FXML
    private Button btntambah;
    @FXML
    private Button btnhapus;
    @FXML
    private Button btnubah;
    @FXML
    private Button btnkeluar;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showdata();
    }    

    private void showdata(){
        ObservableList<PenjualanModel> data = FXMLDocumentController.dpenjualan.Load();
        if(data!=null){            
            tbvpenjualan.getColumns().clear();
            tbvpenjualan.getItems().clear();
            TableColumn col=new TableColumn("NoFaktur");
            col.setCellValueFactory(new PropertyValueFactory<PenjualanModel, String>("NoFaktur"));
            tbvpenjualan.getColumns().addAll(col);
            col=new TableColumn("IdCust");
            col.setCellValueFactory(new PropertyValueFactory<PenjualanModel, String>("IdCust"));
            tbvpenjualan.getColumns().addAll(col);
            col=new TableColumn("IdBarang");
            col.setCellValueFactory(new PropertyValueFactory<PenjualanModel, String>("IdBarang"));
            tbvpenjualan.getColumns().addAll(col);
            col=new TableColumn("TanggalBeli");
            col.setCellValueFactory(new PropertyValueFactory<PenjualanModel, String>("TanggalBeli"));
            tbvpenjualan.getColumns().addAll(col);
            col=new TableColumn("Qty");
            col.setCellValueFactory(new PropertyValueFactory<PenjualanModel, String>("Qty"));
            tbvpenjualan.getColumns().addAll(col);
            col=new TableColumn("Harga");
            col.setCellValueFactory(new PropertyValueFactory<PenjualanModel, String>("Harga"));
            tbvpenjualan.getColumns().addAll(col);
            col=new TableColumn("Stok");
            col.setCellValueFactory(new PropertyValueFactory<PenjualanModel, String>("Stok"));
            tbvpenjualan.getColumns().addAll(col);
            col=new TableColumn("Total");
            col.setCellValueFactory(new PropertyValueFactory<PenjualanModel, String>("Total"));
            tbvpenjualan.getColumns().addAll(col);
            tbvpenjualan.setItems(data);
        }else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvpenjualan.getScene().getWindow().hide();
        }        
    }
    @FXML
    private void awalklik(ActionEvent event) {
        tbvpenjualan.getSelectionModel().selectFirst();
        tbvpenjualan.requestFocus();
    }

    @FXML
    private void sebelumklik(ActionEvent event) {
        tbvpenjualan.getSelectionModel().selectAboveCell();
        tbvpenjualan.requestFocus();
    }

    @FXML
    private void sesudahklik(ActionEvent event) {
        tbvpenjualan.getSelectionModel().selectBelowCell();
        tbvpenjualan.requestFocus();
    }

    @FXML
    private void akhirklik(ActionEvent event) {
        tbvpenjualan.getSelectionModel().selectLast();
        tbvpenjualan.requestFocus();
    }

    @FXML
    private void tambahklik(ActionEvent event) {
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("InputPenjualan.fxml"));    
        Parent root = (Parent)loader.load();        Scene scene = new Scene(root);        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);        stg.setIconified(false);        stg.setScene(scene);
        stg.showAndWait();
        } catch (IOException e){   e.printStackTrace();   }
        showdata();        awalklik(event);
    }

    @FXML
    private void hapusklik(ActionEvent event) {
        PenjualanModel p= new PenjualanModel();
        p=tbvpenjualan.getSelectionModel().getSelectedItem();
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Hapus data?",               ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        if(a.getResult()==ButtonType.YES){
            if(FXMLDocumentController.dpenjualan.delete(p.getNoFaktur())){
               Alert c=new Alert(Alert.AlertType.INFORMATION,                  "Data berhasil dihapus", ButtonType.OK);
               c.showAndWait();
            } else {
               Alert c=new Alert(Alert.AlertType.ERROR,                   "Data gagal dihapus", ButtonType.OK);
               c.showAndWait();
            }
            showdata();           awalklik(event);       }
    }

    @FXML
    private void ubahklik(ActionEvent event) {
        PenjualanModel p= new PenjualanModel();
        p=tbvpenjualan.getSelectionModel().getSelectedItem();
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("InputPenjualan.fxml"));    
        Parent root = (Parent)loader.load();
        InputPenjualanController isidt=(InputPenjualanController)loader.getController();
        isidt.execute(p);                
        Scene scene = new Scene(root);
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);
        stg.setIconified(false);
        stg.setScene(scene);
        stg.showAndWait();
        } catch (IOException e){   e.printStackTrace();   }
        showdata();  awalklik(event);
    }

    @FXML
    private void keluarklik(ActionEvent event) {
        btnkeluar.getScene().getWindow().hide();
    }
    
}

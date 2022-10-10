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
public class DisplayKonsumenController implements Initializable {

    @FXML
    private TableView<KonsumenModel> tbvkonsumen;
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
        
        ObservableList<KonsumenModel> data = FXMLDocumentController.dkonsumen.Load();
        if(data!=null){            
            tbvkonsumen.getColumns().clear();
            tbvkonsumen.getItems().clear();
            
            TableColumn
            col=new TableColumn("IdCust");
            col.setCellValueFactory(new PropertyValueFactory<KonsumenModel, String>("IdCust"));
            tbvkonsumen.getColumns().addAll(col);
            
            col=new TableColumn("NamaCust");
            col.setCellValueFactory(new PropertyValueFactory<KonsumenModel, String>("NamaCust"));
            tbvkonsumen.getColumns().addAll(col);
            
            col=new TableColumn("Alamat");
            col.setCellValueFactory(new PropertyValueFactory<KonsumenModel, String>("Alamat"));
            tbvkonsumen.getColumns().addAll(col);
            
            col=new TableColumn("NoHp");
            col.setCellValueFactory(new PropertyValueFactory<KonsumenModel, String>("NoHp"));
            tbvkonsumen.getColumns().addAll(col);
            
            tbvkonsumen.setItems(data);
        }else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvkonsumen.getScene().getWindow().hide();
        }        
        
    }    

    @FXML
    private void awalklik(ActionEvent event) {
        tbvkonsumen.getSelectionModel().selectFirst();
        tbvkonsumen.requestFocus();
    }

    @FXML
    private void sebelumklik(ActionEvent event) {
        tbvkonsumen.getSelectionModel().selectAboveCell();
        tbvkonsumen.requestFocus();
    }

    @FXML
    private void sesudahklik(ActionEvent event) {
        tbvkonsumen.getSelectionModel().selectBelowCell();
        tbvkonsumen.requestFocus();
    }

    @FXML
    private void akhirklik(ActionEvent event) {
        tbvkonsumen.getSelectionModel().selectLast();
        tbvkonsumen.requestFocus();
    }

    @FXML
    private void tambahklik(ActionEvent event) {
    try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("InputKonsumen.fxml"));    
        Parent root = (Parent)loader.load();        Scene scene = new Scene(root);        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);        stg.setIconified(false);        stg.setScene(scene);
        stg.showAndWait();
        } catch (IOException e){   e.printStackTrace();   }
        showdata();        awalklik(event);
    }

    @FXML
    private void hapusklik(ActionEvent event) {
        KonsumenModel k= new KonsumenModel();
               k=tbvkonsumen.getSelectionModel().getSelectedItem();
       Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Hapus data?",               ButtonType.YES,ButtonType.NO);
       a.showAndWait();
       if(a.getResult()==ButtonType.YES){
           if(FXMLDocumentController.dkonsumen.delete(k.getIdCust())){
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
        KonsumenModel k= new KonsumenModel();
        k=tbvkonsumen.getSelectionModel().getSelectedItem();
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("InputKonsumen.fxml"));    
        Parent root = (Parent)loader.load();
        InputKonsumenController isidt=(InputKonsumenController)loader.getController();
        isidt.execute(k);                
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



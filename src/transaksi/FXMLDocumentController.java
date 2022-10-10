/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package transaksi;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author ASUS
 */
public class FXMLDocumentController implements Initializable {
    
    public static DBKonsumen dkonsumen=new DBKonsumen();
    public static DBBarang dbarang=new DBBarang();
    public static DBPenjualan dpenjualan=new DBPenjualan();
    
    private Label label;
    @FXML
    private MenuItem masterkonsumen;
    @FXML
    private MenuItem masterproduk;
    @FXML
    private MenuItem transaksipenjualan;
    @FXML
    private MenuItem transaksireturpenjualan;
    @FXML
    private MenuItem displaykonsumen;
    @FXML
    private MenuItem displayproduk;
    @FXML
    private MenuItem displaypenjualan;
    @FXML
    private MenuItem displayreturpenjualan;
    @FXML
    private MenuItem Keluar;
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {


        // TODO
    }    

    @FXML
    private void masterkonsumenklik(ActionEvent event) {
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("InputKonsumen.fxml"));    
        Parent root = (Parent)loader.load();
        Scene scene = new Scene(root);
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);
        stg.setIconified(false);
        stg.setScene(scene);
        stg.show();        
        } catch (IOException e){   e.printStackTrace();   }
    }

    @FXML
    private void masterprodukklik(ActionEvent event) {
    try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("InputBarang.fxml"));    
        Parent root = (Parent)loader.load();
        Scene scene = new Scene(root);
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);
        stg.setIconified(false);
        stg.setScene(scene);
        stg.show();        
        } catch (IOException e){   e.printStackTrace();   }
    }

    @FXML
    private void transaksipenjualanklik(ActionEvent event) {
    try{  FXMLLoader loader=new FXMLLoader(getClass().getResource("InputPenjualan.fxml"));    
        Parent root = (Parent)loader.load();
        Scene scene = new Scene(root);
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);
        stg.setIconified(false);
        stg.setScene(scene);
        stg.show();        
        } catch (IOException e){   e.printStackTrace();   }

    }

    @FXML
    private void transaksireturpenjualanklik(ActionEvent event) {
    }

    @FXML
    private void klikdisplaykonsumen(ActionEvent event) {
    try{  FXMLLoader loader=new FXMLLoader(getClass().getResource("DisplayKonsumen.fxml"));    
        Parent root = (Parent)loader.load();
        Scene scene = new Scene(root);
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);
        stg.setIconified(false);
        stg.setScene(scene);
        stg.show();        
        } catch (IOException e){   e.printStackTrace();   }

    }

    @FXML
    private void displayprodukklik(ActionEvent event) {
    try{  FXMLLoader loader=new FXMLLoader(getClass().getResource("DisplayBarang.fxml"));    
        Parent root = (Parent)loader.load();
        Scene scene = new Scene(root);
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);
        stg.setIconified(false);
        stg.setScene(scene);
        stg.show();        
        } catch (IOException e){   e.printStackTrace();   }

    }

    @FXML
    private void displaypenjualanklik(ActionEvent event) {
        try{  FXMLLoader loader=new FXMLLoader(getClass().getResource("DisplayPenjualan.fxml"));    
        Parent root = (Parent)loader.load();
        Scene scene = new Scene(root);
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);
        stg.setIconified(false);
        stg.setScene(scene);
        stg.show();        
        } catch (IOException e){   e.printStackTrace();   }

    }

    @FXML
    private void displayreturpenjualanklik(ActionEvent event) {
    }

    @FXML
    private void klikkeluar(ActionEvent event) {
    System.exit(0);
    }

}

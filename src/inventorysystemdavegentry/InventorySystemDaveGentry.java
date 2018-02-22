/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystemdavegentry;

import inventorysystemdavegentry.Model.InhousePart;
import inventorysystemdavegentry.Model.Part;
import inventorysystemdavegentry.Model.Product;
import inventorysystemdavegentry.View_Controller.AddPartController;
import inventorysystemdavegentry.View_Controller.InventorySystemGUIController;
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author David Gentry
 */
public class InventorySystemDaveGentry extends Application {
    
    private Stage window;
    private static ObservableList<Part> partData = FXCollections.observableArrayList();
    private static ObservableList<Product> productData = FXCollections.observableArrayList();
   
  
    
    public InventorySystemDaveGentry() {
        

    }
    
      //update parts table
    public static ObservableList<Part> getPartData() {
        return partData;
    }
    //update products table
    public static ObservableList<Product> getProductData() {
        return productData;
    }
   

     @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Inventory System");
        initializeInventorySystemGUI();
        showInventorySystemGUI();

    }
    
    public void initializeInventorySystemGUI() throws IOException {
        FXMLLoader loader = new FXMLLoader();
            loader.setLocation(InventorySystemDaveGentry.class.getResource("View_Controller/InventorySystemGUI.fxml"));
            AnchorPane InventorySystemGUI = (AnchorPane) loader.load();
            Scene scene = new Scene(InventorySystemGUI);
            window.setScene(scene);
            window.show();
}
    
     public void showInventorySystemGUI() throws IOException {
        {
            // Load Inventory System GUI
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(InventorySystemDaveGentry.class.getResource("View_Controller/InventorySystemGUI.fxml"));
            //AnchorPane inventorySystemGUI = (AnchorPane) loader.load();

          
            // Give the controller access to the main app.
            InventorySystemGUIController controller = loader.getController();
            //controller.setMainApp(this);
        } 
    }
    
     
    public static void main(String[] args) {
        launch(args);
    }
    
}

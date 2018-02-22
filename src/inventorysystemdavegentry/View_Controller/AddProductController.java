/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystemdavegentry.View_Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import inventorysystemdavegentry.*;
import inventorysystemdavegentry.Model.Inventory;
import static inventorysystemdavegentry.Model.Inventory.getPartInv;
import static inventorysystemdavegentry.Model.Inventory.getProductInv;
import inventorysystemdavegentry.Model.Part;
import inventorysystemdavegentry.Model.Product;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;



public class AddProductController {

    
    @FXML
    private TextField addProductID;

    @FXML
    private TextField addName;

    @FXML
    private TextField addInStock;

    @FXML
    private TextField addPrice;

    @FXML
    private TextField addMax;

    @FXML
    private TextField addMin;

    @FXML
    private TableView<Part> view;

    @FXML
    private TableColumn<Part, Integer> partIDView;

    @FXML
    private TableColumn<Part, String> partNameView;

    @FXML
    private TableColumn<Part, Integer> inventoryLevelView;

    @FXML
    private TableColumn<Part, Double> priceView;

    @FXML
    private TableView<Product> view2;

    @FXML
    private TableColumn<Product, Integer> partIDView2;

    @FXML
    private TableColumn<Product, String> partNameView2;

    @FXML
    private TableColumn<Product, Integer> inventoryLevelView2;

    @FXML
    private TableColumn<Product, Double> priceView2;
    
    @FXML
    public ObservableList<Product> productData = FXCollections.observableArrayList();
    public ObservableList<Part> partData = FXCollections.observableArrayList();

    

    @FXML
    void addProduct(ActionEvent event) {
        System.out.println("addProduct button clicked");
        String id = addProductID.getText();
        String name = addName.getText();
        String inv = addInStock.getText();
        String price = addPrice.getText();
        String max = addMax.getText();
        String min = addMin.getText();
        
        Product product = new Product();
        product.setProductID(Integer.parseInt(id));
        product.setName(name);
        product.setInStock(Integer.parseInt(inv));
        product.setPrice(Double.parseDouble(price));
        product.setMax(Integer.parseInt(max));
        product.setMin(Integer.parseInt(min));
        Inventory.addProductData(product);
        product.setAssociatedParts(partData);
      
    }
    
   

    @FXML
    void cancelProduct(ActionEvent event) throws IOException {
            //System.out.println("Button Clicked");
        Parent cancelPartParent = FXMLLoader.load(getClass().getResource("InventorySystemGUI.fxml"));
        Scene mainScene = new Scene(cancelPartParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(mainScene);
        window.show();
    }

    @FXML
    void deleteProduct(ActionEvent event) {

    }

    @FXML
    void saveProduct(ActionEvent event) {

    }

    @FXML
    void searchAddProduct(ActionEvent event) {

    }
    
    private void initialize() {
        // Initialize the Part table
        partData = view.getItems();
        partIDView.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        partNameView.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        inventoryLevelView.setCellValueFactory(cellData -> cellData.getValue().partInvProperty().asObject());
        priceView.setCellValueFactory(cellData -> cellData.getValue().partPriceProperty().asObject());
         // Initialize the bottom Part table
        productData = view2.getItems();
        partIDView2.setCellValueFactory(cellData -> cellData.getValue().productIDProperty().asObject());
        partNameView2.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        inventoryLevelView2.setCellValueFactory(cellData -> cellData.getValue().productInvProperty().asObject());
        priceView2.setCellValueFactory(cellData -> cellData.getValue().productPriceProperty().asObject());
        setPartView();
        setProductView();
   
    }
    
    public void setPartView(){
        view.setItems(partData);
    }
    
    public void setProductView(){
        view2.setItems(getProductInv());
    }
   

}

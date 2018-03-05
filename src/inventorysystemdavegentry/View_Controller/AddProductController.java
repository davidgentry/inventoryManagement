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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
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
    private TableView<Part> view2;

    @FXML
    private TableColumn<Part, Integer> partIDView2;

    @FXML
    private TableColumn<Part, String> partNameView2;

    @FXML
    private TableColumn<Part, Integer> inventoryLevelView2;

    @FXML
    private TableColumn<Part, Double> priceView2;
    
     @FXML
    private TextField partSearchField;
    
    @FXML
    public ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    public ObservableList<Part> partData = FXCollections.observableArrayList();
 
    private int id;
    
    @FXML
    void addPartToProduct(ActionEvent event) {
       
        Part selectedPart = view.getSelectionModel().getSelectedItem();
        if (selectedPart != null){
       associatedParts.add(selectedPart);
       setAssociatedPartsView();
        } else { //if the part isn't selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("OOPS!");
            alert.setHeaderText("No Selection!");
            alert.setContentText("Please select a part from the top table.");
            alert.showAndWait();
        }
      
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
    void deletePartFromProduct(ActionEvent event) {
        Part selectedPart = view2.getSelectionModel().getSelectedItem();
        if (selectedPart != null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Remove Part");
            alert.setHeaderText("Remove Part from Prodcut?");
            alert.setContentText("Click OK to remove the part from the product.");
            alert.showAndWait()
                    
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> associatedParts.remove(selectedPart));
            
            setAssociatedPartsView();
       
        } else { //if the part isn't selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("OOPS!");
            alert.setHeaderText("No Selection!");
            alert.setContentText("Please select a part from the top table.");
            alert.showAndWait();
                    
        }
    }

    @FXML
    void saveProduct(ActionEvent event) throws IOException {
        System.out.println("saveProduct button clicked");
        String name = addName.getText();
        String inv = addInStock.getText();
        String price = addPrice.getText();
        String max = addMax.getText();
        String min = addMin.getText();
        
        Product product = new Product();
        product.setProductID(id);
        product.setName(name);
        product.setInStock(Integer.parseInt(inv));
        product.setPrice(Double.parseDouble(price));
        product.setMax(Integer.parseInt(max));
        product.setMin(Integer.parseInt(min));
        Inventory.addProductData(product);
        product.setAssociatedParts(associatedParts);
        
        
        Parent cancelPartParent = FXMLLoader.load(getClass().getResource("InventorySystemGUI.fxml"));
        Scene mainScene = new Scene(cancelPartParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(mainScene);
        window.show();
    }
    
       @FXML
       private void initialize() {
           
           id = Inventory.getProductIdValue();
           addProductID.setText("Prod ID = " + id);
           
        // Initialize the Part table
        partData = view.getItems();
        partIDView.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        partNameView.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        inventoryLevelView.setCellValueFactory(cellData -> cellData.getValue().partInvProperty().asObject());
        priceView.setCellValueFactory(cellData -> cellData.getValue().partPriceProperty().asObject());
       
        setPartView();
        
        // Initialize the associated Parts table
        associatedParts = view2.getItems();
        partIDView2.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        partNameView2.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        inventoryLevelView2.setCellValueFactory(cellData -> cellData.getValue().partInvProperty().asObject());
        priceView2.setCellValueFactory(cellData -> cellData.getValue().partPriceProperty().asObject());
        
        
        /****************************** SEARCH FUNCTIONALITY ************************/
        //Search Functionality for Parts/Products
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Part> filteredPartData = new FilteredList<>(Inventory.getPartInv(), p -> true);

        // Set the filter Predicate whenever the filter changes.
        partSearchField.setOnKeyPressed(p ->{
        
        partSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredPartData.setPredicate(part -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (Integer.toString(part.getPartID()).equals(newValue)) {
                    
                    return true; 
                } else if (part.getName().equals(lowerCaseFilter)) {
                    
                    return true; 
                }
                return false; 
            });
      
        });
        });

        // Wrap the FilteredList in a SortedList. 
        SortedList<Part> sortedPartData = new SortedList<>(filteredPartData);

        // Bind the SortedList comparator to the TableView comparator.
        sortedPartData.comparatorProperty().bind(view.comparatorProperty());

        // Add sorted (and filtered) data to the table.
        view.setItems(sortedPartData);
        
       }
  
    public void setPartView() {
        view.setItems(getPartInv());
    }
   
    public void setAssociatedPartsView(){
        view2.setItems(associatedParts);
    }
    
   
}

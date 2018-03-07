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
    Product product = new Product();
    
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
         Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Cancel?");
            alert.setHeaderText("Changes won't be saved");
            alert.setContentText("Really Cancel? Any changes will be lost!");
            alert.showAndWait();
                    
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
        int inv = tryParseInt(addInStock.getText());
        Double price = tryParseDouble(addPrice.getText());
        int max = tryParseInt(addMax.getText());
        int min = tryParseInt(addMin.getText());
       
        product.setProductID(id);
        product.setName(name);
        product.setInStock(inv);
        product.setPrice(price);
        product.setMax(max);
        product.setMin(min);
        
        if (validInput()){
        Inventory.addProductData(product);
        product.setAssociatedParts(associatedParts);
        
        //set scene
        Parent cancelPartParent = FXMLLoader.load(getClass().getResource("InventorySystemGUI.fxml"));
        Scene mainScene = new Scene(cancelPartParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(mainScene);
        window.show();
        }  else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Please Correct Invalid Input");
            alert.showAndWait();
        }
        
        
      }
    
    
    int tryParseInt(String value){
        int newValue = 0;
        if (value != null && value.length() > 0) {
            try {
                newValue += Integer.parseInt(value);
                    } 
            catch (NumberFormatException e) {
                return 0;
            }
        } 
        return newValue;
    } 
    
    Double tryParseDouble(String value){
        Double newValue = 0.0;
        if (value != null && value.length() > 0) {
            try {
                newValue += Double.parseDouble(value);
                    } 
            catch (NumberFormatException e) {
                return -1.0;
            }
        }
        
        return newValue;
    } 
    
    boolean invalidPrice(){
        Double productPrice = tryParseDouble(addPrice.getText()); 
        Double partPriceTotal = 0.00;
        
        for ( int i = 0; i < associatedParts.size(); i++ ){
           partPriceTotal += associatedParts.get(i).getPrice();
         System.out.println(partPriceTotal);  
         System.out.println(productPrice);
        }
        //check to see if the sum of all part prices is more than the product price
        if (partPriceTotal > productPrice){
            return true;
        } else {
            return false;
        }
        
        }
    
    boolean invalidInv(){
        int minInt =  tryParseInt(addMin.getText());
        int maxInt = tryParseInt(addMax.getText());
        int invInt = tryParseInt(addInStock.getText());
       
        //verify that inventory is between minimum and maximum for each product
        if (invInt < minInt || invInt > maxInt){
            return true;
        } else {
            return false;
        }
    }
    boolean invalidMinOrMax(){
        int minInt =  tryParseInt(addMin.getText());
        int maxInt = tryParseInt(addMax.getText());
        //verify that min is not greater than max and vice versa
        if (minInt > maxInt || maxInt < minInt){
            return true;
        } else {
            return false;
        }
    }
   

   boolean validInput() {
       String errorMessageText = "";
       if (addName.getText().length() == 0 || addName.getText() == null ){
           errorMessageText += ("Error!Please add a valid product name.\n\n");
       }
       
       if (addInStock.getText().length() == 0 || addInStock.getText() == null){
           errorMessageText += ("Error!Please add a valid product inventory level.\n\n");
       }
       
       if (addPrice.getText().length() == 0 || addPrice.getText() == null ){
           errorMessageText += ("Error!Please add a valid product price.\n\n");
       }
       
       
       if (invalidPrice()){
           errorMessageText += ("The sum of part prices is greater than the product.  Please adjust product price!\n\n");
       }
       
       if (invalidInv()){
           errorMessageText += ("Insufficient inventory.  Please adjust inventory amount to a number between minimum and maximum amounts.\n\n");
       }
       
       if (invalidMinOrMax()){
           errorMessageText += ("Minimum cannot be larger than maximum, and maximum cannot be less than minimum.  Please adjust.\n\n");
       }
       
       if (associatedParts.size() < 1){
           errorMessageText += ("Please add a part to the product. Products must contain at least one part.\n\n");
       }
       
       
 
       if (errorMessageText.length() == 0) {
        return true;
   
   } else {
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Invalid Input");
            alert.setContentText(errorMessageText);
            alert.showAndWait();
            return false;
       }
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

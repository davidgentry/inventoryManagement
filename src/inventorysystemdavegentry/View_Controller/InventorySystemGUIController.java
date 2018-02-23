package inventorysystemdavegentry.View_Controller;

import inventorysystemdavegentry.InventorySystemDaveGentry;
import inventorysystemdavegentry.Model.Part;
import inventorysystemdavegentry.Model.Product;
import static inventorysystemdavegentry.Model.Inventory.addPartData;
import static inventorysystemdavegentry.Model.Inventory.addProductData;
import static inventorysystemdavegentry.Model.Inventory.getPartInv;
import static inventorysystemdavegentry.Model.Inventory.getProductInv;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import inventorysystemdavegentry.*;
import inventorysystemdavegentry.Model.Inventory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class InventorySystemGUIController   {

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
    private TableColumn<Product, Integer> productIDView;

    @FXML
    private TableColumn<Product, String> productNameView;

    @FXML
    private TableColumn<Product, Integer> productInventoryLevelView;

    @FXML
    private TableColumn<Product, Double> productPriceView;
    
    @FXML
    private TextField partSearchField;
    
    @FXML 
    private TextField productSearchField;
    
    //initialize variables for tracking parts/products to modify
    public static Part tempPart;
    public static Product tempProduct;
    public static int tempPartIndex;
    public static int tempProductIndex;
    
    
    //getters to return the tempPart & tempProduct Indexes
    public static int getTempPartIndex(){
        return tempPartIndex;
    }
    
    public static int getTempProductIndex(){
        return tempProductIndex;
    }
    
    
   
    
    private InventorySystemDaveGentry mainApp;
    
    @FXML
    public ObservableList<Part> partData = FXCollections.observableArrayList();
    @FXML
    public ObservableList<Product> productData = FXCollections.observableArrayList();

    @FXML
    void addPart(ActionEvent event) throws IOException {
        //System.out.println("Button Clicked");
        Parent addPartParent = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
        Scene addPartScene = new Scene(addPartParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(addPartScene);
        window.show();
       
    }

    @FXML
    void addProductMain(ActionEvent event) throws IOException {
        //System.out.println("Button Clicked");
        Parent addProductParent = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
        Scene addProductScene = new Scene(addProductParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(addProductScene);
        window.show();
    }

    @FXML
    void deletePart(ActionEvent event) {
        Part selectedPart = view.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
        //confirmation message
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete...");
        alert.setHeaderText("Deleting...");
        alert.setContentText("Are you sure you want to delete " + selectedPart.getName() + " ?");        
        alert.showAndWait()
            
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> Inventory.getPartInv().remove(selectedPart));

           
        //update part table
        view.setItems(Inventory.getPartInv());
        
        } else {
        // handle if no selection present
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Selection");
        alert.setHeaderText("Oops!");
        alert.setContentText("Please select a part from the table.");

        alert.showAndWait();
        }
    }
    
    @FXML
    void deleteProduct(ActionEvent event) {
        Product selectedProduct = view2.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
        //confirmation message
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete...");
        alert.setHeaderText("Deleting...");
        alert.setContentText("Are you sure you want to delete " + selectedProduct.getName() + " ?");        
        alert.showAndWait()
            
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> Inventory.getProductInv().remove(selectedProduct));

           
        //update product table
        view2.setItems(Inventory.getProductInv());
        
        } else {
        // handle if no selection present
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Selection");
        alert.setHeaderText("Oops!");
        alert.setContentText("Please select a product from the table.");

        alert.showAndWait();
        }
    }
   

    @FXML
    void exitApp(ActionEvent event) {
        System.exit(1);
    }

    @FXML
    void modProduct(ActionEvent event) throws IOException {
         //System.out.println("Button Clicked");
         //get selected item from tableview view2
        tempProduct = view2.getSelectionModel().getSelectedItem();
        //set index of tempProduct
        tempProductIndex = getProductInv().indexOf(tempProduct);
        Parent addProductParent = FXMLLoader.load(getClass().getResource("ModifyProduct.fxml"));
        Scene addProductScene = new Scene(addProductParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(addProductScene);
        window.show();
    }

    @FXML
    void modifyPart(ActionEvent event) throws IOException {
        //System.out.println("Button Clicked");
        //get selected item from tableview view
        tempPart = view.getSelectionModel().getSelectedItem();
        //set index of tempPart
        tempPartIndex = getPartInv().indexOf(tempPart);
        Parent modifyPartParent = FXMLLoader.load(getClass().getResource("ModifyPart.fxml"));
        Scene addPartScene = new Scene(modifyPartParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(addPartScene);
        window.show();
    }
    
    
    @FXML
    private void initialize() {
        // Initialize the Part table
        partData = view.getItems();
        partIDView.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        partNameView.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        inventoryLevelView.setCellValueFactory(cellData -> cellData.getValue().partInvProperty().asObject());
        priceView.setCellValueFactory(cellData -> cellData.getValue().partPriceProperty().asObject());
         // Initialize the Product table
        productData = view2.getItems();
        productIDView.setCellValueFactory(cellData -> cellData.getValue().productIDProperty().asObject());
        productNameView.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        productInventoryLevelView.setCellValueFactory(cellData -> cellData.getValue().productInvProperty().asObject());
        productPriceView.setCellValueFactory(cellData -> cellData.getValue().productPriceProperty().asObject());
       
        updateView();
        updateView2();
        
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
       
        
        
        
        //Product Search Functionallity
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Product> filteredProductData = new FilteredList<>(Inventory.getProductInv(), p -> true);

        // Set the filter Predicate whenever the filter changes.
        productSearchField.setOnKeyReleased(p ->{
        productSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredProductData.setPredicate(product -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (Integer.toString(product.getProductID()).equals(newValue)) {
                    
                    return true; 
                } else if (product.getName().equals(lowerCaseFilter)) {
                    
                    return true; 
                }
                return false; 
            });
        });
        });

        // Wrap the FilteredList in a SortedList. 
        SortedList<Product> sortedProductData = new SortedList<>(filteredProductData);

        // Bind the SortedList comparator to the TableView comparator.
        sortedProductData.comparatorProperty().bind(view2.comparatorProperty());

        // Add sorted (and filtered) data to the table.
        view2.setItems(sortedProductData);
        
    }
      
    
    
    public void updateView() {
        view.setItems(getPartInv());
    }

    public void updateView2() {
        view2.setItems(getProductInv());
    }
    
    /**
     *
     * @param mainApp
     */
    public void setMainApp(InventorySystemDaveGentry mainApp) {
          this.mainApp = mainApp;
        //update parts table
        
        updateView();
        updateView2();
       
       
        
    }


}


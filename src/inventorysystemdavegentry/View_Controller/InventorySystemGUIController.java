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
    
    private Part modPart;
    private Product modProduct;
    
   
    
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
                    .ifPresent(response -> InventorySystemDaveGentry.getPartData().remove(selectedPart));

           
        //update part table
        view.setItems(mainApp.getPartData());
        
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
                    .ifPresent(response -> InventorySystemDaveGentry.getProductData().remove(selectedProduct));

           
        //update product table
        view2.setItems(mainApp.getProductData());
        
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
        modProduct = view2.getSelectionModel().getSelectedItem();
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
        modPart = view.getSelectionModel().getSelectedItem();
        Parent modifyPartParent = FXMLLoader.load(getClass().getResource("ModifyPart.fxml"));
        Scene addPartScene = new Scene(modifyPartParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(addPartScene);
        window.show();
    }
    
    

    @FXML
    void searchParts(ActionEvent event) throws IOException {
        
        partIDView.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        partNameView.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Part> filteredPartData = new FilteredList<>(Inventory.getPartInv(), p -> true);

        // Set the filter Predicate whenever the filter changes.
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

        // Wrap the FilteredList in a SortedList. 
        SortedList<Part> sortedPartData = new SortedList<>(filteredPartData);

        // Bind the SortedList comparator to the TableView comparator.
        sortedPartData.comparatorProperty().bind(view.comparatorProperty());

        // Add sorted (and filtered) data to the table.
        view.setItems(sortedPartData);
        System.out.println(sortedPartData);
    
        
    /*    
        String searchField = partSearchField.getText();
        boolean found = false;
        try {
            int number = Integer.parseInt(searchField);
                for (Part p: partData){
                    if (p.getPartID() == number){
                        System.out.println("This is part " + number);
                        found = true;
                        
                        partData = view.getItems();
                        partData.add(p);
                        
                            partIDView.setCellValueFactory(new PropertyValueFactory<>("partID"));
                            partNameView.setCellValueFactory(new PropertyValueFactory<>("name"));
                            inventoryLevelView.setCellValueFactory(new PropertyValueFactory<>("inStock"));
                            priceView.setCellValueFactory(new PropertyValueFactory<>("price"));
                            
                        view.setItems(partData);
                    }
                    
                    if (found == false){
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Uh Oh");
                        alert.setHeaderText("Error!");
                        alert.setContentText("Part Not Found");
                        
                        alert.showAndWait();
                    }
                }
        }
        catch(NumberFormatException e) {
            
            for(Part p: partData){
                if(p.getName().equals(searchField)){
                    System.out.println("This is part " + p.getPartID());
                    found = true;
                    
                    partData = view.getItems();
                    partData.add(p);
                    
                    partIDView.setCellValueFactory(new PropertyValueFactory<>("partID"));
                    partNameView.setCellValueFactory(new PropertyValueFactory<>("name"));
                    inventoryLevelView.setCellValueFactory(new PropertyValueFactory<>("inStock"));
                    priceView.setCellValueFactory(new PropertyValueFactory<>("price"));
                    view.setItems(partData);
                    
                }
                if (found == false){
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Uh Oh");
                        alert.setHeaderText("Error!");
                        alert.setContentText("Part Not Found");
                        
                        alert.showAndWait();
            }
        
        }
                
         
    }
        */
    }

    @FXML
    void searchProducts(ActionEvent event) {
        String searchProducts = productSearchField.getText();
        FilteredList<Product> searchProductResults = searchProducts(searchProducts);
        SortedList<Product> sortedData = new SortedList<>(searchProductResults);
        sortedData.comparatorProperty().bind(view2.comparatorProperty());
        view2.setItems(sortedData);
    }
    
     public FilteredList<Part> searchParts(String string){
        return InventorySystemDaveGentry.getPartData().filtered(p -> p.getName().toLowerCase().contains(string.toLowerCase()));
    }
    
    public FilteredList<Product> searchProducts(String string){
        return InventorySystemDaveGentry.getProductData().filtered(p -> p.getName().toLowerCase().contains(string.toLowerCase()));
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
        
       
        
    }


}


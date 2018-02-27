package inventorysystemdavegentry.View_Controller;

import inventorysystemdavegentry.Model.InhousePart;
import inventorysystemdavegentry.Model.Inventory;
import static inventorysystemdavegentry.Model.Inventory.getPartInv;
import static inventorysystemdavegentry.Model.Inventory.getProductInv;
import inventorysystemdavegentry.Model.OutsourcedPart;
import inventorysystemdavegentry.Model.Part;
import inventorysystemdavegentry.Model.Product;
import static inventorysystemdavegentry.View_Controller.InventorySystemGUIController.tempPartIndex;
import static inventorysystemdavegentry.View_Controller.InventorySystemGUIController.tempProductIndex;
import static inventorysystemdavegentry.View_Controller.ModifyPartController.partID;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModifyProductController {

    @FXML
    private TextField ID;

    @FXML
    private TextField name;

    @FXML
    private TextField inv;

    @FXML
    private TextField price;

    @FXML
    private TextField max;

    @FXML
    private TextField company;

    @FXML
    private TextField min;

    @FXML
    private TableView<Part> view;

    @FXML
    private TableColumn<Part, Integer> partID;

    @FXML
    private TableColumn<Part, String> partName;

    @FXML
    private TableColumn<Part, Integer> inventoryLevel;

    @FXML
    private TableColumn<Part, Double> pricePerUnit;

    @FXML
    private TableView<Part> view2;

    @FXML
    private TableColumn<Part, Integer> partID2;

    @FXML
    private TableColumn<Part, String> partName2;

    @FXML
    private TableColumn<Part, Integer> inventoryLevel2;

    @FXML
    private TableColumn<Part, Double> pricePerUnit2;

    @FXML
    private TextField searchPartsField;
    
    private int productID;
    
    
    @FXML
    public ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    public ObservableList<Part> partData = FXCollections.observableArrayList();

    @FXML
    void addPart(ActionEvent event) {

    }

    @FXML
    void cancelModifyPart(ActionEvent event) throws IOException {
           //System.out.println("Button Clicked");
        Parent cancelPartParent = FXMLLoader.load(getClass().getResource("InventorySystemGUI.fxml"));
        Scene mainScene = new Scene(cancelPartParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(mainScene);
        window.show();
    }

    @FXML
    void deletePart(ActionEvent event) {

    }

    @FXML
    void maximize(ActionEvent event) {

    }

    @FXML
    void minimize(ActionEvent event) {

    }

    @FXML
    void saveModifyPart(ActionEvent event) throws IOException {
        
        String getName = name.getText();
        String getInv = inv.getText();
        String getPrice = price.getText();
        String getMax = max.getText();
        String getMin = min.getText();
        
        Product product = new Product();
        product.setProductID(productID);
        product.setName(getName);
        product.setInStock(Integer.parseInt(getInv));
        product.setPrice(Double.parseDouble(getPrice));
        product.setMax(Integer.parseInt(getMax));
        product.setMin(Integer.parseInt(getMin));
        Inventory.updateProduct(tempProductIndex, product);
        //product.setAssociatedParts(partData);
        
        
        Parent cancelPartParent = FXMLLoader.load(getClass().getResource("InventorySystemGUI.fxml"));
        Scene mainScene = new Scene(cancelPartParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(mainScene);
        window.show();

    }

    @FXML
    void searchParts(ActionEvent event) {

    }
    
   @FXML
       private void initialize() {
           
        // Initialize the Part table
        partData = view.getItems();
        partID.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        partName.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        inventoryLevel.setCellValueFactory(cellData -> cellData.getValue().partInvProperty().asObject());
        pricePerUnit.setCellValueFactory(cellData -> cellData.getValue().partPriceProperty().asObject());
       
        setPartView();
        
        // Initialize the associated Parts table
        associatedParts = view2.getItems();
        partID2.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        partName2.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        inventoryLevel2.setCellValueFactory(cellData -> cellData.getValue().partInvProperty().asObject());
        pricePerUnit2.setCellValueFactory(cellData -> cellData.getValue().partPriceProperty().asObject());
        
        
        //Search Functionality for Parts/Products
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Part> filteredPartData = new FilteredList<>(Inventory.getPartInv(), p -> true);

        // Set the filter Predicate whenever the filter changes.
        searchPartsField.setOnKeyPressed(p ->{
        
        searchPartsField.textProperty().addListener((observable, oldValue, newValue) -> {
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

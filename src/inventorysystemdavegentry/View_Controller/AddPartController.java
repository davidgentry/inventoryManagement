
package inventorysystemdavegentry.View_Controller;

import inventorysystemdavegentry.Model.InhousePart;
import inventorysystemdavegentry.Model.Inventory;
import inventorysystemdavegentry.Model.OutsourcedPart;
import inventorysystemdavegentry.Model.Part;
import inventorysystemdavegentry.Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author David Gentry
 */



public class AddPartController  {
    
  
    @FXML
    private Label companyORmachine;

    @FXML
    private TextField addID;

    @FXML
    private TextField addName;

    @FXML
    private TextField addInv;

    @FXML
    private TextField addPrice;

    @FXML
    private TextField addMax;

    @FXML
    private TextField addCompany;

    @FXML
    private TextField addMin;
    
    @FXML
    private RadioButton inHouseSelected;
    
    @FXML
    private RadioButton outsourcedSelected;
    
    private int id;
    
   
    
    boolean outsourced;
    
     @FXML
    public ObservableList<Part> partData = FXCollections.observableArrayList();
    
     
    
    
    @FXML
    public void initialize() {
       id = Inventory.getPartIdValue();
       addID.setText("Part ID = " + id);
       
    }

    @FXML
    void handleAdd(ActionEvent event) throws IOException {
        
        String name = addName.getText();
        int inv = tryParseInt(addInv.getText());
        Double price = tryParseDouble(addPrice.getText());
        int max = tryParseInt(addMax.getText());
        int min = tryParseInt(addMin.getText());
        int machID = tryParseInt(addCompany.getText());
        String compName = addCompany.getText();
        
       if (validInput()){ 
        if (outsourced == false){
            InhousePart inhousePart = new InhousePart();
            inhousePart.setPartID(id);
            inhousePart.setName(name);
            inhousePart.setPrice(price);
            inhousePart.setMax(max);
            inhousePart.setMin(min);
            inhousePart.setInStock(inv);
            inhousePart.setMachineID(machID);
            Inventory.addPartData(inhousePart);        
        } else {
            OutsourcedPart outsourcedPart = new OutsourcedPart();
            outsourcedPart.setPartID(id);
            outsourcedPart.setName(name);
            outsourcedPart.setPrice(price);
            outsourcedPart.setMax(max);
            outsourcedPart.setMin(min);
            outsourcedPart.setInStock(inv);
            outsourcedPart.setCompanyName(compName);
            Inventory.addPartData(outsourcedPart);
            
            
        }
        
        Parent cancelPartParent = FXMLLoader.load(getClass().getResource("InventorySystemGUI.fxml"));
        Scene mainScene = new Scene(cancelPartParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(mainScene);
        window.show();
    } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Please Correct Invalid Input");
            alert.showAndWait();
       }
    }
    

    @FXML
    void handleCancel(ActionEvent event) throws IOException {
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
    
       boolean invalidInv(){
        int minInt =  tryParseInt(addMin.getText());
        int maxInt = tryParseInt(addMax.getText());
        int invInt = tryParseInt(addInv.getText());
       
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

    @FXML
    void handleInhouse(ActionEvent event) {
        outsourced = false;
        companyORmachine.setText("MACHINE ID");
        inHouseSelected.setSelected(true);
    }

    @FXML
    void handleOutsourced(ActionEvent event) {
        outsourced = true;
        companyORmachine.setText("COMPANY NAME");
        outsourcedSelected.setSelected(true);
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
    
    boolean validInput() {
       String errorMessageText = "";
       if (addName.getText().length() == 0 || addName.getText() == null ){
           errorMessageText += ("Error!Please add a valid product name.\n\n");
       }
       
       if (addInv.getText().length() == 0 || addInv.getText() == null){
           errorMessageText += ("Error!Please add a valid product inventory level.\n\n");
       }
       
       if (addPrice.getText().length() == 0 || addPrice.getText() == null ){
           errorMessageText += ("Error!Please add a valid product price.\n\n");
       }
    
       if (invalidInv()){
           errorMessageText += ("Insufficient inventory.  Please adjust inventory amount to a number between minimum and maximum amounts.\n\n");
       }
       
       if (invalidMinOrMax()){
           errorMessageText += ("Minimum cannot be larger than maximum, and maximum cannot be less than minimum.  Please adjust.\n\n");
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
    
    

}



        


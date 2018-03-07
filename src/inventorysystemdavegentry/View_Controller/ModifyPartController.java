package inventorysystemdavegentry.View_Controller;

import inventorysystemdavegentry.Model.InhousePart;
import inventorysystemdavegentry.Model.Inventory;
import static inventorysystemdavegentry.Model.Inventory.getPartInv;
import inventorysystemdavegentry.Model.OutsourcedPart;
import inventorysystemdavegentry.Model.Part;
import static inventorysystemdavegentry.View_Controller.InventorySystemGUIController.tempPartIndex;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModifyPartController {

        @FXML
    private TextField modID;

    @FXML
    private TextField modName;

    @FXML
    private TextField modInv;

    @FXML
    private TextField modPrice;

    @FXML
    private TextField modMax;

    @FXML
    private TextField companyORmach;
    
     @FXML
    private Label companyORmachine;

    @FXML
    private TextField modMin;
    
    boolean outsourced;
    
    @FXML
    private RadioButton inHouseSelected;

    @FXML
    private RadioButton outsourcedSelected;
    
     @FXML
    public ObservableList<Part> partData = FXCollections.observableArrayList();

     public static int partID;
    
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

   

    @FXML
    void handleSave(ActionEvent event) throws IOException {
       if (validInput()){
        
        String id = modID.getText();
        String name = modName.getText();
        int inv = tryParseInt(modInv.getText());
        Double price = tryParseDouble(modPrice.getText());
        int max = tryParseInt(modMax.getText());
        int min = tryParseInt(modMin.getText());
        String compORmach = companyORmach.getText();
        
        if (outsourced == false){
            InhousePart inhousePart = new InhousePart();
            inhousePart.setPartID(partID);
            inhousePart.setName(name);
            inhousePart.setPrice(price);
            inhousePart.setMax(max);
            inhousePart.setMin(min);
            inhousePart.setInStock(inv);
            inhousePart.setMachineID(Integer.parseInt(compORmach));
            Inventory.updatePart(tempPartIndex, inhousePart);       
        } else {
            OutsourcedPart outsourcedPart = new OutsourcedPart();
            outsourcedPart.setPartID(Integer.parseInt(id));
            outsourcedPart.setName(name);
            outsourcedPart.setPrice(price);
            outsourcedPart.setMax(max);
            outsourcedPart.setMin(min);
            outsourcedPart.setInStock(inv);
            outsourcedPart.setCompanyName(compORmach);
            Inventory.updatePart(tempPartIndex, outsourcedPart);
            //System.out.println(outsourcedPart);
            
        }
        
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
    
   @FXML
    void handleInhouse(ActionEvent event) {
        outsourced = false;
        companyORmachine.setText("MACHINE ID");
    }

    @FXML
    void handleOutsourced(ActionEvent event) {
        outsourced = true;
        companyORmachine.setText("COMPANY NAME");
    }
    
       boolean invalidInv(){
        int minInt =  tryParseInt(modMin.getText());
        int maxInt = tryParseInt(modMax.getText());
        int invInt = tryParseInt(modInv.getText());
       
        //verify that inventory is between minimum and maximum for each product
        if (invInt < minInt || invInt > maxInt){
            return true;
        } else {
            return false;
        }
    }
    boolean invalidMinOrMax(){
        int minInt =  tryParseInt(modMin.getText());
        int maxInt = tryParseInt(modMax.getText());
        //verify that min is not greater than max and vice versa
        if (minInt > maxInt || maxInt < minInt){
            return true;
        } else {
            return false;
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
    
    boolean validInput() {
       String errorMessageText = "";
       if (modName.getText().length() == 0 || modName.getText() == null ){
           errorMessageText += ("Error!Please add a valid product name.\n\n");
       }
       
       if (modInv.getText().length() == 0 || modInv.getText() == null){
           errorMessageText += ("Error!Please add a valid product inventory level.\n\n");
       }
       
       if (modPrice.getText().length() == 0 || modPrice.getText() == null ){
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
    
    public void initialize(){
    Part part = getPartInv().get(tempPartIndex);
    partID = getPartInv().get(tempPartIndex).getPartID();
    modID.setText("Autoset: " + partID);
    modName.setText(part.getName());
    modInv.setText(Integer.toString(part.getInStock()));
    modPrice.setText(Double.toString(part.getPrice()));
    modMax.setText(Integer.toString(part.getMax()));
    modMin.setText(Integer.toString(part.getMin()));
    
    
    if (part instanceof InhousePart){
     companyORmachine.setText("MACHINE ID");
     companyORmach.setText(Integer.toString(((InhousePart) getPartInv().get(tempPartIndex)).getMachineID()));
    // inHouseSelected.setSelected(true);
     
     
     
    } else {
     companyORmachine.setText("COMPANY NAME");
     companyORmach.setText(((OutsourcedPart) getPartInv().get(tempPartIndex)).getCompanyName());
     //outsourcedSelected.setSelected(true);
     
    }
        
    }
}

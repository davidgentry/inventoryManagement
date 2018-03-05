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
        
        String id = modID.getText();
        String name = modName.getText();
        String inv = modInv.getText();
        String price = modPrice.getText();
        String max = modMax.getText();
        String min = modMin.getText();
        String compORmach = companyORmach.getText();
        
        if (outsourced == false){
            InhousePart inhousePart = new InhousePart();
            inhousePart.setPartID(partID);
            inhousePart.setName(name);
            inhousePart.setPrice(Double.parseDouble(price));
            inhousePart.setMax(Integer.parseInt(max));
            inhousePart.setMin(Integer.parseInt(min));
            inhousePart.setInStock(Integer.parseInt(inv));
            inhousePart.setMachineID(Integer.parseInt(compORmach));
            Inventory.updatePart(tempPartIndex, inhousePart);       
        } else {
            OutsourcedPart outsourcedPart = new OutsourcedPart();
            outsourcedPart.setPartID(Integer.parseInt(id));
            outsourcedPart.setName(name);
            outsourcedPart.setPrice(Double.parseDouble(price));
            outsourcedPart.setMax(Integer.parseInt(max));
            outsourcedPart.setMin(Integer.parseInt(min));
            outsourcedPart.setInStock(Integer.parseInt(inv));
            outsourcedPart.setCompanyName(compORmach);
            Inventory.updatePart(tempPartIndex, outsourcedPart);
            //System.out.println(outsourcedPart);
            
        }
        
        Parent cancelPartParent = FXMLLoader.load(getClass().getResource("InventorySystemGUI.fxml"));
        Scene mainScene = new Scene(cancelPartParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(mainScene);
        window.show();
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

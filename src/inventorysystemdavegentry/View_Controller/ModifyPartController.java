package inventorysystemdavegentry.View_Controller;

import inventorysystemdavegentry.Model.InhousePart;
import inventorysystemdavegentry.Model.Inventory;
import inventorysystemdavegentry.Model.OutsourcedPart;
import inventorysystemdavegentry.Model.Part;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
    public ObservableList<Part> partData = FXCollections.observableArrayList();

     
    
    @FXML
    void handleCancel(ActionEvent event) throws IOException {
         //System.out.println("Button Clicked");
        Parent cancelPartParent = FXMLLoader.load(getClass().getResource("InventorySystemGUI.fxml"));
        Scene mainScene = new Scene(cancelPartParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(mainScene);
        window.show();
    }

   

    @FXML
    void handleSave(ActionEvent event) throws IOException {
           System.out.println("addPart button clicked");
        String id = modID.getText();
        String name = modName.getText();
        String inv = modInv.getText();
        String price = modPrice.getText();
        String max = modMax.getText();
        String min = modMin.getText();
        String compORmach = companyORmach.getText();
        
        if (outsourced == false){
            InhousePart inhousePart = new InhousePart();
            inhousePart.setPartID(Integer.parseInt(id));
            inhousePart.setName(name);
            inhousePart.setPrice(Double.parseDouble(price));
            inhousePart.setMax(Integer.parseInt(max));
            inhousePart.setMin(Integer.parseInt(min));
            inhousePart.setInStock(Integer.parseInt(inv));
            inhousePart.setMachineID(Integer.parseInt(compORmach));
            Inventory.addPartData(inhousePart);        
        } else {
            OutsourcedPart outsourcedPart = new OutsourcedPart();
            outsourcedPart.setPartID(Integer.parseInt(id));
            outsourcedPart.setName(name);
            outsourcedPart.setPrice(Double.parseDouble(price));
            outsourcedPart.setMax(Integer.parseInt(max));
            outsourcedPart.setMin(Integer.parseInt(min));
            outsourcedPart.setInStock(Integer.parseInt(inv));
            outsourcedPart.setCompanyName(compORmach);
            Inventory.addPartData(outsourcedPart);
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
    
    
}

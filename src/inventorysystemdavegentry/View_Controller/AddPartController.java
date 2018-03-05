
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
        String inv = addInv.getText();
        String price = addPrice.getText();
        String max = addMax.getText();
        String min = addMin.getText();
        String compORmach = addCompany.getText();
        
        if (outsourced == false){
            InhousePart inhousePart = new InhousePart();
            inhousePart.setPartID(id);
            inhousePart.setName(name);
            inhousePart.setPrice(Double.parseDouble(price));
            inhousePart.setMax(Integer.parseInt(max));
            inhousePart.setMin(Integer.parseInt(min));
            inhousePart.setInStock(Integer.parseInt(inv));
            inhousePart.setMachineID(Integer.parseInt(compORmach));
            Inventory.addPartData(inhousePart);        
        } else {
            OutsourcedPart outsourcedPart = new OutsourcedPart();
            outsourcedPart.setPartID(id);
            outsourcedPart.setName(name);
            outsourcedPart.setPrice(Double.parseDouble(price));
            outsourcedPart.setMax(Integer.parseInt(max));
            outsourcedPart.setMin(Integer.parseInt(min));
            outsourcedPart.setInStock(Integer.parseInt(inv));
            outsourcedPart.setCompanyName(compORmach);
            Inventory.addPartData(outsourcedPart);
            
            
        }
        
        Parent cancelPartParent = FXMLLoader.load(getClass().getResource("InventorySystemGUI.fxml"));
        Scene mainScene = new Scene(cancelPartParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(mainScene);
        window.show();
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
    
    

}



        


package inventorysystemdavegentry.View_Controller;

import java.io.IOException;
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
    private TableView<?> view;

    @FXML
    private TableColumn<?, ?> partID;

    @FXML
    private TableColumn<?, ?> partName;

    @FXML
    private TableColumn<?, ?> inventoryLevel;

    @FXML
    private TableColumn<?, ?> pricePerUnit;

    @FXML
    private TableView<?> view2;

    @FXML
    private TableColumn<?, ?> partID2;

    @FXML
    private TableColumn<?, ?> partName2;

    @FXML
    private TableColumn<?, ?> inventoryLevel2;

    @FXML
    private TableColumn<?, ?> pricePerUnit2;

    @FXML
    private TextField searchPartsField;

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
    void saveModifyPart(ActionEvent event) {

    }

    @FXML
    void searchParts(ActionEvent event) {

    }

}

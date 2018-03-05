/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystemdavegentry.Model;
//imports
import inventorysystemdavegentry.Model.Part;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author David Gentry
 */
public class Product {
    
    //variables
    private IntegerProperty productID;
    private StringProperty name;
    private DoubleProperty price;
    private IntegerProperty inStock;
    private IntegerProperty min;
    private IntegerProperty max;
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList(); 
   
    
    //constructor to initialize variables
    public Product(){
    productID = new SimpleIntegerProperty();
    name = new SimpleStringProperty();
    price = new SimpleDoubleProperty();
    inStock = new SimpleIntegerProperty();
    min = new SimpleIntegerProperty();
    max = new SimpleIntegerProperty();
    
    }
    
 
    public Product(int productID, String name, int inStock, double price, int max, int min){
      setProductID(productID);
      setName(name);
      setPrice(price);
      setInStock(inStock);
      setMin(min);
      setMax(max);
    }

   
   
    //getters
    public String getName(){
        return name.get();
    }
    
    public Double getPrice() {
        return price.get();
    }
    
    public Integer getInStock(){
        return inStock.get();
    }
    
    public Integer getMin() {
        return min.get();
    }
    
    public Integer getMax(){
        return max.get();
    }
    
    public Integer getProductID() {
        return productID.get();
    }
    
  
    //property methods
    public IntegerProperty productIDProperty(){
        return productID;
    }
    public StringProperty productNameProperty(){
        return name;
    }
    
    public IntegerProperty productInvProperty(){
        return inStock;
    }
    
    public DoubleProperty productPriceProperty(){
        return price;
    }
    
    public ObservableList<Part> getAssociatedParts(){
        return associatedParts;
    }
   
    
    
    //setters
    
    public void setName(String name){
        this.name = new SimpleStringProperty(name);
    }
    
    public void setPrice(Double price){
        this.price = new SimpleDoubleProperty(price);
    }
    
    public void setInStock(Integer inStock){
        this.inStock = new SimpleIntegerProperty(inStock);
    }
    
    public void setMin(Integer min){
        this.min = new SimpleIntegerProperty(min);
    }
    
    public void setMax(Integer max){
        this.max = new SimpleIntegerProperty(max);
    }
    
    public void setProductID(Integer productID){
        this.productID = new SimpleIntegerProperty(productID);
    }
    
    
    
    //set associatedParts
    
    public void setAssociatedParts(ObservableList<Part> associatedParts){
        this.associatedParts = associatedParts;
    
    }
   
    //lookup associated part using partID
    
    public Part lookupAssociatedPart(int partID){
        for(Part part : associatedParts){
            if (part.getPartID() == partID){
                return part;
            }
        }
        return null;
    }
    
    //add part to associatedParts
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }
    
    //remove part from associatedParts
    public boolean removeAssociatedPart(int partID){
        Part part = lookupAssociatedPart(partID);
        return associatedParts.remove(part);
    }
    
    
   
}

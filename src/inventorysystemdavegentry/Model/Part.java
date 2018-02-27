/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystemdavegentry.Model;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;



/**
 *
 * @author David Gentry
 */
public abstract class Part {
    
    protected IntegerProperty partID;
    protected StringProperty name;
    protected DoubleProperty price;
    protected IntegerProperty inStock;
    protected IntegerProperty min;
    protected IntegerProperty max; 
    
   
    //constructor

    /**
     *
     */
    public Part() 
   {
   name = new SimpleStringProperty ("");
   price = new SimpleDoubleProperty ();
   inStock = new SimpleIntegerProperty();
   partID = new SimpleIntegerProperty();
   min = new SimpleIntegerProperty();
   max = new SimpleIntegerProperty();
  
   }
   
     
    //getters
     public String getName() {
         return name.get();
}
     
    public Double getPrice(){
        return price.get();
    }
    
    public Integer getInStock(){
        return inStock.get();
    }
    
    public Integer getMin(){
        return min.get();
    }
    
    public Integer getMax(){
        return max.get();
    }
    
    public Integer getPartID() {
        return partID.get();
    }
    
    //property getter methods
    public IntegerProperty partIDProperty(){
        return partID;
    }
    public StringProperty partNameProperty(){
        return name;
    }
    
    public IntegerProperty partInvProperty(){
        return inStock;
    }
    
    public DoubleProperty partPriceProperty(){
        return price;
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
    
    public void setPartID(Integer partID){
        this.partID = new SimpleIntegerProperty(partID);
    }   
 
}



        



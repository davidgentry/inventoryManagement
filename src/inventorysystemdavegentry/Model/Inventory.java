/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystemdavegentry.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author David Gentry
 */
public class Inventory {
    
    private static ObservableList<Product> productInv = FXCollections.observableArrayList();
    private static ObservableList<Part> partInv = FXCollections.observableArrayList();
    
    
    //need variables to keep track of partID and productID auto-generated counts
    private static int partIDCounter = 0;
    private static int productIDCounter = 0;
    
    
    //observable list getters
    public static ObservableList<Product> getProductInv(){
        return productInv;
    }
   
    public static ObservableList<Part> getPartInv(){
        return partInv;
    }
   
    
    //addProduct here
     public static void addProductData(Product product){
          productInv.add(product);

}      // lookupProduct(int){}
    public Product lookupProduct(int productID){
        for(Product product : productInv){
            if (product.getProductID() == productID){
                return product;
            }
        }
        //if not return null
        return null;
    }
    
    //remove product 
    public boolean removeProduct(int productID){
       Product product = lookupProduct(productID);
       return productInv.remove(product);
    }
   
    //void updateProduct(int){}
    
    
    //addPart 
    public static void addPartData(Part part){partInv.add(part);}
    
    
    //boolean deletePart(part){}
    public boolean deletePart(int partID){
        Part part = lookupPart(partID);
        return partInv.remove(part);
    }
    
    //part lookupPart(int){}
    public Part lookupPart(int partID){
        for (Part part : partInv){
            if (part.getPartID() == partID){
                return part;
            }
        }
        // if not, return null
        return null;
    }
    public static void updatePart(int partIndex, Part part){
        partInv.set(partIndex, part);
    }
    
    
    //code to get current partIDCounter and current productIDCounter variables
    public static int getPartIdValue(){
        partIDCounter++;
        return partIDCounter;
    }
    
     public static int getProductIdValue(){
        productIDCounter++;
        return productIDCounter;
    }
    
     
}
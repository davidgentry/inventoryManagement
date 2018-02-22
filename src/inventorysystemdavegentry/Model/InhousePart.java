/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystemdavegentry.Model;
import inventorysystemdavegentry.Model.Part;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.IntegerProperty;

/**
 *
 * @author David Gentry
 */
public class InhousePart extends Part {
    
     private IntegerProperty machineID; 
    
    public InhousePart(){
        
        machineID = new SimpleIntegerProperty(0);
    }
    
    public int getMachineID(){
        return machineID.get();
    }
    
    public void setMachineID(int machineID){
        this.machineID = new SimpleIntegerProperty(machineID);
    }
    
}

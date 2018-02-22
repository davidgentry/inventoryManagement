/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystemdavegentry.Model;

import inventorysystemdavegentry.Model.Part;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 *
 * @author David Gentry
 */
public class OutsourcedPart extends Part {
    
    private StringProperty companyName;
    
    public OutsourcedPart(){
        
        companyName = new SimpleStringProperty("");
    }
    
    public String getCompanyName(){
        return companyName.get();
    }
    
    public void setCompanyName(String companyName){
        this.companyName = new SimpleStringProperty(companyName);
    }
}

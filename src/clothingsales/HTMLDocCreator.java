/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clothingsales;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jaked
 */
public class HTMLDocCreator {
    
    private BufferedWriter bw = null;   
    

    public HTMLDocCreator(ArrayList<SaleProduct> saleProducts) {
        File file = new File("C:\\Users\\jaked\\Documents\\GitHub\\ClothingSales\\webpage\\sales.html");
        
        
        try {
            bw = new BufferedWriter(new FileWriter(file, false));
            linkStyleSheet();
            bw.append("<div class=\"container\">");
            bw.newLine();
            for(SaleProduct product : saleProducts){
                bw.append("\t"
                        + "<div class=\"item "+product.getGender()+"\">");
                
                bw.newLine();
                bw.append("<span class=\"shop-name\">"+product.getShopName()+"</span>");
                bw.append("\t"
                        + "<a href=\""+ product.getProductLink() +"\" target=\"_blank\">");
                bw.newLine();
                bw.append("\t"
                        + "\t"
                        + "\t"
                        + "<img src=\""+product.getImageSrc() +"\">");
                bw.newLine();
                bw.append("</a>");
                
                bw.newLine();
                bw.append("\t"
                        + "\t"
                        + "<span>"+ product.getTitle() +"</span>");
                bw.newLine();
                bw.append("\t"
                        + "\t"
                        + "<span class=\"old-price\">"+ product.getOriginalPrice() +"</span>");
                                
                bw.newLine();
                bw.append("\t"
                        + "\t"
                        + "<span class=\"sale-price\">"+ product.getSalePrice() +"</span>");
                bw.newLine();
                bw.append("\t"
                        + "</div>");
                
                bw.newLine();
            }
            bw.append("</div>");
            bw.close();
            
        } catch (IOException ex) {
            Logger.getLogger(HTMLDocCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void linkStyleSheet(){
        if(bw != null){
            try {
                bw.append("<head>");
                bw.newLine();
                bw.append("\t"
                        + "<link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">");
                bw.newLine();
                bw.append("</head>");
            } catch (IOException ex) {
                Logger.getLogger(HTMLDocCreator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
}

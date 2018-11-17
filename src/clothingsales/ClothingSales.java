/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clothingsales;

import java.awt.Desktop;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URI;
import java.net.URISyntaxException;
import org.jsoup.HttpStatusException;

/**
 *
 * @author jaked
 */
public class ClothingSales {

    public static void main(String[] args) {
        Shops shops = new Shops();
        
        ArrayList<SaleProduct> sp = shops.SearchUniqlo();
        sp.addAll(shops.SearchAsos());
        HTMLDocCreator htmlDocCreator = new HTMLDocCreator(sp);
        File file = new File("C:\\Users\\jaked\\Documents\\GitHub\\ClothingSales\\webpage\\sales.html");
        if(file.exists()){
            try {
                Desktop.getDesktop().browse(file.toURI());
            } catch (IOException ex) {
                Logger.getLogger(ClothingSales.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        /*
        String myurl = "www.youtube.com";
        
        try {
            URI myURI = new URI(myurl);
            java.awt.Desktop.getDesktop().browse(myURI);
        } catch (IOException | URISyntaxException ex) {
            Logger.getLogger(ClothingSales.class.getName()).log(Level.SEVERE, null, ex);
        }
        <*/
   }
    
}

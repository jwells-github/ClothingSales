/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clothingsales;

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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.HttpStatusException;
/**
 *
 * @author jaked
 */
public class Shops {
    
    public static final class Names{
        public static final String ASOS = "asos";
        public static final String BANANA_REPUBLIC = "banana republic";
        public static final String BOOHOO = "boohoo";
        public static final String DEBENHAMS = "debenhams";
        public static final String END = "end";
        public static final String GAP = "gap";
        public static final String JACK_WILLS = "jack wills";
        public static final String H_AND_M = "h&m";
        public static final String HOLLISTER = "hollister";
        public static final String HOUSE_OF_FRASER = "house of fraser";
        public static final String MATALAN = "matalan";
        public static final String MANGO = "mango";
        public static final String MR_PORTER = "mr porter";
        public static final String PRIMARK = "primark";
        public static final String THE_IDLE_MAN = "the idle man";
        public static final String UNIQLO = "uniqlo";
        public static final String WEEKDAY = "weekday";
    }
    
    private String Url;
    private String ItemSelector;
    private String ItemNameSelector;
    private String ItemImageSelector;
    
    private  ArrayList<SaleProduct> SearchWebsite(){
        ArrayList<SaleProduct> saleProducts = new ArrayList<SaleProduct>(); 
        try {
            Document webpage = Jsoup.connect(Url).get();
            Elements saleItems = webpage.select(ItemSelector);
            for (Element e : saleItems){
                SaleProduct saleProduct = new SaleProduct();
                saleProduct.setTitle(e.select(ItemNameSelector).text());
                saleProduct.setImageSrc(e.select(ItemImageSelector).first().absUrl("src").toString());
                saleProduct.setOriginalPrice(e.select("span.product-sales-price").text());
                System.out.println(e.select("span.product-sales-price").text());
                saleProducts.add(saleProduct);
            }
        } catch (IOException ex) {
            Logger.getLogger(Shops.class.getName()).log(Level.SEVERE, null, ex);
        }
        return saleProducts;
        
    }
   
    public ArrayList<SaleProduct> SearchUniqlo() {
        Url = "https://www.uniqlo.com/uk/en/men/featured/sale";
        ItemSelector = "div.productTile__spacer";
        ItemNameSelector = "a.name-link";
        ItemImageSelector = "img.productTile__image";
        return SearchWebsite();
    }
    
    
  
}

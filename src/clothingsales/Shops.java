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
        public static final String ASOS = "Asos";
        public static final String BANANA_REPUBLIC = "Banana Republic";
        public static final String BOOHOO = "Boohoo";
        public static final String DEBENHAMS = "Debenhams";
        public static final String END = "End";
        public static final String GAP = "Gap";
        public static final String JACK_WILLS = "Jack Wills";
        public static final String H_AND_M = "H&M";
        public static final String HOLLISTER = "Hollister";
        public static final String HOUSE_OF_FRASER = "House Of Fraser";
        public static final String MATALAN = "Matalan";
        public static final String MANGO = "Mango";
        public static final String MR_PORTER = "Mr Porter";
        public static final String PRIMARK = "Primark";
        public static final String THE_IDLE_MAN = "The Idle Man";
        public static final String UNIQLO = "Uniqlo";
        public static final String WEEKDAY = "Weekday";
    }
    
    private String RootUrl;
    private String Url;
    private String ShopName;
    private String ItemSelector;
    private String ItemNameSelector;
    private String ItemImageSelector;
    private String ItemOriginalPriceSelector;
    private String ItemSalePriceSelector;
    private String ItemProductUrl;
    
    private  ArrayList<SaleProduct> SearchWebsite(){
        ArrayList<SaleProduct> saleProducts = new ArrayList<SaleProduct>(); 
        try {
            Document webpage = Jsoup.connect(Url).get();
            Elements saleItems = webpage.select(ItemSelector);
            for (Element e : saleItems){
                SaleProduct saleProduct = new SaleProduct();
                saleProduct.setShopName(ShopName);
                saleProduct.setTitle(e.select(ItemNameSelector).text());
                if(e.select(ItemImageSelector) != null){
                    saleProduct.setImageSrc(e.select(ItemImageSelector).first().absUrl("src").toString());
                }
                saleProduct.setProductLink(RootUrl + e.select(ItemProductUrl).attr("href"));
                saleProduct.setOriginalPrice(e.select(ItemOriginalPriceSelector).text());
                saleProduct.setSalePrice(e.select(ItemSalePriceSelector).text());
                saleProducts.add(saleProduct);
            }
        } catch (IOException ex) {
            Logger.getLogger(Shops.class.getName()).log(Level.SEVERE, null, ex);
        }
        return saleProducts;
        
    }
   
    public ArrayList<SaleProduct> SearchUniqlo() {
        RootUrl = "https://www.uniqlo.com";
        Url = "https://www.uniqlo.com/uk/en/men/featured/sale";
        ShopName = Names.UNIQLO;
        ItemSelector = "div.productTile__spacer";
        ItemNameSelector = "a.name-link.fontUniqloProRegular";
        ItemImageSelector = "img.productTile__image";
        ItemOriginalPriceSelector = "span.product-standard-price";
        ItemSalePriceSelector = "span.product-sales-price";
        ItemProductUrl = "a.thumb-link";
        return SearchWebsite();
    }
    
    
  
}

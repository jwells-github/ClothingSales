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
    
    private String MENS = "mens";
    private String WOMENS = "womens";
   
    private String RootUrl;
    private String MensUrl;
    private String WomensUrl;
    private String ShopName;
    private String ItemSelector;
    private String ItemNameSelector;
    private String ItemImageSelector;
    private String ItemOriginalPriceSelector;
    private String ItemSalePriceSelector;
    private String ItemProductUrl;
    private Boolean paging;

    
    private  ArrayList<SaleProduct> SearchWebsite(String Url, String Gender){
        ArrayList<SaleProduct> saleProducts = new ArrayList<SaleProduct>(); 
        try {
            
            int pageNumber = 1;
            Document webpage;
            Elements saleItems = new Elements();
            if(paging){
                while(true){
                    try{
                        webpage = Jsoup.connect(Url + pageNumber).get();
                        Elements pageSaleItems = webpage.select(ItemSelector);
                        if(pageSaleItems.size() > 0 && pageNumber < 10){
                            saleItems.addAll(pageSaleItems);
                            pageNumber++;
                            System.out.println(pageSaleItems.size());
                            System.out.println(pageNumber);
                        }
                        else{
                            break;
                        }
                    }
                    catch (IOException ex) {
                        Logger.getLogger(Shops.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    

                }                  
            }
            else{
                webpage = Jsoup.connect(Url).get();
                saleItems = webpage.select(ItemSelector);
            }
            
           // Elements saleItems = webpage.select(ItemSelector);
            
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
                saleProduct.setGender(Gender);
                saleProducts.add(saleProduct);
            }
        } catch (IOException ex) {
            Logger.getLogger(Shops.class.getName()).log(Level.SEVERE, null, ex);
        }
        return saleProducts;
        
    }
    

    
   
    public ArrayList<SaleProduct> SearchUniqlo() {
        ArrayList<SaleProduct> saleProducts = new ArrayList<SaleProduct>(); 
        RootUrl = "https://www.uniqlo.com";
        MensUrl = "https://www.uniqlo.com/uk/en/men/featured/sale";
        WomensUrl = "https://www.uniqlo.com/uk/en/women/featured/sale";
        ShopName = Names.UNIQLO;
        ItemSelector = "div.productTile__spacer";
        ItemNameSelector = "a.name-link.fontUniqloProRegular";
        ItemImageSelector = "img.productTile__image";
        ItemOriginalPriceSelector = "span.product-standard-price";
        ItemSalePriceSelector = "span.product-sales-price";
        ItemProductUrl = "a.thumb-link";
        paging = false;
        saleProducts = SearchWebsite(MensUrl, MENS);
        saleProducts.addAll(SearchWebsite(WomensUrl, WOMENS));
        return saleProducts;
    }
    
        public ArrayList<SaleProduct> SearchAsos() {
        ArrayList<SaleProduct> saleProducts = new ArrayList<SaleProduct>(); 
        RootUrl = "";
        MensUrl = "https://www.asos.com/men/outlet/cat/?cid=27396&nlid=mw|outlet|shop%20by%20product&page=";
        WomensUrl = "https://www.asos.com/women/outlet/cat/?cid=27391&nlid=ww|outlet|shop%20by%20product&page=";
        ShopName = Names.ASOS;
        ItemSelector = "article._2oHs74P";
        ItemNameSelector = "p";
        ItemImageSelector = "img";
        ItemOriginalPriceSelector = "span._342BXW_";
        ItemSalePriceSelector = "span.JW3hTZk";
        ItemProductUrl = "a._3x-5VWa";
        paging = true;
        saleProducts = SearchWebsite(MensUrl, MENS);
        saleProducts.addAll(SearchWebsite(WomensUrl, WOMENS));
        return saleProducts;
    }
  
}

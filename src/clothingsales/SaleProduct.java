/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clothingsales;

/**
 *
 * @author jaked
 */
public class SaleProduct {
    
   private String title;
   private String imageSrc ="";
   private String originalPrice;
   private String salePrice;
   private String productLink;
   private String shopName;
   private String gender;
   private String productType;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public String getProductLink() {
        return productLink;
    }
    

    public void setOriginalPrice(String OriginalPrice) {
        this.originalPrice = OriginalPrice;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String SalePrice) {
        this.salePrice = SalePrice;
    }

    public void setProductLink(String productLink) {
        this.productLink = productLink;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType() {
        String[] titleWords = title.toLowerCase().split(" ");
        Boolean matched = false;
        for(String word : titleWords){
          
            switch(word){
                case"t-shirt":
                {
                    productType = "T-Shirt";
                    matched = true;
                    break;
                }
                case"hoodie":{
                    productType = "Hoodie";
                    matched = true;
                    break;
                }
                case"shirt":{
                    productType = "Shirt";
                    matched = true;
                    break;
                }
                case "chino":
                case "trouser":
                case "trousers":
                {
                    productType = "Trousers";
                    matched = true;
                    break;
                }
                case"jeans":{
                    productType = "Jeans";
                    matched = true;
                    break;
                }
            }
            if(matched){
                break;
            }
        }
        if(!matched){
            productType = "Other";
        }
       
    }
   
    
    
    
}

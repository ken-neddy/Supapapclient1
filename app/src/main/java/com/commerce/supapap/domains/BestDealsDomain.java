package com.commerce.supapap.domains;

import android.util.Log;

public class BestDealsDomain {
    private String name,image;
    private String description;
    private String price;
    private String productkey;

    public BestDealsDomain() {
    }


    public BestDealsDomain(String name, String image, String description, String price,String productkey) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.productkey = productkey;
        ;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }



    public String getImage() {
        return image;
    }

    public void setImage(String pic) {
        this.image = pic;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public String getPrice() {
        return String.valueOf(price);
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public String getProductkey(){
        return productkey;
    }
    public void setProductkey(String productkey) {
        this.productkey = productkey;
    }

}

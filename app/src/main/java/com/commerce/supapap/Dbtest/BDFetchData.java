package com.commerce.supapap.Dbtest;

public class BDFetchData {
    String name,description,price,productkey;

    public BDFetchData() {
    }

    public BDFetchData(String name, String description, String price,String productkey) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.productkey=productkey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
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

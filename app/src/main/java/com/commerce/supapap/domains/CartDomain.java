package com.commerce.supapap.domains;

public class CartDomain {
    private String numberoforder;
    private String totalprice;
    private String productprice;
    private String productkey;
   // private String name;
   // private String description;

    public CartDomain() {
    }


    public CartDomain(String productkey,String numberoforder,String totalprice,String productprice,String name, String description) {

        this.productkey = productkey;
        this.numberoforder = numberoforder;
        this.totalprice = totalprice;
        this.productprice = productprice;
       // this.name = name;
       // this.description = description;
    }

    public String getProductkey() {
        return productkey;
    }
    public void setProductkey(String productkey) {
        this.productkey = productkey;
    }


    public String getNumberoforder() {
        return numberoforder;
    }

    public void setNumberoforder(String numberoforder) {
        this.numberoforder = numberoforder;
    }


    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }


    public String getProductprice() {
        return String.valueOf(productprice);
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }


//    public String getname() {
//        return String.valueOf(name);
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }


//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }

}

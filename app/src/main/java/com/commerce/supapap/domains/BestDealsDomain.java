package com.commerce.supapap.domains;

public class BestDealsDomain {
    private String name;
    private String image;
    private String description;
    private Double price;
    private int stock;

//    public BestDealsDomain(String title, String pic, String description, Double fee) {
//        this.title = title;
//        this.pic = pic;
//        this.description = description;
//        this.fee = fee;
//    }

    public BestDealsDomain(String name, String image, String description, Double price, int stock) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

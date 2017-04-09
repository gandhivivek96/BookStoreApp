package com.example.vivekgandhi.bookstoreapp;

import java.io.Serializable;

/**
 * Created by Vivek Gandhi on 3/19/2017.
 */

public class Books /*implements Serializable*/ {

   //@SerializedName("id")
    public String id;
   // @SerializedName("bookname")
   public String bookName;
   // @SerializedName("author")
   public String authorName;
   // @SerializedName("price")
   public Integer price;
   // @SerializedName("stock")
   public Integer stock;

 /*   public Books(Integer id, String bookName, String authorName, Integer price, Integer stock) {
        this.id = id;
        this.bookName = bookName;
        this.authorName = authorName;
        this.price = price;
        this.stock = stock;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }*/
}

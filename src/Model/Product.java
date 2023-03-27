/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author beepxD
 */
public class Product {

    private int id;
    private String name;
    private int stock;
    private float price;
    private boolean visible;
    
    public Product(String name, int stock, float price){
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.visible = true;
    }
    
    public Product(String name, int stock, float price, int visible){
        this.name = name;
        this.stock = stock;
        this.price = price;
        if(visible == 0) this.visible = false;
        else this.visible = true;
    }
    
    
    public Product(int id, String name, int stock, float price){
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.visible = true;
    }
    
    public Product(int id, String name, int stock, float price, int visible){
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.price = price;
        if(visible == 0) this.visible = false;
        else this.visible = true;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    public boolean isVisible() {
        return visible;
    }
}

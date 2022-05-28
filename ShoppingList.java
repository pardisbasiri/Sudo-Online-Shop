import java.util.*;
public class ShoppingList {
    String user;
    int listId;
    int numProduct = 0;
    float totalPrice;
    ArrayList<Product> Products;

    enum SendingStatus{
        PROCESSING, SENDING, DELIVERED
    }
    SendingStatus sendingStatus = SendingStatus.PROCESSING;

    boolean finished = false;

    public SendingStatus functionSS() {
        return sendingStatus;
    }

    public void changeSS(int change){
        switch (change){
            case 1:
                sendingStatus = SendingStatus.SENDING;
                break;
            case 3:
                sendingStatus = SendingStatus.DELIVERED;
                break;
        }
    }

    static int id;
    public static int addID(){
        id ++;
        return id;
    }

    public void getID(){
        listId = addID();
    }

    public void change(){
        finished = true;
    }

    public boolean getting(ShoppingList shopping_list){
        return shopping_list.finished;
    }

    public ArrayList<Product> shoppingProduct(){
        if(Products == null)
            Products = new ArrayList<Product>();
        return Products;
    }

    public void addProduct(Product product){
        shoppingProduct();
        Products.add(product);
    }

    public void priceTotal(){
        totalPrice = 0;
        for (int products = 0; products < Products.size(); products++) {
            totalPrice += Products.get(products).price;
        }
    }

}


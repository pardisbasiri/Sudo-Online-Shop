import java.lang.reflect.Array;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Product {
    static String name;
    static float price;
    public static ArrayList<Product> products;

    public static ArrayList<Product> totalProducts(){
        if(products == null)
            products = new ArrayList<Product>();
        return products;
    }

    public static void Add_Pro(Product product){
        products.add(product);
    }

    public static int productByName(String proName){
        ArrayList<Product> products = totalProducts();
        for (int product = 0; product < products.size() ; product++)
            if(proName.equals(products.get(product).name))
                return product;

        return -1;
    }

    public static int allProducts() throws NoSuchAlgorithmException {
        ArrayList<Product> productsArrayList= totalProducts();
        if(productsArrayList.size() == 0){
            System.out.println("There is no product available at the moment please check the store later");
            return -1;
        }
        else
            for (int product = 0; product < productsArrayList.size(); product++) {
                System.out.printf("Product%d : Name = %s , Price = %f \n#" , product+1 ,  productsArrayList.get(product).name , productsArrayList.get(product).price);
            }
        return 1;
    }

    public static void removeProduct(int product) {
        products.remove(product);
    }

}


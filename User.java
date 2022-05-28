import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

public class User {
    ArrayList<ShoppingList> arraylists;
    static Scanner input = new Scanner(System.in);

    String username;
    String password;

    public static ArrayList<User> users;

    public static void addUser(User user){
        users.add(user);
    }

    public static ArrayList<User> allUsers(){
        if(users == null)
            users = new ArrayList<User>();
        return users;
    }

    public static User AddAUser() throws NoSuchAlgorithmException {
        User user = new User();
        int flag = 0;
        String Username = null;
        String Password = null;

        while (flag != 1){
            System.out.println("username:");
            Username = input.nextLine();
            System.out.println("Password:");
            Password = Pass.Get_hash(input.nextLine());

            if (findingUsername(Username) < 0)
                flag=1;
            else
                System.out.println("Sorry the Username has been chose already .");
        }
        user.username = Username;
        user.password = Password;

        User.addUser(user);
        System.out.println("welcome " + Username + "\n#\n");
        return user;
    } // done

    public static User Login() throws NoSuchAlgorithmException {
        int flag = 0;
        int user = -1;
        String Username = null;
        String Password = null;

        while (flag != 1){
            System.out.println("username:");
            Username = input.nextLine();
            System.out.println("Password:");
            Password = Pass.Get_hash(input.nextLine());

            user = findingUsername(Username);
            if(user >= 0 && Password.equals(User.allUsers().get(user).password)){
                flag = 1;
            }
            else {
                if ( user < 0) {
                    System.out.println("username not found would you like to creat an user account ? (y/n)");
                    char check = input.nextLine().charAt(0);

                    if (check == 'y')
                        return AddAUser();

                }

                System.out.println("username or password is incorrect. Please try again.");
            }
        }
        System.out.println("Welcome "+Username);
        return User.allUsers().get(user);
    }

    public static void viewUserList(User user){
        for (int shop = 0; shop < user.arraylists.size(); shop++) {
            ShoppingList shopping_list = user.arraylists.get(shop);
            System.out.println("list id : " + shopping_list.listId + " sending status : " + shopping_list.functionSS() + " num of product = " + shopping_list.numProduct + " total price = " + shopping_list.totalPrice + ":\n");
            for (int product = 0; product < shopping_list.Products.size(); product++) {
                System.out.printf("product%d : Name = %s , Price = %f \n" , product+1 , shopping_list.Products.get(product).name , shopping_list.Products.get(product).price);
            }
        }
    }

    public static void getUserList(User user){
        for (int shop = 0; shop < user.arraylists.size(); shop++) {
            ShoppingList shopping_list = user.arraylists.get(shop);
            System.out.println("shopping id : " + shopping_list.listId + " list status : " + shopping_list.functionSS() + " num of product = " + shopping_list.numProduct + " total price = " + shopping_list.totalPrice + ":\n");
            for (int product = 0; product < shopping_list.Products.size(); product++) {
                System.out.printf("product%d : Name = %s , Price = %f \n#\n" , product+1 , shopping_list.Products.get(product).name , shopping_list.Products.get(product).price);
            }
        }
    }

    public int PurchaseId(int ID , User users){
        for (int x = 0; x < users.arraylists.size(); x++) {
            if(ID == users.arraylists.get(x).listId)
                return x;
        }
        return -1;
    }

    public void AddToList(User user) throws NoSuchAlgorithmException {
        if(arraylists == null)
            arraylists = new ArrayList<ShoppingList>();
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.getID();

        boolean finish = false;
        while (!finish){
            int x = Product.allProducts();
            if(x > 0) { // there is a product.
                System.out.println("Enter you're product to continue shopping or 0 to stop shopping: ");
                int product_num = input.nextInt() - 1;

                if (product_num < 0)
                    finish = true;

                else if (product_num + 1 > Product.totalProducts().size())
                    System.out.println("Not found!");

                else {
                    shoppingList.addProduct(Product.totalProducts().get(product_num));
                    shoppingList.numProduct++;
                }
            }
            else {
                return;
            }
            input.nextLine();
        }
        shoppingList.priceTotal();

        finish = false;
        while (!finish) {

            int Chikar_Konm = input.nextInt();
            input.nextLine();

            switch (Chikar_Konm) {
                case 1: // pay
                    float total_price = shoppingList.totalPrice;

                    System.out.println("total price of your list = " + total_price);
                    for (int list = 0; list < shoppingList.Products.size(); list++) {
                        System.out.println("Name = " + shoppingList.Products.get(list).name + " Price = " + shoppingList.Products.get(list).price);
                    }

                    System.out.println("Enter 1 to continue shopping:");
                    String pay = input.nextLine();

                    if (pay.equals("1")) {
                        shoppingList.getting(shoppingList);
                        System.out.println("Thank you for your purchase!");
                        finish = true;
                    }
                    break;

                case 2:
                    boolean flag = false;
                    while (!flag) {
                        System.out.println("total Price = " + shoppingList.totalPrice);

                        if (shoppingList.Products.size() == 0) {
                            System.out.println("Your list is empty");
                            return;
                        }

                        for (int list = 0; list < shoppingList.Products.size(); list++) {
                            System.out.println("product" + list + " : Name = " + shoppingList.Products.get(list).name + " Price = " + shoppingList.Products.get(list).price);
                        }

                        System.out.println("Enter the product or -1 to stop deleting:");
                        int pro = input.nextInt();

                        if (pro < 0)
                            flag = true;

                        else if (pro > shoppingList.Products.size())
                            System.out.println("Not found");

                        else {
                            shoppingList.Products.remove(pro);
                            shoppingList.totalPrice -= shoppingList.Products.get(pro).price;
                        }
                    }
                    break;

                case 3: // cancel
                    return;
            }
        }
        arraylists.add(shoppingList);
    }
    public static void DeleteFromList(User user , int shopping_list){
        boolean Done_Del = false;
        while (!Done_Del) {
            System.out.println("total Price = " + user.arraylists.get(shopping_list).totalPrice);

            if (user.arraylists.get(shopping_list).Products.size() == 0) {
                System.out.println("Empty list.");
                return;
            }

            for (int list = 0; list < user.arraylists.get(shopping_list).Products.size(); list++) {
                System.out.println("Product" + list + " : Name = " + user.arraylists.get(shopping_list).Products.get(list).name + " Price = " + user.arraylists.get(shopping_list).Products.get(list).price);
            }

            System.out.println("Enter the product or -1 to stop deleting:");
            int pro = input.nextInt();

            if (pro < 0)
                Done_Del = true;

            else if (pro > user.arraylists.get(shopping_list).Products.size())
                System.out.println("Not found");

            else {
                user.arraylists.get(shopping_list).Products.remove(pro);
                user.arraylists.get(shopping_list).totalPrice -= user.arraylists.get(shopping_list).Products.get(pro).price;
            }
        }
    }
    static int findingUsername(String Username){
        ArrayList<User> users = allUsers();
        for (int user = 0; user < users.size() ; user++)
            if(Username.equals(users.get(user).username))
                return user;

        return -1;
    }



}

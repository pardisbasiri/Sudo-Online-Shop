import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

public class Seller {
    static Scanner input = new Scanner(System.in);
    String username;
    String password;
    public static ArrayList<Seller> sellers;

    static int usernameSeller(String username){
        ArrayList<Seller> arraySellers = totalSellers();
        for (int x = 0; x < arraySellers.size() ; x++)
            if (username.equals(arraySellers.get(x).username))
                return x;
        return -1;
    }

    public static Seller Login() throws NoSuchAlgorithmException {

        String username = null;
        String password = null;
        int right = -1;

        boolean done = false;
        while (done = false){

            System.out.println("Enter you're username:");
            username = input.nextLine();
            System.out.println("Enter you're password:");
            password = Pass.Get_hash(input.nextLine());

            right = usernameSeller(username);

            if(right >= 0 && password.equals(totalSellers().get(right).password)){
                done = true;
            }
            else {

                if (right < 0) {
                    System.out.println("There isn't any account with such info type 'yes' to continue making a new account.");
                    String answer = input.nextLine();

                    if (answer.equals("yes"))
                        return makeAccount();
                }
                System.out.println("Please try again you're username or password was incorrect.");
            }
        }
        System.out.println("Welcome back" + username);
        return totalSellers().get(right);
    }

    public static Seller makeAccount() throws NoSuchAlgorithmException {
        int flag = 0;
        String Username = null;
        String Password = null;
        Seller seller = new Seller();

        while (flag != 1){
            System.out.println("Enter username:");
            Username = input .nextLine();
            System.out.println("Enter password:");
            Password = Pass.Get_hash(input.nextLine());

            if (usernameSeller(Username) < 0)
                flag=1;
            else
                System.out.println("This username is not available, try again.");
        }
        seller.username = Username;
        seller.password = Password;

        sellers.add(seller); //
        System.out.println("Welcome " + Username + "\n");
        return seller;
    }

    public static ArrayList<Seller> totalSellers(){
        if(sellers == null)
            sellers = new ArrayList<Seller>();
        return sellers;
    }
    public static void addProduct(){
        System.out.println("Enter the number of products you want to add to the shop:");
        int num = input.nextInt();
        input.nextLine();

        for (int i = 0; i < num; i++) {

            String name = null;
            float price;
            Product product = new Product();

            System.out.println("Enter a name for you're product:");
            System.out.println("(You can change name of products that already exist in shop by entering their name here)");
            name = input.nextLine();
            System.out.println("Enter the price of your product:");
            price = input.nextFloat();
            input.nextLine();

            int pro = Product.productByName(name);

            if (pro < 0) {
                product.name = name;
                product.price = price;
                Product.Add_Pro(product);
            } else {
                System.out.println("This product is already available in store, do you want to change the price? (Enter 1 to change the price)" +
                        "Current price is " + Product.totalProducts().get(pro).price );
                String check = input.nextLine();

                if (check.equals("1"))
                   Product.totalProducts().get(pro).price = price;

                else {
                    product.name = name;
                    product.price = price;
                    Product.Add_Pro(product);
                }
            }
        }

    }
    public static void allProducts() throws NoSuchAlgorithmException {
        Product.allProducts();
    }

    public static void viewLists(){
        User user;
        if(User.allUsers().size() == 0)
            System.out.println("There is no list available cause there is no user in the store");
        else
            for (int user_num = 0; user_num < User.allUsers().size(); user_num++) {
                System.out.println("username = " + User.allUsers().get(user_num).username + ":\n#");
                user = User.allUsers().get(user_num);

                User.viewUserList(user);
            }
    }

    public static void allUsers() {
        if(User.allUsers().size() == 0)
            System.out.println("There isn't any user in the site .");
        else
            for (int user = 0; user < User.allUsers().size(); user++) {
                System.out.println(user + ". username :" + User.allUsers().get(user).username);
                System.out.println("#");
            }
    }

    public static void removeUser() throws NoSuchAlgorithmException {

        Seller.allUsers();
        if(User.allUsers().size()!=0){
            int user = input.nextInt();
            input.nextLine();
            User.allUsers().remove(user);
        }
    }

    public static void Change_Status(){
        User user;
        System.out.println("Enter the username");
        for (int user_num = 0; user_num < User.allUsers().size(); user_num++) {
            System.out.printf("user%d : username = %s" , user_num +1 , User.allUsers().get(user_num).username);
        }
        int user_num = input.nextInt() - 1;
        user = User.allUsers().get(user_num);
        User.getUserList(user);

        System.out.println("Enter the list ID");
        int ID = input.nextInt();

        ID = user.PurchaseId(ID , user);

        System.out.println("Enter a number:(1.sending 2.delivered");

        int status = input.nextInt();

        user.arraylists.get(ID).changeSS(status);
    }


    static int usernameAccount(String username){
        ArrayList<Seller> sellers = totalSellers();
        for (int seller = 0; seller < sellers.size() ; seller++)
            if (username.equals(sellers.get(seller).username))
                return seller;
        return -1;
    }

    public static void removeProduct() throws NoSuchAlgorithmException {
        Product.allProducts();
        System.out.println("Enter the number that belongs to the product you want to remove:");
        int product = input.nextInt()-1;
        Product.removeProduct(product);
    }

    public static void removePro(){
        User user;
        System.out.println("Enter a username:");
        for (int username = 0; username < User.allUsers().size(); username++) {
            System.out.printf("user%d : username = %s" , username+1 , User.allUsers().get(username).username);
        }
        int user_num = input.nextInt() -1;
        user = User.allUsers().get(user_num);
        User.getUserList(user);
        System.out.println("Enter the list id:");
        int ID = input.nextInt();
        User.DeleteFromList(user , user.PurchaseId(ID,user));
    }
}

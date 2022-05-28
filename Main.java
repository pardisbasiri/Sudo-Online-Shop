import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static User user;
    static Seller seller;

    static Scanner input = new Scanner(System.in);
    static boolean userEntered = false;
    static boolean sellerEntered = false;

    public static void main(String[] args) throws NoSuchAlgorithmException {

        while (true) {
            System.out.println("Welcome to the online shop!");
            System.out.println("Are you buyer or seller?(Enter 'seller' or 'buyer' to continue)");
            String currentUserType = input.nextLine();
            System.out.println("Do you have an account?(Enter 'yes' to login or 'no' to signup");
            String entryStatus = input.nextLine();

            if(currentUserType.equals("seller")){
                if(entryStatus.equals("yes"))
                    seller = Seller.Login();
                else
                    seller = Seller.makeAccount();
                sellerEntered = true;
            }

            else {
                if (entryStatus.equals("yes"))
                    user = User.Login();
                else
                    user = User.AddAUser();
                userEntered = true;
            }

            boolean currentUserISLoggedIn = true;

            while (currentUserISLoggedIn ){
                if (userEntered){
                    System.out.println("Enter a number: 1.buying products 2.seeing shopping list 3.logging out");
                    String inp = input.nextLine();
                    if(inp.equals("1"))
                        user.AddToList(user);
                    else if(inp.equals("2"))
                        User.viewUserList(user);
                    else if(inp.equals("3")) {
                        currentUserISLoggedIn = false;
                        userEntered = false;
                    }
                }

                else if(sellerEntered){
                    System.out.println("Enter a number: 1.deleting users 2.adding product 3.removing product 4.viewing shopping lists" +
                            "5.deleting product from a user's list 6.seeing all users 7.setting sending status 8.seeing all products " +
                            "9.logging out");
                    String inp = input.nextLine();
                    if(inp.equals("1"))
                        Seller.removeUser();
                    else if(inp.equals("2"))
                        Seller.addProduct();
                    else if(inp.equals("3"))
                        Seller.removeProduct();
                    else if(inp.equals("4"))
                        Seller.viewLists();
                    else if(inp.equals("5"))
                        Seller.removePro();
                    else if(inp.equals("6"))
                        Seller.allUsers();
                    else if(inp.equals("7"))
                        Seller.Change_Status();
                    else if(inp.equals("8"))
                        Seller.allProducts();
                    else if(inp.equals("9")) {
                        currentUserISLoggedIn = false;
                        sellerEntered = false;
                    }
                }
                else
                    break;
            }
        }
    }
}


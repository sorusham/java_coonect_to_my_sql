import java.sql.SQLException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/shop";
        String user = "root";
        String password = "";
        //----------------------
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product name: ");
        String productName = scanner.nextLine();
        System.out.println("Enter product price: ");
        double productPrice = scanner.nextDouble();
        //------------------------
        DatabaseManager dbManager = new DatabaseManager(url, user, password);
        Product product = new Product(1, productName,productPrice );
        dbManager.saveProduct(product);
        System.out.println("Product saved successfully!");
        //============
        dbManager.printAllProducts();



    }
}
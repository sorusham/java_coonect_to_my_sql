import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class DatabaseManager {
    double kolle=0;
    private List<String> productList = new ArrayList<>();

    private String url;
    private String user;
    private String password;

    public DatabaseManager(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }


    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void saveProduct(Product product) {
        String query = "INSERT INTO products (name, price) VALUES (?, ?)";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // در اینجا می‌توانید ارور را لاگ کنید یا کار دیگری انجام دهید
        }

    }

    public void printAllProducts() {
        String query = "SELECT * FROM products"; // فرض بر این است که نام جدول محصولات 'products' است
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                kolle += price;
                String output = "ID: " + id +" : "+ name + ",Price: " + price + "\n";
                System.out.println(output);//برای// نسخه کنسول
                productList.add(output);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public List<String> getProductList() {
        return productList;
    }
    public String james() {
        return String.valueOf(kolle);
    }
}
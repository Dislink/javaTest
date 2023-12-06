import java.math.BigDecimal;
import java.sql.*;

public class Main {

    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/Demo";
    private static final String USERNAME = "root";
    private static final String PASSWD = "Dislink123";

    private static Connection connection=null;
    public static int connectDB(String url, String username, String password) throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
        return connection==null?-1:0;
    }
    public static void addUser(String username, String password, long phone) throws SQLException {
        PreparedStatement preparedStatement=connection.prepareStatement("insert into User (username, password, phone) values (?, ?, ?);");
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);
        preparedStatement.setLong(3,phone);
        preparedStatement.executeUpdate();
    }

    public static void deleteUser(String username) throws SQLException {
        PreparedStatement preparedStatement=connection.prepareStatement("delete from User where username = ?;");
        preparedStatement.setString(1,username);
        preparedStatement.executeUpdate();
    }

    public static void updateUsername(String username, String value) throws SQLException {
        PreparedStatement preparedStatement=connection.prepareStatement("update User set username  = ? where username = ?;");
        preparedStatement.setString(1, value);
        preparedStatement.setString(2, username);
        preparedStatement.executeUpdate();
    }
    public static void updatePassword(String username, String value) throws SQLException {
        PreparedStatement preparedStatement=connection.prepareStatement("update User set password  = ? where username = ?;");
        preparedStatement.setString(1, value);
        preparedStatement.setString(2, username);
        preparedStatement.executeUpdate();
    }
    public static void updatePhone(String username, long value) throws SQLException {
        PreparedStatement preparedStatement=connection.prepareStatement("update User set phone  = ? where username = ?;");
        preparedStatement.setLong(1, value);
        preparedStatement.setString(2, username);
        preparedStatement.executeUpdate();
    }
    public static ResultSet queryUser(String username) throws SQLException {
        PreparedStatement preparedStatement=connection.prepareStatement("select * from User where username = ?;");
        preparedStatement.setString(1,username);
        return preparedStatement.executeQuery();
    }


    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        connectDB(URL, USERNAME, PASSWD);
        System.out.println("Hello world!");
        //addUser("testAddUser", "password", 1149810l);
        //deleteUser("testAddUser");
        //updatePassword("Hello", "+safePassword");
        //deleteUser("World");
        Statement statement = connection.createStatement();
        //Class.forName(DRIVER_NAME);
        String sql = "select * from User limit 10;";
        ResultSet resultSet=queryUser("testAddUser");
        while(resultSet.next()){
            String username = resultSet.getString(1);
            System.out.println(username);
        }

    }
}
package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BaseRepository {
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/javaGram_db?user=root&password=123456");
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        BaseRepository baseRepository = new BaseRepository();
        Connection connection =baseRepository.getConnection();
        if(connection!=null){
            System.out.println("ok");
        }
    }
}

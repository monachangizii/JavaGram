package repository;

import Model.Personal;
import com.example.javagram.HelloApplication;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonalRepository implements IPersonalRepository{

    BaseRepository baseRepository = new BaseRepository();
    Connection connection = null;
    PreparedStatement prsm = null;

    Personal personal = null;
    @Override
    public Personal getPass(String user, String pass) throws SQLException, ClassNotFoundException {
        personal = null;
        connection = baseRepository.getConnection();
        String password = null;
        if (connection!=null){
            prsm = connection.prepareStatement("SELECT * FROM `javagram_db`.`tbl_users` where user_name = ? and pass = ?");
            prsm.setString(1, user);//?s in prepareStatement starts from 1. so user full username
            prsm.setString(2, pass);

            ResultSet resultSet = prsm.executeQuery();
            if(resultSet.next()) {
                String name = resultSet.getString("name");
                String lastname = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");
                personal = new Personal(name,lastname, user, pass, email, country);
            }
        }
        connection.close();
        return personal;
    }

    @Override
    public boolean signUp(String name, String lastName, String userName, String password, String email, String country) throws SQLException, ClassNotFoundException {
        connection = baseRepository.getConnection();
        ResultSet resultSet;

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Process failed");
        alert.setHeaderText(null);

        if (connection!=null){
            prsm = connection.prepareStatement("SELECT user_name from tbl_users where user_name = ?");
            prsm.setString(1, userName);
            resultSet = prsm.executeQuery();
            if(resultSet.next()){
                alert.setContentText("Choose another username please this is repetitive ");
                alert.showAndWait();
            }
            prsm = connection.prepareStatement("SELECT email from tbl_users where email = ?");
            prsm.setString(1, email);
            resultSet = prsm.executeQuery();
            if (resultSet.next())
            {
                alert.setContentText("You have an account with this email !");
                alert.showAndWait();
            }else
            {
                prsm = connection.prepareStatement("INSERT INTO `javagram_db`.`tbl_users` (name, last_name, user_name, pass, email, country)\n" +
                        "VALUES (? , ?, ?, ?, ?, ?); ");
                prsm.setString(1, name);
                prsm.setString(2, lastName);
                prsm.setString(3, userName);
                prsm.setString(4, password);
                prsm.setString(5, email);
                prsm.setString(6, country);
                int i = prsm.executeUpdate();
                if(i==1){
                    return true;
                }
            }
        }
        connection.close();
        return false;
    }

    @Override
    public List<String> search(String username) throws SQLException, ClassNotFoundException {
        String user = null;
        List<String> users = new ArrayList();
        connection = baseRepository.getConnection();
        prsm = connection.prepareStatement("select user_name from tbl_users where user_name like ?");
        prsm.setString(1, username+"%");
        ResultSet resultSet = prsm.executeQuery();
        while (resultSet.next()){
            user = resultSet.getString("user_name");
            users.add(user);
        }
        connection.close();
        return users;
    }

    @Override
    public List<Personal> getFollowers(Personal personal) throws SQLException, ClassNotFoundException {
        connection = baseRepository.getConnection();
        List<Personal> followers = new ArrayList();
        Personal personal1 = null;
        String user = null;
        prsm = connection.prepareStatement("Select user_name1 from table_follow " +
                "where user_name2=?");//username1 follow 2
        prsm.setString(1, personal.getP_username());
        ResultSet resultSet = prsm.executeQuery();
        while (resultSet.next()){
            user = resultSet.getString("user_name1");
            personal1 = new Personal(user);
            followers.add(personal1);
        }
        connection.close();
        return followers;
    }

    @Override
    public List<Personal> getFollowings(Personal personal) throws SQLException, ClassNotFoundException {
        connection = baseRepository.getConnection();
        List<Personal> followings = new ArrayList();
        Personal personal1 = null;
        String user = null;
        prsm = connection.prepareStatement("Select user_name2 from table_follow " +
                "where user_name1=?");
        prsm.setString(1, personal.getP_username());
        ResultSet resultSet = prsm.executeQuery();
        while (resultSet.next()){
            user = resultSet.getString("user_name2");
            personal1 = new Personal(user);
            followings.add(personal1);
        }
        connection.close();
        return followings;
    }

    @Override
    public void follow(Personal personal) throws SQLException, ClassNotFoundException {
        connection = baseRepository.getConnection();
        prsm = connection.prepareStatement("INSERT INTO table_follow (user_name1, user_name2)" +
                " values (?, ?)");
        prsm.setString(1, HelloApplication.getPersonal().getP_username());
        prsm.setString(2, personal.getP_username());
        prsm.executeUpdate();
        connection.close();
    }

    @Override
    public void unfollow(Personal personal) throws SQLException, ClassNotFoundException {
        connection = baseRepository.getConnection();
        prsm = connection.prepareStatement("DELETE FROM table_follow WHERE " +
                "user_name1 = ? and user_name2 = ?");
        prsm.setString(1, HelloApplication.getPersonal().getP_username());
        prsm.setString(2, personal.getP_username());
        prsm.executeUpdate();
        connection.close();
    }


}

package repository;

import Model.Personal;

import java.sql.SQLException;
import java.util.List;

public interface IPersonalRepository {
       Personal getPass(String user, String pass) throws SQLException, ClassNotFoundException;
       boolean signUp(String name, String lastName, String userName, String password, String email, String country) throws SQLException, ClassNotFoundException;
       List<String> search(String username) throws SQLException, ClassNotFoundException;
       List<Personal> getFollowers(Personal personal) throws SQLException, ClassNotFoundException;
       List<Personal> getFollowings(Personal personal) throws SQLException, ClassNotFoundException;
       void follow(Personal personal) throws SQLException, ClassNotFoundException;
       void unfollow(Personal personal) throws SQLException, ClassNotFoundException;

}

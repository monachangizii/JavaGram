package repository;

import Model.Personal;
import Model.Post;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public interface IPostRepository {
    List<Post> getPost(Personal personal) throws SQLException, ClassNotFoundException;

    boolean deletPost(Post post) throws SQLException, ClassNotFoundException;
    boolean insertPost(Post post) throws SQLException, ClassNotFoundException, FileNotFoundException;
    List<Post> getPosts() throws SQLException, ClassNotFoundException;
    Integer getLike(Post post) throws SQLException, ClassNotFoundException;
    Integer getComment(Post post) throws SQLException, ClassNotFoundException;


}

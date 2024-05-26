package repository;

import Model.Personal;
import Model.Post;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostRepository implements IPostRepository {

    BaseRepository baseRepository = new BaseRepository();
    Connection connection = null ;
    PreparedStatement prsm = null;

    @Override
    public List<Post> getPost(Personal personal) throws SQLException, ClassNotFoundException {
        String username = personal.getP_username();
        List<Post> info = new ArrayList();
        Post post = null;
        String text = null;
        Timestamp time = null;
        Blob pic = null;
        connection = baseRepository.getConnection();
        prsm = connection.prepareStatement("SELECT * FROM javagram_db.tbl_post where user_name = ? order by sharing_date desc ");
        prsm.setString(1, username);
        ResultSet resultSet = prsm.executeQuery();
        while (resultSet.next()) {
            text = resultSet.getString("post_txt");
            time = resultSet.getTimestamp("sharing_date");
            pic = resultSet.getBlob("post_pic");
            post = new Post(username, text, pic, time );
            info.add(post);
        }
        connection.close();
        return info;
    }

    @Override
    public boolean deletPost(Post post) throws SQLException, ClassNotFoundException {
        connection = baseRepository.getConnection();
        String text = post.getText();
        Timestamp time = post.getTime();
        prsm = connection.prepareStatement("DELETE FROM tbl_post WHERE post_txt = ? AND sharing_date = ? ");
        prsm.setString(1,text );
        prsm.setTimestamp(2, time);
        int state = prsm.executeUpdate();
        if(state==1)
        {
            connection.close();
            return true;
        }
        else
        {
            connection.close();
            return false;
        }
    }

    @Override
    public boolean insertPost(Post post) throws SQLException, ClassNotFoundException, FileNotFoundException {
        connection = baseRepository.getConnection();
        prsm = connection.prepareStatement("insert into tbl_post(user_name, post_txt, post_pic)" +
                "values (?, ?, ?)");
        prsm.setString(1, post.getName());
        prsm.setString(2,post.getText());
        FileInputStream fileInputStream = new FileInputStream(new File(post.getPath().toString()));
        prsm.setBlob(3, fileInputStream);
        int resultSet = prsm.executeUpdate();
        if(resultSet==1)
        {
            connection.close();
            return true;
        }
        else
        {
            connection.close();
            return false;
        }

    }

    @Override
    public List<Post> getPosts() throws SQLException, ClassNotFoundException {
        List<Post> info = new ArrayList();
        String username;
        Post post = null;
        String text = null;
        Timestamp time = null;
        Blob pic = null;
        connection = baseRepository.getConnection();
        prsm = connection.prepareStatement("SELECT * from tbl_post order by sharing_date desc ");
        ResultSet resultSet = prsm.executeQuery();
        while (resultSet.next()){
            text = resultSet.getString("post_txt");
            time = resultSet.getTimestamp("sharing_date");
            pic = resultSet.getBlob("post_pic");
            username = resultSet.getString("user_name");
            post = new Post(username, text,pic,time);
            info.add(post);
        }
        connection.close();
        return info;
    }

    @Override
    public Integer getLike(Post post) throws SQLException, ClassNotFoundException {
        connection = baseRepository.getConnection();
        String txt = post.getText();
        String user = post.getName();
        prsm = connection.prepareStatement("SELECT * from tbl_like_post " +
                "where post =? and user_name1 =?");
        prsm.setString(1, txt);
        prsm.setString(2, user);
        ResultSet resultSet = prsm.executeQuery();
        int i = 0;
        while (resultSet.next()){
            i++;
        }
        connection.close();
        return i;

    }

    @Override
    public Integer getComment(Post post) throws SQLException, ClassNotFoundException {
        connection = baseRepository.getConnection();
        String txt = post.getText();
        String user = post.getName();
        prsm = connection.prepareStatement("SELECT * from tbl_comments " +
                "where post =? and user_name1 =?");
        prsm.setString(1, txt);
        prsm.setString(2, user);
        ResultSet resultSet = prsm.executeQuery();
        int i = 0;
        while (resultSet.next()){
            i++;
        }
        connection.close();
        return i;
    }


}

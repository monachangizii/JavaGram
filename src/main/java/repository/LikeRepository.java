package repository;

import Model.Personal;
import Model.Post;
import com.example.javagram.HelloApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LikeRepository implements ILikeRepository{
    BaseRepository baseRepository = new BaseRepository();
    Connection connection;
    PreparedStatement prsm;
    PostRepository postRepository = new PostRepository();
    Personal personal = HelloApplication.getPersonal();

    @Override
    public Integer setLike(Post post) throws SQLException, ClassNotFoundException {
        connection = baseRepository.getConnection();
        int like = postRepository.getLike(post) ;
        prsm = connection.prepareStatement("INSERT into tbl_like_post(user_name1, user_name2, " +
                "post) values (?, ?, ?)");
        prsm.setString(1, post.getName());//who post post!
        prsm.setString(2, personal.getP_name());//who like
        prsm.setString(3, post.getText());
        int resultSet = prsm.executeUpdate();
        if (resultSet==1)
        {
            connection.close();
            return like+1;
        }
        else
        {
            connection.close();
            return -1;
        }
    }
}

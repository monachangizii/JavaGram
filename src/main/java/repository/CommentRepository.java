package repository;

import Model.Comment;
import Model.Personal;
import Model.Post;
import com.example.javagram.HelloApplication;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentRepository implements ICommentRepository{

    BaseRepository baseRepository = new BaseRepository();
    PostRepository postRepository = new PostRepository();
    Personal personal = HelloApplication.getPersonal();

    Connection connection = null;
    PreparedStatement prsm = null;

    @Override
    public Integer setComment(Post post, String txt) throws SQLException, ClassNotFoundException {
        connection = baseRepository.getConnection();
        int comment = postRepository.getComment(post);
        prsm = connection.prepareStatement("INSERT into tbl_comments (user_name1, user_name2, post," +
                " post_date, comment_text) values (?, ?, ?, ?, ?)");
        prsm.setString(1, post.getName());
        prsm.setString(2, personal.getP_username());
        prsm.setString(3, post.getText());
        prsm.setTimestamp(4, post.getTime());
        prsm.setString(5, txt);
        int result = prsm.executeUpdate();
        if(result==1)
        {
            connection.close();
            return comment+1;
        }
        else
        {
            connection.close();

            return -1;
        }
    }

    @Override
    public List<Comment> getComments(Post post) throws SQLException, ClassNotFoundException {
        Comment comment = null;
        String user1 = null;
        String user2 = null;
        String posttxt = null;
        Timestamp postdate = null;
        String text = null;
        Timestamp commentdate = null;
        List<Comment> comments = new ArrayList();
        connection = baseRepository.getConnection();
        prsm = connection.prepareStatement("SELECT * from tbl_comments where user_name1 = ?" +
                " and post = ? and post_date = ?");
        prsm.setString(1, post.getName());
        prsm.setString(2, post.getText());
        prsm.setTimestamp(3, post.getTime());
        ResultSet resultSet = prsm.executeQuery();
        while (resultSet.next()){
            user1 = resultSet.getString("user_name1");
            user2 = resultSet.getString("user_name2");
            posttxt = resultSet.getString("post");
            postdate = resultSet.getTimestamp("post_date");
            text = resultSet.getString("comment_text");
            commentdate = resultSet.getTimestamp("comment_date");
            comment = new Comment(user1, user2, posttxt, postdate, text, commentdate);
            comments.add(comment);
        }
        return comments;
    }
}

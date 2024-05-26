package repository;

import Model.Comment;
import Model.Post;

import java.sql.SQLException;
import java.util.List;

public interface ICommentRepository {
    Integer setComment(Post post, String comment) throws SQLException, ClassNotFoundException;

    List<Comment> getComments(Post post) throws SQLException, ClassNotFoundException;
}

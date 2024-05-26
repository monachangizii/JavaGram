package repository;

import Model.Post;

import java.sql.SQLException;

public interface ILikeRepository {
    Integer setLike(Post post) throws SQLException, ClassNotFoundException;
}

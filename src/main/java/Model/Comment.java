package Model;

import java.sql.Timestamp;

public class Comment {
    String username1;
    String username2;
    String posttxt;
    Timestamp postdate;
    String text;
    Timestamp commentdate;

    public String getUsername1() {
        return username1;
    }

    public void setUsername1(String username1) {
        this.username1 = username1;
    }

    public Comment(String username1, String username2, String post, Timestamp postdate, String text, Timestamp commentdate) {
        this.username1 = username1;
        this.username2 = username2;
        this.posttxt = post;
        this.postdate = postdate;
        this.text = text;
        this.commentdate = commentdate;
    }

    public String getUsername2() {
        return username2;
    }

    public void setUsername2(String username2) {
        this.username2 = username2;
    }

    public String getPost() {
        return posttxt;
    }

    public void setPost(String post) {
        this.posttxt = post;
    }

    public Timestamp getPostdate() {
        return postdate;
    }

    public void setPostdate(Timestamp postdate) {
        this.postdate = postdate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getCommentdate() {
        return commentdate;
    }

    public void setCommentdate(Timestamp commentdate) {
        this.commentdate = commentdate;
    }
}

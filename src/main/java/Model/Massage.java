package Model;

import java.sql.Time;
import java.sql.Timestamp;

public class Massage {
    String user1;
    String user2;
    String msg;
    Timestamp date;
    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Massage(String user1, String user2, String msg, Timestamp date) {
        this.user1 = user1;
        this.user2 = user2;
        this.msg = msg;
        this.date = date;
    }


}

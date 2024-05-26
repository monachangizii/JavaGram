package Model;

import java.util.List;

public class Personal {
    String p_name;
    String p_lastname;
    String p_username;
    String p_pass;
    String p_email;
    String p_country;
    List<Personal> followers;
    List<Personal> followings;

    public Personal(String p_name, String p_lastname, String p_username, String p_pass, String p_email, String p_country) {
        this.p_name = p_name;
        this.p_lastname = p_lastname;
        this.p_username = p_username;
        this.p_pass = p_pass;
        this.p_email = p_email;
        this.p_country = p_country;
    }

    public Personal(String p_username) {
        this.p_username = p_username;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_lastname() {
        return p_lastname;
    }

    public void setP_lastname(String p_lastname) {
        this.p_lastname = p_lastname;
    }

    public String getP_username() {
        return p_username;
    }

    public void setP_username(String p_username) {
        this.p_username = p_username;
    }

    public String getP_pass() {
        return p_pass;
    }

    public void setP_pass(String p_pass) {
        this.p_pass = p_pass;
    }

    public String getP_email() {
        return p_email;
    }

    public void setP_email(String p_email) {
        this.p_email = p_email;
    }

    public String getP_country() {
        return p_country;
    }

    public void setP_country(String p_country) {
        this.p_country = p_country;
    }
}
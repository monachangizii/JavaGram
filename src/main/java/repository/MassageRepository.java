package repository;

import Model.Massage;
import Model.Personal;
import com.example.javagram.HelloApplication;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MassageRepository implements IMassageRepository{
    BaseRepository baseRepository = new BaseRepository();
    Connection connection = null;
    PreparedStatement prsm = null;
    Massage massage = null;
    @Override
    public List<Massage> getMassages(Personal personal) throws SQLException, ClassNotFoundException {
        connection = baseRepository.getConnection();
        prsm = connection.prepareStatement("SELECT * from tbl_direct_massages where user_name2 = ?");
        prsm.setString(1, personal.getP_username());
        ResultSet resultSet = prsm.executeQuery();
        String user1, user2, msg;
        Timestamp date;
        List<Massage> massages = new ArrayList();
        while (resultSet.next()){
            user1 = resultSet.getString("user_name1");
            user2 = personal.getP_username();
            msg = resultSet.getString("massage_txt");
            date = resultSet.getTimestamp("date");
            massage = new Massage(user1,user2,msg,date);
            massages.add(massage);
        }
        connection.close();
        return massages;
    }

    @Override
    public void sendMassage(Personal personal, String txt) throws SQLException, ClassNotFoundException {
        connection = baseRepository.getConnection();
        prsm = connection.prepareStatement("INSERT INTO tbl_direct_massages (user_name1, user_name2, " +
                "massage_txt) values (?, ?, ?)");
        prsm.setString(1, HelloApplication.getPersonal().getP_username());
        prsm.setString(2, personal.getP_username());
        prsm.setString(3, txt);
        prsm.executeUpdate();
        connection.close();
    }
}

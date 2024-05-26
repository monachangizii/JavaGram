package repository;

import Model.Massage;
import Model.Personal;

import java.sql.SQLException;
import java.util.List;

public interface IMassageRepository {
    List<Massage> getMassages(Personal personal) throws SQLException, ClassNotFoundException;
    void sendMassage(Personal personal, String txt) throws SQLException, ClassNotFoundException;
}

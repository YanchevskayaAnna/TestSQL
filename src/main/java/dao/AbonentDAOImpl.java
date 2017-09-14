package dao;

import model.Abonent;

import java.util.ArrayList;
import java.util.List;

import java.sql.*;


/**
 * Created by IT-Univer004 on 14.09.2017.
 */
public class AbonentDAOImpl implements AbonentDAO {
    private Connection connection;

    public AbonentDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Abonent> getAll() {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM abonents;");) {

            List<Abonent> abonentList = getAllAbonentsFromResultSet(resultSet);

            return abonentList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Abonent getEntityById(Integer id) {
        Abonent abonent = new Abonent();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM abonents WHERE abonent_id=" + id + ";")) {

            if (resultSet.next()) {
                abonent.setId(resultSet.getInt("abonent_id"));
                abonent.setName(resultSet.getString("abonent_name"));
                return abonent;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public boolean update(Abonent entity) {
        String sqlQuery = "UPDATE abonents SET abonent_name=? WHERE abonent_id=?";

        if (getEntityById(entity.getId()) == null) {
            return false;
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean create(Abonent entity) {
        String sqlQuery = "INSERT INTO abonents (abonent_name) VALUES (?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)){
            preparedStatement.setString(1, entity.getName());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public List<Abonent> getAllAbonentsFromResultSet(ResultSet resultSet) throws SQLException {

        List<Abonent> abonentList = new ArrayList<>();
        while (resultSet.next()) {
            Abonent abonent = new Abonent();
            abonent.setId(resultSet.getInt("abonent_id"));
            abonent.setName(resultSet.getString("abonent_name"));
            abonentList.add(abonent);
        }
        return abonentList;
    }


}

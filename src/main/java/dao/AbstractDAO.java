package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by IT-Univer004 on 14.09.2017.
 */
public interface AbstractDAO<E> {
    List<E> getAll();

    E getEntityById(Integer id);

    boolean update(E entity);

    boolean create(E entity);

    default boolean delete(Connection connection, String tableName, int id) {
        String sqlQuery = "DELETE FROM " + tableName + " WHERE " + tableName.substring(0, tableName.length() - 1) + "_id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}

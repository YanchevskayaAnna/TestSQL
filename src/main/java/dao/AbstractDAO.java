package dao;

import com.sun.javafx.image.BytePixelSetter;
import exceptions.NotFoundIDColumnException;
import model.Abonent;
import model.Column;
import model.Id;
import model.Table;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by IT-Univer004 on 14.09.2017.
 */
public interface AbstractDAO<E> {

    List<E> getAll(Connection connection);

    //E getEntityById(Integer id); Old method
    default E getEntityById(Connection connection, Integer id, Class<E> cls)throws Exception {
        //find the name of the corresponding table in the database
        Table table = cls.getAnnotation(Table.class);
        String tableName = table == null ? cls.getName() + "s": table.name();

        //find columns, find id columns
        List<String> listColumns = new ArrayList<String>();
        String idColumn = "";
        for(Field field : cls.getDeclaredFields()){
            Column columnAnnotation = field.getAnnotation(Column.class);
            Id idAnnotation = field.getAnnotation(Id.class);
            if (idAnnotation != null) {
                idColumn = field.getName();
            }
            if (columnAnnotation != null) {
                listColumns.add(field.getName());
            }
        }

        if (idColumn == "") {throw new NotFoundIDColumnException();}
        idColumn = cls.getSimpleName() + "_" + idColumn;

        E entity = cls.newInstance();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName + " WHERE " + idColumn + " = " + id + ";")) {

            if (resultSet.next()) {
                for (String field: listColumns) {
                    Class<?>[] paramTypes = {String.class, int.class};
                    try {
                        Method setter = cls.getDeclaredMethod("set" + (field.substring(0,1).toUpperCase() + field.substring(1)), paramTypes[1]);
                        setter.invoke(entity, resultSet.getInt(cls.getSimpleName() + "_" + field));}
                    catch (NoSuchMethodException e){
                        Method setter = cls.getDeclaredMethod("set" + (field.substring(0,1).toUpperCase() + field.substring(1)), paramTypes[0]);
                        setter.invoke(entity, resultSet.getString(cls.getSimpleName() + "_" + field));
                    }

                }
                return entity;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
     }

    boolean update(Connection connection, E entity);

    boolean create(Connection connection, E entity);

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

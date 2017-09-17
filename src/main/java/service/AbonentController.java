package service;

import dao.AbonentDAO;
import model.Abonent;

import java.sql.Connection;
import java.util.List;

/**
 * Created by IT-Univer004 on 14.09.2017.
 */
public class AbonentController {

    private AbonentDAO abonentDAO;
    private Connection connection;

    public AbonentController(AbonentDAO abonentDAO, Connection connection) {
        this.abonentDAO = abonentDAO;
        this.connection = connection;
    }

    public List<Abonent> getAllAbonents() {
        return abonentDAO.getAll(connection);
    }

    public Abonent getEntityById(Integer id,  Connection connection) throws Exception  {
        return abonentDAO.getEntityById(connection, id, Abonent.class);
    }

}

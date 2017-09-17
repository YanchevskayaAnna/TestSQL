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

    public AbonentController(AbonentDAO abonentDAO) {
        this.abonentDAO = abonentDAO;
    }

    public List<Abonent> getAllAbonents() {
        return abonentDAO.getAll();
    }

    public Abonent getEntityById(Integer id,  Connection connection) throws Exception  {
        return abonentDAO.getEntityById(id, connection, Abonent.class);
    }

}


import dao.AbonentDAOImpl;
import model.Abonent;
import org.junit.*;
import service.AbonentController;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;



/**
 * Created by IT-Univer004 on 14.09.2017.
 */
public class TestAbonentController {

    private static final String PROPERTIES_PATH = "src/main/resources/properties";

    private static Connection connection;
    private static Properties properties;
    private static AbonentController abonentController;

    @BeforeClass
    public static void beforeClass() throws IOException, SQLException {

        properties = new Properties();
        properties.load(new FileInputStream(new File(PROPERTIES_PATH)));
        connection = DriverManager.getConnection(
                properties.getProperty("URL"),
                properties.getProperty("USER"),
                properties.getProperty("PASSWORD"));
        abonentController = new AbonentController(new AbonentDAOImpl(connection));
    }

    @AfterClass
    public static void afterClass() throws SQLException {
        connection.close();
    }

    @Test
    public void getAllAbonents() {
        List<Abonent> abonentList = abonentController.getAllAbonents();
        Assert.assertNotNull(abonentList);
    }

    @Test
    public void getNumberAbonents() {
        List<Abonent> abonentList = abonentController.getAllAbonents();
        Assert.assertEquals(3, abonentList.size());
    }

}

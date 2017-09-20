
import dao.AbonentDAO;
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
import java.util.Properties;



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
        abonentController = new AbonentController(new AbonentDAO(connection));
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

    @Test
    public void getAbonentbiId() {
        try {
            Abonent abonent = abonentController.getEntityById(1);
            Assert.assertNotNull(abonent);
            Assert.assertEquals("Yanchevskaya Anna", abonent.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

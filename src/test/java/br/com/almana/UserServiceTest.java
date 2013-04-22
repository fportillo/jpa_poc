package br.com.almana;

import static org.junit.Assert.*;

import java.util.Properties;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.almana.domain.User;
import br.com.almana.service.UserService;

public class UserServiceTest {

    private static Context context;

    private static UserService userService;

    @BeforeClass
    public static void setup() throws NamingException {
        Properties props = new Properties();
        props.put("myDataSource", "new://Resource?type=javax.sql.DataSource");
        props.put("myDataSource.defaultAutoCommit", true);
        props.put("myDataSource.jdbcDriver","com.mysql.jdbc.Driver");
        props.put("myDataSource.jdbcUrl", "jdbc:mysql://127.0.0.1:3306/jpa_poc");
        props.put("myDataSource.jtaManaged", true);
        props.put("myDataSource.userName", "jpa_poc");
        props.put("myDataSource.password", "jpa_poc");
        props.put("myDataSource.validationQuery", "SELECT 1");
        context = EJBContainer.createEJBContainer(props).getContext();
        userService = (UserService) context.lookup("java:global/jpa_poc/UserService");
    }

    @Test
    public void testApp() {
        User johnDoe = new User();
        johnDoe.setName("John Doe");
        johnDoe.setAge(33);
        userService.save(johnDoe);
        assertNotNull(userService.findById(1l));
    }

    @AfterClass
    public static void tearDown() throws NamingException {
        context.close();
    }
}

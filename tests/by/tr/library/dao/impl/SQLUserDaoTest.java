package by.tr.library.dao.impl;

import by.tr.library.dao.exception.ConnectionPoolException;
import by.tr.library.dao.pool.ConnectionPool;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by ivanpryshchepau on 7/18/16.
 */
public class SQLUserDaoTest {

    @BeforeMethod
    public void setUp() throws Exception {
        try {
            ConnectionPool.connectionPool.initPoolData();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetCatalog() throws Exception {
        SQLUserDao dao = new SQLUserDao();
        dao.getCatalog();
    }

    @Test
    public void testRegisterUser() throws Exception {
        SQLUserDao dao = new SQLUserDao();
        dao.registerUser("1", "1");
    }

    @Test
    public void testTakeBook() throws Exception {
        SQLUserDao dao = new SQLUserDao();
        dao.takeBook("Fight club");
    }

    @Test(dependsOnMethods = "testTakeBook")
    public void testReturnBook() throws Exception {
        SQLUserDao dao = new SQLUserDao();
        dao.returnBook("Fight club");
    }

    @AfterMethod
    public void tearDown() throws Exception {
        ConnectionPool.connectionPool.dispose();
    }
}
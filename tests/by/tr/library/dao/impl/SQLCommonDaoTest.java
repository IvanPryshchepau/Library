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
public class SQLCommonDaoTest {

    @BeforeMethod
    public void setUp() throws Exception {
        try {
            ConnectionPool.connectionPool.initPoolData();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAuthorization() throws Exception {
        SQLCommonDao dao = new SQLCommonDao();
        dao.authorization("mylogin1", "mypassword1");
    }

    @AfterMethod
    public void tearDown() throws Exception {
        ConnectionPool.connectionPool.dispose();
    }
}
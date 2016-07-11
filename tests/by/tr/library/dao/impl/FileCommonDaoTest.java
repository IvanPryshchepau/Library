package by.tr.library.dao.impl;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by ivanpryshchepau on 7/11/16.
 */
public class FileCommonDaoTest {
    @Test
    public void testAuthorization() throws Exception {
        FileCommonDao fcd = new FileCommonDao();
        assertTrue(fcd.authorization("mylogin", "mypassword"));
    }

}
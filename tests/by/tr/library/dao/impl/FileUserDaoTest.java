package by.tr.library.dao.impl;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by ivanpryshchepau on 7/11/16.
 */
public class FileUserDaoTest {
    @Test
    public void testGetCatalog() throws Exception {

    }

    @Test
    public void testRegisterUser() throws Exception {
        FileUserDao fud = new FileUserDao();
        assertTrue(fud.registerUser("user", "user"));
    }

    @Test
    public void testTakeBook() throws Exception {
        FileUserDao fud = new FileUserDao();
        assertTrue(fud.takeBook("Fight club"));
    }

    @Test(dependsOnMethods = "testTakeBook")
    public void testReturnBook() throws Exception {
        FileUserDao fud = new FileUserDao();
        assertTrue(fud.returnBook("Fight club"));
    }

}
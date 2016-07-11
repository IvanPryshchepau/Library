package by.tr.library.dao.impl;

import by.tr.library.bean.Book;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by ivanpryshchepau on 7/11/16.
 */
public class FileAdminDaoTest {

    @DataProvider(name = "blockUser")
    public static Object[][] blockUser() {
        return new Object[][] {
                {"mylogin", true},
                {"mylog", false},
                {"my", false}
        };
    }

    @Test(dataProvider = "blockUser")
    public void testBlockUser(String login, boolean result) throws Exception {
        FileAdminDao fad = new FileAdminDao();
        assertEquals(fad.blockUser(login), result);
    }

    @Test
    public void testAddBook() throws Exception {
        Book book = new Book();
        book.setTitle("123");
        FileAdminDao fad = new FileAdminDao();
        assertTrue(fad.addBook(book));
    }

    @Test(dependsOnMethods = "testAddBook")
    public void testDeleteBook() throws Exception {
        Book book = new Book();
        book.setTitle("123");
        FileAdminDao fad = new FileAdminDao();
        assertTrue(fad.deleteBook(book));
    }

}
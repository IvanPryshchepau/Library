package by.tr.library.dao.impl;

import by.tr.library.bean.Book;
import by.tr.library.dao.exception.ConnectionPoolException;
import by.tr.library.dao.pool.ConnectionPool;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by ivanpryshchepau on 7/18/16.
 */
public class SQLAdminDaoTest {

    @BeforeMethod
    public void setUp() throws Exception {
        try {
            ConnectionPool.connectionPool.initPoolData();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBlockUser() throws Exception {
        SQLAdminDao dao = new SQLAdminDao();
        dao.blockUser("mylog");
    }

    @Test
    public void testAddBook() throws Exception {
        SQLAdminDao dao = new SQLAdminDao();
        Book book = new Book();
        book.setTitle("Harry Potter");
        book.setPrice(1000);
        book.setAvailable("");
        dao.addBook(book);
    }

    @Test(dependsOnMethods = "testAddBook")
    public void testDeleteBook() throws Exception {
        SQLAdminDao dao = new SQLAdminDao();
        Book book = new Book();
        book.setTitle("Harry Potter");
        book.setPrice(1000);
        book.setAvailable("");
        dao.deleteBook(book);
    }

    @AfterMethod
    public void tearDown() throws Exception {
        ConnectionPool.connectionPool.dispose();
    }
}
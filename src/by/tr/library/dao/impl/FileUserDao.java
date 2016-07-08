package by.tr.library.dao.impl;

import by.tr.library.bean.Book;
import by.tr.library.dao.UserDao;
import by.tr.library.dao.exception.DAOException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivanpryshchepau on 7/8/16.
 */
public class FileUserDao implements UserDao {

    @Override
    public List<Book> getCatalog() throws DAOException {

        List<Book> bookList = new ArrayList<Book>();

        File file = new File("Library.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String currentLine;
            while((currentLine = reader.readLine()) != null) {
                Book book = new Book();
                book.expand(currentLine);
                bookList.add(book);
            }

        } catch (IOException e) {
            throw new DAOException("DAO message", e);
        }

        return bookList;
    }

    @Override
    public boolean registerUser(String login, String password) throws DAOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Users.txt", true));
             BufferedReader reader = new BufferedReader(new FileReader("Users.txt"))){

            String line;
            while ((line = reader.readLine()) != null) {
                String[] result = line.split("((^user)=')|(', (password|status)=')|('$)");
                if (login.equals(result[1])){
                    return false;
                }
            }


            writer.append("user='" + login + "', password='" + password +
                    "', status='user'" + System.getProperty("line.separator"));
            return true;

        } catch (IOException e) {
            throw new DAOException("DAO message", e);
        }
    }

    @Override
    public boolean takeBook(String title) throws DAOException {

        Book book = new Book();
        File file = new File("Library.txt");
        File tempFile = new File("LibraryTemp.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String currentLine;
            while((currentLine = reader.readLine()) != null) {
                book.expand(currentLine);
                if (title.equals(book.getTitle())){
                    book.setAvailable("taken");
                    writer.write(book.toString() + System.getProperty("line.separator"));
                    continue;
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            tempFile.renameTo(file);
            return true;
        } catch (IOException e) {
            throw new DAOException("DAO message", e);
        }

    }

    @Override
    public boolean returnBook(String title) throws DAOException {
        Book book = new Book();
        File file = new File("Library.txt");
        File tempFile = new File("LibraryTemp.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String currentLine;
            while((currentLine = reader.readLine()) != null) {
                book.expand(currentLine);
                if (title.equals(book.getTitle())){
                    book.setAvailable(null);
                    writer.write(book.toString() + System.getProperty("line.separator"));
                    continue;
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            tempFile.renameTo(file);
            return true;
        } catch (IOException e) {
            throw new DAOException("DAO message", e);
        }
    }

    @Override
    public Book getBookByTitle(String title) {
        // TODO Auto-generated method stub
        return null;
    }

}

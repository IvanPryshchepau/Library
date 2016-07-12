package by.tr.library.dao.impl;

import by.tr.library.bean.Book;
import by.tr.library.dao.AdminDao;
import by.tr.library.dao.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

/**
 * Created by ivanpryshchepau on 7/8/16.
 */
public class FileAdminDao implements AdminDao {

    private static final Logger LOG = LogManager.getRootLogger();

    @Override
    public boolean blockUser(String login) throws DAOException {
        File file = new File("Users.txt");
        File tempFile = new File("UsersTemp.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))){

            String line;
            boolean status = false;
            while ((line = reader.readLine()) != null) {
                String[] result = line.split("((^user)=')|(', (password|status)=')|('$)");
                if (login.equals(result[1]) && result[3].equals("user")) {
                    result[3] = "blocked";
                    writer.write("user='" + result[1] + "', password='" + result[2] + "', status='" +
                            result[3] + "'" + System.getProperty("line.separator"));
                    status = true;
                    continue;
                }
                writer.write(line + System.getProperty("line.separator"));
            }

            tempFile.renameTo(file);

            if (!status){
                LOG.error("User not found(Block user)");
            }
            return status;
        } catch (IOException e) {
            LOG.error("Block user error DAO IOException");
            throw new DAOException("DAO message", e);
        }

    }

    @Override
    public boolean addBook(Book book) throws DAOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Library.txt", true))){

            writer.append(book.toString() + System.getProperty("line.separator"));
            return true;

        } catch (IOException e) {
            LOG.error("Add book error DAO IOException");
            throw new DAOException("DAO message", e);
        }
    }

    @Override
    public boolean deleteBook(Book book) throws DAOException {
        File file = new File("Library.txt");
        File tempFile = new File("LibraryTemp.txt");
        boolean status = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String currentLine;
            String lineToRemove = book.toString();

            while((currentLine = reader.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                if(trimmedLine.equals(lineToRemove)) {
                    status = true;
                    continue;
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            tempFile.renameTo(file);
            if (!status){
                LOG.error("Book not found(Delete book)");
            }
        } catch (IOException e) {
            LOG.error("Delete book error DAO IOException");
            throw new DAOException("DAO message", e);
        }
        return status;
    }

}

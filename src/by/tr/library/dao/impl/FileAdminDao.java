package by.tr.library.dao.impl;

import by.tr.library.bean.Book;
import by.tr.library.dao.AdminDao;
import by.tr.library.dao.exception.DAOException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivanpryshchepau on 7/8/16.
 */
public class FileAdminDao implements AdminDao {

    @Override
    public boolean blockUser(String login) throws DAOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("Users.txt"))){

            String line;
            List<String[]> lines = new ArrayList<String[]>();
            while ((line = reader.readLine()) != null) {
                String[] result = line.split("((^user)=')|(', (password|status)=')|('$)");
                lines.add(result);
            }

            boolean status = false;
            BufferedWriter writer = new BufferedWriter(new FileWriter("Users.txt"));
            for (String[] field : lines) {
                if (login.equals(field[1])) {
                    field[3] = "blocked";
                    status = true;
                }
                writer.write("user='" + field[1] + "', password='" + field[2] + "', status='" + field[3] + "'");
                writer.append('\n');
            }

            writer.flush();

            return status;
        } catch (IOException e) {
            throw new DAOException("DAO message", e);
        }

    }

    @Override
    public boolean addBook(Book book) throws DAOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Library.txt", true))){

            writer.append(book.toString() + System.getProperty("line.separator"));
            return true;

        } catch (IOException e) {
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
        } catch (IOException e) {
            throw new DAOException("DAO message", e);
        }
        return status;
    }

}

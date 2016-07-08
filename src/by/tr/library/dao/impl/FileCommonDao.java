package by.tr.library.dao.impl;

import by.tr.library.dao.CommonDao;
import by.tr.library.dao.exception.DAOException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivanpryshchepau on 7/8/16.
 */
public class FileCommonDao implements CommonDao{

    @Override
    public boolean authorization(String login, String password) throws DAOException {

        try (BufferedReader reader = new BufferedReader(new FileReader("Users.txt"))){

            String line;
            List<String[]> lines = new ArrayList<String[]>();
            while ((line = reader.readLine()) != null) {
                String[] result = line.split("((^user)=')|(', (password|status)=')|('$)");
                lines.add(result);
            }

            for (String[] field : lines) {
                if (login.equals(field[1]) && password.equals(field[2]) && !field[3].equals("blocked")){
                    return true;
                }
            }

        } catch (IOException e) {
            throw new DAOException("DAO message", e);
        }
        return false;
    }

}
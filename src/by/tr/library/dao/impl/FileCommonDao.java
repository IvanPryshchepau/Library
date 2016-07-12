package by.tr.library.dao.impl;

import by.tr.library.dao.CommonDao;
import by.tr.library.dao.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * Created by ivanpryshchepau on 7/8/16.
 */
public class FileCommonDao implements CommonDao{

    private static final Logger LOG = LogManager.getRootLogger();

    @Override
    public boolean authorization(String login, String password) throws DAOException {

        try (BufferedReader reader = new BufferedReader(new FileReader("Users.txt"))){

            String line;
            while ((line = reader.readLine()) != null) {
                String[] result = line.split("((^user)=')|(', (password|status)=')|('$)");
                if (login.equals(result[1]) && password.equals(result[2]) && !result[3].equals("blocked")){
                    return true;
                }
            }

            LOG.error("User not found or blocked(Authorization)");

        } catch (IOException e) {
            LOG.error("Authorization error DAO IOException");
            throw new DAOException("DAO message", e);
        }
        return false;
    }

}

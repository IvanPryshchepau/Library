package by.tr.library.dao.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.tr.library.dao.CommonDao;
import by.tr.library.dao.exception.DAOException;

public class SQLCommonDao implements CommonDao {

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
				if (login.equals(field[1]) && password.equals(field[2])){
					return true;
				}
			}

		} catch (IOException e) {
			throw new DAOException("DAO message", e);
		}
		return false;
	}

}

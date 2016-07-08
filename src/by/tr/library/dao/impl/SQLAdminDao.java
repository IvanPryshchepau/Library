package by.tr.library.dao.impl;

import by.tr.library.bean.Book;
import by.tr.library.dao.AdminDao;
import by.tr.library.dao.exception.DAOException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SQLAdminDao implements AdminDao {

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
	public boolean addBook(Book book) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBook(Book book) throws DAOException {
		return false;
	}

}

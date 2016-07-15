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


		return false;
	}

}

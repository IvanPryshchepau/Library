package by.tr.library.dao;

import by.tr.library.dao.impl.*;

public class DAOFactory {
	private static final DAOFactory factory = new DAOFactory();
	
	private final CommonDao commonDao = new SQLCommonDao();
	private final UserDao userDao = new SQLUserDao();
	private final AdminDao adminDao = new SQLAdminDao();
	
	private DAOFactory(){}
	
	
	public static DAOFactory getInstance(){
		return factory;
	}
	

	public CommonDao getCommonDao() {
		return commonDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public AdminDao getAdminDao() {
		return adminDao;
	}

}

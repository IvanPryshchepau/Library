package by.tr.library.start;

import by.tr.library.bean.Request;
import by.tr.library.bean.Response;
import by.tr.library.controller.Controller;
import by.tr.library.dao.exception.ConnectionPoolException;
import by.tr.library.dao.pool.ConnectionPool;

public class Main {

	public static void main(String[] args) {
		try {
			ConnectionPool.connectionPool.initPoolData();
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
		}
		Controller controller = new Controller();
		
		
		Request request = new Request();
		request.setCommandName("ADD_BOOK_COMMAND");
		request.setLogin("mylogin1");
		request.setPassword("mypassword1");
		request.setTitle("Fight club");
		request.setPrice(100);

		Response response = controller.doAction(request);
		
		if(response.getErrorMessage() != null){
			System.out.println(response.getErrorMessage());
		}else{
			System.out.println(response.getMessage());
		}
		
		
		ConnectionPool.connectionPool.dispose();
	}

}

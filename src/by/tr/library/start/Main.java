package by.tr.library.start;

import by.tr.library.bean.Request;
import by.tr.library.bean.Response;
import by.tr.library.controller.Controller;

public class Main {

	public static void main(String[] args) {
		Controller controller = new Controller();
		
		
		Request request = new Request();
		request.setCommandName("RETURN_BOOK");
		request.setLogin("mylog");
		request.setPassword("mypassword");
		request.setTitle("War and Peace");
		request.setPrice(1000);

		Response response = controller.doAction(request);
		
		if(response.getErrorMessage() != null){
			System.out.println(response.getErrorMessage());
		}else{
			System.out.println(response.getMessage());
		}
		
		

	}

}

package by.tr.library.command.impl;

import by.tr.library.bean.Request;
import by.tr.library.bean.Response;
import by.tr.library.command.Command;
import by.tr.library.command.exception.CommandException;
import by.tr.library.service.LibraryService;
import by.tr.library.service.ServiceFactory;
import by.tr.library.service.exception.ServiceException;

public class AddBookCommand implements Command {

	@Override
	public Response execute(Request request) throws CommandException {
		String title = request.getTitle();
		int price = request.getPrice();

/////////////////////////
		ServiceFactory factory = ServiceFactory.getInstance();
		LibraryService service = factory.getLibraryService();

/////////////////////////
		Response response = new Response();
		try {
			service.addBook(title, price);
			response.setErrorMessage(null);
			response.setMessage("Addition is OK");
		} catch (ServiceException e) {
			throw new CommandException("command message", e);
		}

		return response;
	}

}

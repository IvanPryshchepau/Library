package by.tr.library.command.impl;

import java.util.List;

import by.tr.library.bean.Book;
import by.tr.library.bean.Request;
import by.tr.library.bean.Response;
import by.tr.library.command.Command;
import by.tr.library.command.exception.CommandException;
import by.tr.library.service.LibraryService;
import by.tr.library.service.ServiceFactory;
import by.tr.library.service.exception.ServiceException;

public class GetCatalog implements Command{

	@Override
	public Response execute(Request request) throws CommandException {

		ServiceFactory factory = ServiceFactory.getInstance();
		LibraryService service = factory.getLibraryService();

		List<Book> books;
		try {
			books = service.getCatalog();
		} catch (ServiceException e) {
			throw new CommandException("command message", e);
		}
		
		Response response = new Response();
		response.setErrorMessage(null);
		response.setMessage("Catalog ready");
		response.setListBook(books);
		
		return response;
	}

}

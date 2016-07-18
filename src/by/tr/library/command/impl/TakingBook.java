package by.tr.library.command.impl;

import by.tr.library.bean.Request;
import by.tr.library.bean.Response;
import by.tr.library.command.Command;
import by.tr.library.command.exception.CommandException;
import by.tr.library.service.ClientService;
import by.tr.library.service.LibraryService;
import by.tr.library.service.ServiceFactory;
import by.tr.library.service.exception.ServiceException;

/**
 * Created by ivanpryshchepau on 7/7/16.
 */
public class TakingBook implements Command {
    @Override
    public Response execute(Request request) throws CommandException {
        String title = request.getTitle();

/////////////////////////
        ServiceFactory factory = ServiceFactory.getInstance();
        LibraryService service = factory.getLibraryService();

        Response response = new Response();
        try {
            service.takingBook(title);
            response.setErrorMessage(null);
            response.setMessage("Book taken");
        } catch (ServiceException e) {
            throw new CommandException("command message", e);
        }

        return response;

    }
}

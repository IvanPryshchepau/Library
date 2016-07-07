package by.tr.library.command.impl;

import by.tr.library.bean.Request;
import by.tr.library.bean.Response;
import by.tr.library.command.Command;
import by.tr.library.command.exception.CommandException;
import by.tr.library.service.ClientService;
import by.tr.library.service.ServiceFactory;
import by.tr.library.service.exception.ServiceException;

/**
 * Created by ivanpryshchepau on 7/7/16.
 */
public class BlockUser implements Command {
    @Override
    public Response execute(Request request) throws CommandException {
        String login = request.getLogin();

/////////////////////////
        ServiceFactory factory = ServiceFactory.getInstance();
        ClientService service = factory.getClientService();

/////////////////////////
        boolean result;
        try {
            result = service.blockUser(login);
        } catch (ServiceException e) {
            throw new CommandException("command message", e);
        }
        Response response = new Response();
        if (result) {
            response.setErrorMessage(null);
            response.setMessage("Blocking is OK");
        } else {
            response.setErrorMessage("Blocking failed");
            response.setMessage(null);
        }

        return response;
    }
}

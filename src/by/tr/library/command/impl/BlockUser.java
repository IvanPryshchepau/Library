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
        Response response = new Response();
        try {
            service.blockUser(login);
            response.setErrorMessage(null);
            response.setMessage("Blocking is OK");
        } catch (ServiceException e) {
            throw new CommandException("command message", e);
        }



        return response;
    }
}

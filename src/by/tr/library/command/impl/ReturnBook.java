package by.tr.library.command.impl;

import by.tr.library.bean.Request;
import by.tr.library.bean.Response;
import by.tr.library.command.Command;
import by.tr.library.command.exception.CommandException;

/**
 * Created by ivanpryshchepau on 7/7/16.
 */
public class ReturnBook implements Command {
    @Override
    public Response execute(Request request) throws CommandException {
        return null;
    }
}
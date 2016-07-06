package by.tr.library.controller;

import java.util.HashMap;
import java.util.Map;

import by.tr.library.command.Command;
import by.tr.library.command.impl.AddBookCommand;
import by.tr.library.command.impl.AuthorizationCommand;
import by.tr.library.command.impl.GetCalatog;
import by.tr.library.command.impl.RegisterUser;

public class CommandHelper {
	private Map<CommandName, Command> commands = new HashMap<>();
//	private Map<String, Command> commands = new HashMap<>();
	
	public CommandHelper(){
		commands.put(CommandName.AUTHORIZATION_COMMAND, new AuthorizationCommand());
		commands.put(CommandName.ADD_BOOK_COMMAND, new AddBookCommand());
		commands.put(CommandName.REGISTER_USER, new RegisterUser());
		commands.put(CommandName.GET_CATALOG, new GetCalatog());
	}
	
	public Command getCommand(String commandName){//"REGISTER_USER"
		CommandName command = CommandName.valueOf(commandName);
		Command executeCommand = commands.get(command);
		return executeCommand;
	}
	

}

package by.tr.library.controller;

import java.util.HashMap;
import java.util.Map;

import by.tr.library.command.Command;
import by.tr.library.command.impl.*;

public class CommandHelper {
	private Map<CommandName, Command> commands = new HashMap<>();
//	private Map<String, Command> commands = new HashMap<>();
	
	public CommandHelper(){
		commands.put(CommandName.AUTHORIZATION_COMMAND, new AuthorizationCommand());
		commands.put(CommandName.GET_CATALOG, new GetCatalog());
		commands.put(CommandName.ADD_BOOK_COMMAND, new AddBookCommand());
		commands.put(CommandName.DELETE_BOOK_COMMAND, new DeleteBookCommand());
		commands.put(CommandName.REGISTER_USER, new RegisterUser());
		commands.put(CommandName.BLOCK_USER, new BlockUser());
		commands.put(CommandName.TAKING_BOOK, new TakingBook());
		commands.put(CommandName.RETURN_BOOK, new ReturnBook());

	}
	
	public Command getCommand(String commandName){//"REGISTER_USER"
		CommandName command = CommandName.valueOf(commandName);
		Command executeCommand = commands.get(command);
		return executeCommand;
	}
	

}

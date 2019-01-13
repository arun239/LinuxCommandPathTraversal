package com.arungupta.linux_path_traversal.executor;

import com.arungupta.linux_path_traversal.commands.*;
import com.arungupta.linux_path_traversal.constants.Constants;
import com.arungupta.linux_path_traversal.directory.HeirarchyManager;
import com.arungupta.linux_path_traversal.exceptions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by arun on 22/09/18.
 */
public class Executor {
    private final HeirarchyManager heirarchyManager;
    private final Map<String, Command> commandsMap;

    public Executor(HeirarchyManager heirarchyManager) {
        this.heirarchyManager = heirarchyManager;

        this.commandsMap = new HashMap<>();

        registerCommands();
    }

    /**
     * Used to process user entered commands
     * @param userInput
     * @throws CommandNotFoundException
     */
    public void processUserCommand(String userInput) throws CommandNotFoundException {
        String[] splittedInput = userInput.split(" ");
        String commandName = getCommandName(splittedInput);
        java.util.List commandOptions = getCommandOptions(splittedInput);
        java.util.List commandArguments = getCommandArguments(splittedInput);

        if (this.commandsMap.containsKey(commandName)) {
            try {
                this.commandsMap.get(commandName).execute(commandOptions, commandArguments, this.heirarchyManager);
            } catch (InvalidArgumentException e) {
                System.out.println("ERR: Invalid arguments");
            } catch (NoSuchDirectoryException e) {
                System.out.println("ERR : No Such Directory.");
            }
        } else {
            throw new CommandNotFoundException (commandName + "Command Not Found.");
        }
    }


    /**
     * Create a map of possible commands to be used by application
     */
    private void registerCommands() {
        ChangeDirectory changeDirectoryCommand = new ChangeDirectory();
        MakeDirectory makeDirectoryCommand = new MakeDirectory();
        RemoveDirectory removeDirectoryCommand = new RemoveDirectory();
        PresentWorkingDirectory presentWorkingDirectoryCommand = new PresentWorkingDirectory();
        List listCommand = new List();

        this.commandsMap.put(changeDirectoryCommand.getCommandName(), changeDirectoryCommand);
        this.commandsMap.put(makeDirectoryCommand.getCommandName(), makeDirectoryCommand);
        this.commandsMap.put(removeDirectoryCommand.getCommandName(), removeDirectoryCommand);
        this.commandsMap.put(presentWorkingDirectoryCommand.getCommandName(), presentWorkingDirectoryCommand);
        this.commandsMap.put(listCommand.getCommandName(), listCommand);
    }

    /**
     * Extract Command Name from user input
     * @param spaceSeparatedInput
     * @return
     */
    private String getCommandName(String[] spaceSeparatedInput) {
        return spaceSeparatedInput[0];
    }

    /**
     * Extract Command Options from user input
     * @param spaceSeparatedInput
     * @return
     */
    private java.util.List getCommandOptions(String[] spaceSeparatedInput) {
        java.util.List commandOptions = new ArrayList<>();

        for (int i = 1; i < spaceSeparatedInput.length; i++) {
            // First word will be the command name, then options and then arguments.
            if (spaceSeparatedInput[i].substring(0, 1).equalsIgnoreCase(Constants.COMMAND_OPTION_PREFIX)) {
                commandOptions.add(spaceSeparatedInput[i]);
            }
        }
        return commandOptions;
    }

    /**
     * Extract Command Arguments from user input
     * @param spaceSeparatedInput
     * @return
     */
    private java.util.List getCommandArguments(String[] spaceSeparatedInput) {
        // First word will be the command name, then options and then arguments.
        java.util.List commandArguments = new ArrayList<>();

        for (int i = 1; i < spaceSeparatedInput.length; i++) {
            if (!spaceSeparatedInput[i].substring(0, 1).equalsIgnoreCase(Constants.COMMAND_OPTION_PREFIX)) {
                commandArguments.add(spaceSeparatedInput[i]);
            }
        }

        return commandArguments;
    }

}

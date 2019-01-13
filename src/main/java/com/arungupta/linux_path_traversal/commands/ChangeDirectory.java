package com.arungupta.linux_path_traversal.commands;

import com.arungupta.linux_path_traversal.directory.HeirarchyManager;
import com.arungupta.linux_path_traversal.entity.Directory;
import com.arungupta.linux_path_traversal.exceptions.InvalidArgumentException;
import com.arungupta.linux_path_traversal.exceptions.InvalidOptionsException;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by arun on 22/09/18.
 */
public class ChangeDirectory implements Command {
    private static final String COMMAND_NAME = "cd";
    private static final Logger logger = Logger.getLogger(ChangeDirectory.class.getName());


    @Override
    public void execute(List<String> options, List<String> arguments, HeirarchyManager heirarchyManager) throws InvalidArgumentException {
        logger.log(Level.INFO, "Executing Change Directory Command");

        if (!isValidArgument(arguments)) {
            throw new InvalidArgumentException("Invalid Arguments");
        }

        Optional<Directory> directoryOptional = heirarchyManager.getDirectoryFromPath(arguments.get(0));
        if (directoryOptional.isPresent()) {
            heirarchyManager.setCurrentWorkingDirectory(directoryOptional.get());
            System.out.println("SUCC: REACHED");
        } else {
            System.out.println("ERR: INVALID PATH");
        }
    }

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }

    /**
     * Check if argumentlist passed have 1 or more than one arguments
     * @param argumentsList
     * @return
     */
    private boolean isValidArgument (List<String> argumentsList) {
        if (argumentsList == null || argumentsList.size() == 0)
            return false;

        return true;
    }
}

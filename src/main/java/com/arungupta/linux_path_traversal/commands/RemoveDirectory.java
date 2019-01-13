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
public class RemoveDirectory implements Command {
    private static final String COMMAND_NAME = "rm";
    private static final Logger logger = Logger.getLogger(RemoveDirectory.class.getName());

    @Override
    public void execute(List<String> options, List<String> arguments, HeirarchyManager heirarchyManager) throws InvalidArgumentException {
        logger.log(Level.INFO, "Executing Remove Directory Command");

        if (!isValidArgument(arguments)) {
            throw new InvalidArgumentException("Invalid Argument.");
        }


        for (int i = 0; i < arguments.size(); i++) {
            Optional<Directory> directoryOptional = heirarchyManager.getDirectoryFromPath(arguments.get(i));
            if (directoryOptional.isPresent() && directoryOptional.get() != heirarchyManager.getRootDirectory()) {
                directoryOptional.get().getParentDirectory().deleteSubDirectory(directoryOptional.get().getName());
                System.out.println("SUCC: DELETED");
            } else {
                System.out.println("ERR: INVALID PATH");
            }
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

package com.arungupta.linux_path_traversal.commands;

import com.arungupta.linux_path_traversal.constants.Constants;
import com.arungupta.linux_path_traversal.directory.HeirarchyManager;
import com.arungupta.linux_path_traversal.entity.Directory;
import com.arungupta.linux_path_traversal.exceptions.InvalidArgumentException;
import com.arungupta.linux_path_traversal.exceptions.InvalidOptionsException;
import com.arungupta.linux_path_traversal.exceptions.NoSuchDirectoryException;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by arun on 22/09/18.
 */
public class MakeDirectory implements Command {
    private static final String COMMAND_NAME = "mkdir";
    private static final Logger logger = Logger.getLogger(MakeDirectory.class.getName());

    @Override
    public void execute(List<String> options, List<String> arguments, HeirarchyManager heirarchyManager) throws InvalidArgumentException, NoSuchDirectoryException {
        logger.log(Level.INFO, "Executing Make Directory Command");

        if (!isValidArgument(arguments)) {
            throw new InvalidArgumentException("Invalid Argument.");
        }

        for (int i = 0; i < arguments.size(); i++) {
            if (arguments.get(i).indexOf(Constants.PATH_SEPARATOR.charAt(0)) == 0) {  //Absolute path
                String[] directoryNames = arguments.get(i).split(Constants.PATH_SEPARATOR);
                String directoryNameToBeCreated = directoryNames[directoryNames.length - 1];

                Optional<Directory> parentDirectoryForNew = heirarchyManager.getDirectoryFromPath(arguments.get(i).substring(0, arguments.get(i).lastIndexOf(Constants.PATH_SEPARATOR)));

                if (parentDirectoryForNew.isPresent()) {
                    createDirectory(parentDirectoryForNew.get(), directoryNameToBeCreated);
                } else {
                    throw new NoSuchDirectoryException("No Such Directory :" + arguments.get(i));
                }
            } else {
                createDirectory(heirarchyManager.getCurrentWorkingDirectory(), arguments.get(i));
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

    /**
     * Create a new sub directory for a given parent directory
     * @param parentDirectory
     * @param newDirectoryName
     */
    private void createDirectory (Directory parentDirectory, String newDirectoryName) {
        parentDirectory.createNewSubDirectory(newDirectoryName);
    }
}

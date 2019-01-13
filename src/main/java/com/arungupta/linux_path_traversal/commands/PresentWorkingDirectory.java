package com.arungupta.linux_path_traversal.commands;

import com.arungupta.linux_path_traversal.directory.HeirarchyManager;
import com.arungupta.linux_path_traversal.entity.Directory;
import com.arungupta.linux_path_traversal.exceptions.InvalidArgumentException;
import com.arungupta.linux_path_traversal.exceptions.InvalidOptionsException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by arun on 22/09/18.
 */
public class PresentWorkingDirectory implements Command {
    private static final String COMMAND_NAME = "pwd";
    private static final Logger logger = Logger.getLogger(PresentWorkingDirectory.class.getName());

    @Override
    public void execute(List<String> options, List<String> arguments, HeirarchyManager heirarchyManager)  {
        logger.log(Level.INFO, "Executing Present Working Directory Command");

        printPresentWorkingDirectoryPath(heirarchyManager.getCurrentWorkingDirectory());

    }

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }


    /**
     * Print present working Directory Path
     * @param currentWorkingDirectory
     */
    private void printPresentWorkingDirectoryPath (Directory currentWorkingDirectory) {
        System.out.println("PATH: " + currentWorkingDirectory.getFullPath());
    }
}

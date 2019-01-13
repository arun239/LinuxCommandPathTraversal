package com.arungupta.linux_path_traversal.commands;

import com.arungupta.linux_path_traversal.directory.HeirarchyManager;
import com.arungupta.linux_path_traversal.entity.Directory;
import com.arungupta.linux_path_traversal.exceptions.InvalidArgumentException;
import com.arungupta.linux_path_traversal.exceptions.InvalidOptionsException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by arun on 22/09/18.
 */
public class List implements Command {
    private static final String COMMAND_NAME = "ls";
    private static final Logger logger = Logger.getLogger(List.class.getName());

    @Override
    public void execute(java.util.List options, java.util.List arguments, HeirarchyManager heirarchyManager) {
        logger.log(Level.INFO, "Executing List Directory Command");


        listDirectories (heirarchyManager.getCurrentWorkingDirectory());
    }

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }


    /**
     * print all the sub-directories
     * @param currentDirectory
     */
    private void listDirectories (Directory currentDirectory) {
        currentDirectory.printSubDirectories("DIRS : ");
    }
}

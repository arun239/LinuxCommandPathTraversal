package com.arungupta.linux_path_traversal.commands;

import com.arungupta.linux_path_traversal.directory.HeirarchyManager;
import com.arungupta.linux_path_traversal.exceptions.InvalidArgumentException;
import com.arungupta.linux_path_traversal.exceptions.InvalidOptionsException;
import com.arungupta.linux_path_traversal.exceptions.NoSuchDirectoryException;

import java.util.List;

/**
 * Created by arun on 22/09/18.
 */

public interface Command {

    String getCommandName ();

    /**
     * Method to execute different types of commands for different arguments
     * @param options
     * @param arguments
     * @param heirarchyManager
     * @throws InvalidArgumentException
     * @throws InvalidOptionsException
     * @throws NoSuchDirectoryException
     */
    void execute(List<String> options, List<String> arguments, HeirarchyManager heirarchyManager)throws InvalidArgumentException, NoSuchDirectoryException;

}

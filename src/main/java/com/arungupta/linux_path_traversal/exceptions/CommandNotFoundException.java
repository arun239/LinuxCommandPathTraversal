package com.arungupta.linux_path_traversal.exceptions;

/**
 * Created by arun on 22/09/18.
 */
public class CommandNotFoundException extends Exception {
    public CommandNotFoundException(String message) {
        super(message);
    }
}

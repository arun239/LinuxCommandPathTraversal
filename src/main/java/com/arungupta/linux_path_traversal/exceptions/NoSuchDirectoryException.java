package com.arungupta.linux_path_traversal.exceptions;

/**
 * Created by arun on 22/09/18.
 */
public class NoSuchDirectoryException extends Exception {
    public NoSuchDirectoryException(String message) {
        super(message);
    }
}

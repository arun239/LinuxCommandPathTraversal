package com.arungupta.linux_path_traversal.utility;

import com.arungupta.linux_path_traversal.constants.Constants;

/**
 * Created by arun on 22/09/18.
 */
public class PathValidator {
    public static boolean isValidName (String name) {
        if (name.contains(Constants.PATH_SEPARATOR) ||
                name.contains(Constants.ROOT_DIRECTORY_SYMBOL) ||
                name.contains(" ")) {
            return false;
        }

        return true;
    }
}

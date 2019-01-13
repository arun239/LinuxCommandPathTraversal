package com.arungupta.linux_path_traversal.directory;

import com.arungupta.linux_path_traversal.constants.Constants;
import com.arungupta.linux_path_traversal.entity.Directory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by arun on 22/09/18.
 */

public class HeirarchyManager {

    private Directory rootDirectory;
    private Directory currentWorkingDirectory;


    public HeirarchyManager() {
        this.rootDirectory = new Directory(null, Constants.ROOT_DIRECTORY_SYMBOL);  //Parent of Root will be null.
        this.currentWorkingDirectory = this.rootDirectory;
    }

    public Directory getRootDirectory() {
        return this.rootDirectory;
    }

    public Directory getCurrentWorkingDirectory() {
        return currentWorkingDirectory;
    }

    public void setCurrentWorkingDirectory(Directory currentWorkingDirectory) {
        this.currentWorkingDirectory = currentWorkingDirectory;
    }


    public void reset () {
        this.rootDirectory = new Directory(null, Constants.ROOT_DIRECTORY_SYMBOL);
    }

    /**
     * It returns a Directory corresponding to directory path
     * @param path
     * @return
     */
    public Optional<Directory> getDirectoryFromPath (String path) {
        if (path.equalsIgnoreCase("")) {
            return getDirectoryFromAbsolutePath(Constants.ROOT_DIRECTORY_SYMBOL);
        }
        if (path.substring(0, 1).equalsIgnoreCase(Constants.ROOT_DIRECTORY_SYMBOL)) {
            return getDirectoryFromAbsolutePath(path);
        } else {
            return getDirectoryFromRelativePath(path);
        }
    }

    /**
     * It returns a Directory corresponding to directory absolute path
     * @param absolutePath
     * @return
     */
    private Optional<Directory> getDirectoryFromAbsolutePath (String absolutePath) {
        List<String> directoryNames = new ArrayList<>(Arrays.asList(absolutePath.split(Constants.PATH_SEPARATOR)));

        if (directoryNames.size() > 0 && directoryNames.get(0).length() == 0) {
            directoryNames.remove(0);
        }

        return findDirectory(directoryNames, this.rootDirectory);
    }

    /**
     * It returns a Directory corresponding to directory relative path
     * @param relativePath
     * @return
     */
    private Optional<Directory> getDirectoryFromRelativePath (String relativePath) {
        List<String> directoryNames = new ArrayList<>(Arrays.asList(relativePath.split(Constants.PATH_SEPARATOR)));
            return findDirectory(directoryNames, getCurrentWorkingDirectory());
    }

    /**
     * Starting from given directory it searches for list of directories passed to it and returns latest directory
     * @param directoryNames
     * @param startingDirectory
     * @return
     */
    private Optional<Directory> findDirectory (List<String> directoryNames, Directory startingDirectory) {
        Optional<Directory> currentDirectory = Optional.of(startingDirectory);
        for (int i = 0; i < directoryNames.size(); i++) {
            if (currentDirectory.isPresent()) {
                currentDirectory = currentDirectory.get().getChildDirectory(directoryNames.get(i));
            } else {
                break;
            }
        }
        return currentDirectory;
    }
}

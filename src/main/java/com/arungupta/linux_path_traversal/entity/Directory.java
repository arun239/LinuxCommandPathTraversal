package com.arungupta.linux_path_traversal.entity;

import com.arungupta.linux_path_traversal.constants.Constants;
import com.arungupta.linux_path_traversal.utility.PathValidator;

import java.util.*;

/**
 * Created by arun on 22/09/18.
 */
public class Directory extends HierarchyBaseEntity {

    private final Map<String, Directory> childDirectoriesMap;
    private final Map<String, File> childFilesMap;

    private Directory parentDirectory;

    public Directory(Directory parentDirectory, String name) {
        this.childDirectoriesMap = new HashMap<>();
        this.childFilesMap = new HashMap<>();
        this.parentDirectory = parentDirectory;
        this.setName(name);
    }

    public Directory getParentDirectory() {
        return parentDirectory;
    }

    /**
     * Returns Child directory of passed directory
     * @param name
     * @return
     */
    public Optional<Directory> getChildDirectory (String name) {
        Directory subDirectory = this.childDirectoriesMap.get(name);
        if (subDirectory != null) {
            return Optional.of(subDirectory);
        } else {
            return Optional.empty();
        }
    }

    public String getFullPath () {
        Stack<String> ancestorStack = new Stack<>();

        Directory currentDirectory  = this;
        while (currentDirectory != null) {
            ancestorStack.push(currentDirectory.getName());
            currentDirectory = currentDirectory.getParentDirectory();
        }

        return generatePathFromAncestorStack(ancestorStack);
    }


    /**
     * Creates new sub-directory with name passed to it as directory name
     * @param name
     */
    public void createNewSubDirectory (String name) {
        if (!PathValidator.isValidName(name)) {
            System.out.println("Not a valid name " + name);
            return;
        }
        if (this.childDirectoriesMap.containsKey(name)) {
            System.out.println("ERR: DIRECTORY <" + name + "> ALREADY EXISTS");
            return;
        }

        Directory directory = new Directory(this, name);
        this.childDirectoriesMap.put(name, directory);
        System.out.println("SUCC: <" + name + "> CREATED");
    }

    /**
     * Deletes a child directory
     * @param name
     */
    public void deleteSubDirectory (String name) {
        if (!this.childDirectoriesMap.containsKey(name)) {
            System.out.println("Directory with name " + name + " doesn't exists");
            return;
        }

        this.childDirectoriesMap.remove(name);
    }

    /**
     * Prints all child directories
     * @param prefix
     */
    public void printSubDirectories (String prefix) {
        System.out.print(prefix);
        for (String name : this.childDirectoriesMap.keySet()) {
            System.out.print("  " + name);
        }
        System.out.println();
    }

    private String generatePathFromAncestorStack (Stack<String> ancestorsStack) {
        String path = "";
        ancestorsStack.pop();   //Removing the root directory.

        if (ancestorsStack.size() > 0 ) {
            while (ancestorsStack.size() > 0) {
                path = path + Constants.PATH_SEPARATOR + ancestorsStack.pop();
            }
        } else {
            path = Constants.ROOT_DIRECTORY_SYMBOL;  // Because it is the root directory itself.
        }

        return path;
    }

}

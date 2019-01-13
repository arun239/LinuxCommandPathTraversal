package com.arungupta.linux_path_traversal.main;

import com.arungupta.linux_path_traversal.constants.Constants;
import com.arungupta.linux_path_traversal.directory.HeirarchyManager;
import com.arungupta.linux_path_traversal.exceptions.CommandNotFoundException;
import com.arungupta.linux_path_traversal.executor.Executor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Created by arun on 22/09/18.
 */
public class LinuxPathTraversal {
    private static final Logger logger = Logger.getLogger(LinuxPathTraversal.class.getName());
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private Executor executor;
    private HeirarchyManager heirarchyManager;

    public LinuxPathTraversal() {
        this.heirarchyManager = new HeirarchyManager();
        this.executor = new Executor(this.heirarchyManager);
    }

    /**
     * Method to clear all operations performed
     */
    public void clearOperations() {
        this.heirarchyManager = new HeirarchyManager();
        this.executor = new Executor(this.heirarchyManager);
    }

    /**
     * Starting point of application
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        LogManager.getLogManager().reset();
        LinuxPathTraversal linuxPathTraversal = new LinuxPathTraversal();
        String input;
        System.out.println("<Starting your application...> ");

        while (true) {
            input = bufferedReader.readLine();
            if (input.equalsIgnoreCase(Constants.SESSION_CLEAR)) {
                logger.log(Level.INFO, "Session clear requested.");
                linuxPathTraversal.clearOperations();
                System.out.println("SUCC: CLEARED: RESET TO ROOT");
            } else {
                try {
                    linuxPathTraversal.executor.processUserCommand(input);
                } catch (CommandNotFoundException e) {
                    logger.log(Level.SEVERE, "Check the command name : " + e.getMessage());
                    System.out.println("ERR: CANNOT RECOGNIZE INPUT.");
                }
            }
        }

    }
}

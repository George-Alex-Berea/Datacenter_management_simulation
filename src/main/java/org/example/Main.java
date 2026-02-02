package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            if (args.length == 2) {
                FileHandler fileHandler = FileHandlerFactory.create(PathTypes.parsePathTypes(args[0]), args[1]);
                fileHandler.execute();
            } else if (args.length == 4) {
                FileHandler serverFileHandler = FileHandlerFactory.create(PathTypes.SERVERS, args[1]);
                FileHandler groupFileHandler = FileHandlerFactory.create(PathTypes.GROUPS, args[2]);
                FileHandler alertFileHandler = FileHandlerFactory.create(PathTypes.LISTENER, args[3]);
                serverFileHandler.execute();
                groupFileHandler.execute();
                alertFileHandler.execute();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
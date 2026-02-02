package org.example;

import java.io.IOException;

public class FileHandlerFactory {
    public static FileHandler create(PathTypes pathType, String fileName) throws IOException {
        return switch (pathType) {
            case GROUPS -> new GroupFileHandler(pathType, fileName);
            case SERVERS -> new ServerFileHandler(pathType, fileName);
            case LISTENER -> new AlertFileHandler(pathType, fileName);
        };
    }
}

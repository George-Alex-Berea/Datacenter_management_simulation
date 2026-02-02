package org.example;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public abstract class FileHandler {
    protected Scanner scanner;
    protected PrintWriter pw;
    protected int lineNo;

    public FileHandler(PathTypes pathType, String fileName) throws IOException {
        this.scanner = new Scanner(new FileReader(fileName + ".in"));
        this.pw = new PrintWriter(fileName + ".out");
        this.lineNo = 0;
    }


    public void execute() {
        String header = scanner.nextLine();
        String[] arguments = header.split("\\|", -1);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            lineNo++;
            String[] tokens = line.split("\\|", -1);
            Map<String, String> map = new HashMap<>();
            // This helps avoid using random numbers for each parameter
            for (int i = 0; i < arguments.length; i++)
                map.put(arguments[i], tokens[i]);

            try {
                executeCommand(map);
            } catch (RuntimeException e) {
                pw.println(e.getMessage());
            }
        }
        pw.close();
    }

    public abstract void executeCommand(Map<String, String> map);
}

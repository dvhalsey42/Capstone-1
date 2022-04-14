package com.techelevator;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {
    private String logFilePath = new String("capstone/Log.txt");

    private File logFile = new File(logFilePath);

    public void logMessage(String logMessage) {
        try (PrintWriter writer = new PrintWriter(
                new FileOutputStream(logFile, true)
        )) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            System.out.println(now);
            System.out.println(logMessage);
            writer.write(dtf.format(now) + " " + logMessage + "\n");

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void clearLog(){
        try(PrintWriter writer = new PrintWriter(logFile)){
            writer.print("");
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
}

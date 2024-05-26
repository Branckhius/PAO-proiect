package org_example.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AuditService {

    private static final String CSV_FILE_PATH = "audit.csv";

    private static AuditService instance = new AuditService();

    private AuditService() {
    }

    public static AuditService getInstance() {
        return instance;
    }

    public static void logAction(String actionName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE_PATH, true))) {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());


            writer.println(actionName + ", " + timestamp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

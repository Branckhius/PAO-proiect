package org_example.service;
import org_example.service.AuditService;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService<T> {
    private static DatabaseService instance;
    private Connection connection;
    private AuditService auditService;

    private DatabaseService() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myDB", "root", "parola123");
            auditService = AuditService.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized <T> DatabaseService<T> getInstance() {
        if (instance == null) {
            instance = new DatabaseService<>();
        }
        return instance;
    }

    public void create(String query) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
            auditService.logAction("CREATE");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<T> read(String query, ResultSetExtractor<T> extractor) {
        List<T> results = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                results.add(extractor.extractData(rs));
            }
            auditService.logAction("READ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public void update(String query) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
            auditService.logAction("UPDATE");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String query) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
            auditService.logAction("DELETE");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public interface ResultSetExtractor<T> {
        T extractData(ResultSet rs) throws SQLException;
    }
}

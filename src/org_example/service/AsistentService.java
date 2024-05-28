package org_example.service;

import org_example.domain.Asistent;
import org_example.database.DatabaseConfiguration;
import org_example.validator.AsistentValidator;

import java.sql.*;
import java.util.Set;
import java.util.TreeSet;
import java.util.Comparator;

public class AsistentService {

    private Set<Asistent> asistenti;
    private AsistentValidator validator;

    public AsistentService() {
        this.asistenti = new TreeSet<>(Comparator.comparing(Asistent::getNume));
        this.validator = new AsistentValidator();
    }

    public void deleteAllAsistenti() {
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            String query = "DELETE FROM asistent";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.executeUpdate();
                AuditService.logAction("Ștergere toți asistenții");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addAsistent(Asistent asistent) {
        if (validator.validate(asistent)) {
            asistenti.add(asistent);
            try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
                String query = "INSERT INTO asistent (id, nume, sectie, schimb) VALUES (?, ?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setInt(1, asistent.getId());
                    statement.setString(2, asistent.getNume());
                    statement.setString(3, asistent.getSectie());
                    statement.setString(4, asistent.getSchimb());
                    statement.executeUpdate();
                    AuditService.logAction("Adăugare asistent");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed to add asistent: " + asistent);
        }
    }

    public Set<Asistent> getAllAsistenti() {
        Set<Asistent> asistenti = new TreeSet<>(Comparator.comparing(Asistent::getNume));
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM asistent")) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nume = resultSet.getString("nume");
                String sectie = resultSet.getString("sectie");
                String schimb = resultSet.getString("schimb");
                Asistent asistent = new Asistent(id, nume, sectie, schimb);
                asistenti.add(asistent);
            }
            AuditService.logAction("Afișare asistenți");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return asistenti;
    }

    public void updateAsistent(Asistent asistent) {
        if (validator.validate(asistent)) {
            try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
                String query = "UPDATE asistent SET nume = ?, sectie = ?, schimb = ? WHERE id = ?";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, asistent.getNume());
                    statement.setString(2, asistent.getSectie());
                    statement.setString(3, asistent.getSchimb());
                    statement.setInt(4, asistent.getId());
                    statement.executeUpdate();
                    AuditService.logAction("Actualizare asistent");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed to update asistent: " + asistent);
        }
    }

    public void deleteAsistent(int id) {
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            String query = "DELETE FROM asistent WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
                AuditService.logAction("Ștergere asistent");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

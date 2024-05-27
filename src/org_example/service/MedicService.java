package org_example.service;

import org_example.domain.Medic;
import org_example.database.DatabaseConfiguration;

import java.sql.*;
import java.util.Set;
import java.util.TreeSet;
import java.util.Comparator;

public class MedicService {

    private Set<Medic> medici;

    public MedicService() {
        this.medici = new TreeSet<>(Comparator.comparing(Medic::getNume));
    }
    public void deleteAllMedici() {
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            String query = "DELETE FROM medic";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.executeUpdate();
                AuditService.logAction("Ștergere toți medicii");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addMed(Medic med) {
        medici.add(med);
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            String query = "INSERT INTO medic (id, nume, specialitate, orar) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, med.getId());
                statement.setString(2, med.getNume());
                statement.setString(3, med.getSpecialitate());
                statement.setString(4, med.getOrar());
                statement.executeUpdate();
                AuditService.logAction("Adăugare medic");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Set<Medic> getAllMedici() {
        Set<Medic> medici = new TreeSet<>(Comparator.comparing(Medic::getNume));
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM medic")) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nume = resultSet.getString("nume");
                String specialitate = resultSet.getString("specialitate");
                String orar = resultSet.getString("orar");
                Medic medic = new Medic(id, nume, specialitate, orar);
                medici.add(medic);
            }
            AuditService.logAction("Afișare medici");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medici;
    }

    public void updateMedic(Medic med) {
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            String query = "UPDATE medic SET nume = ?, specialitate = ?, orar = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, med.getNume());
                statement.setString(2, med.getSpecialitate());
                statement.setString(3, med.getOrar());
                statement.setInt(4, med.getId());
                statement.executeUpdate();
                AuditService.logAction("Actualizare medic");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMedic(int id) {
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            String query = "DELETE FROM medic WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
                AuditService.logAction("Ștergere medic");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

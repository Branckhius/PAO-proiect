package org_example.service;

import org_example.domain.Programare;
import org_example.database.DatabaseConfiguration;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProgramareService {

    private List<Programare> programari;
    private int maximNoOfProgs;

    public ProgramareService(int maximNoOfProgs) {
        this.programari = new ArrayList<>();
        this.maximNoOfProgs = maximNoOfProgs;
    }
    public void deleteAllProgramari() {
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            String query = "DELETE FROM programare";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.executeUpdate();
                AuditService.logAction("Ștergere toate programările");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addProg(Programare prog) {
        if (programari.size() < maximNoOfProgs) {
            programari.add(prog);
            try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
                String query = "INSERT INTO programare (id, id_client, id_medic, data, motiv) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setInt(1, prog.getId());
                    statement.setInt(2, prog.getIdClient());
                    statement.setInt(3, prog.getIdMedic());
                    statement.setTimestamp(4, new Timestamp(prog.getData().getTime()));
                    statement.setString(5, prog.getMotiv());
                    statement.executeUpdate();
                    AuditService.logAction("Adăugare programare");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            throw new RuntimeException("Too many progs were added");
        }
    }

    public List<Programare> getAllProgramari() {
        List<Programare> programari = new ArrayList<>();
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM programare")) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idClient = resultSet.getInt("id_client");
                int idMedic = resultSet.getInt("id_medic");
                Date data = resultSet.getTimestamp("data");
                String motiv = resultSet.getString("motiv");
                Programare programare = new Programare(id, idClient, idMedic, data, motiv);
                programari.add(programare);
            }
            AuditService.logAction("Afișare programări");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return programari;
    }

    public void updateProgramare(Programare prog) {
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            String query = "UPDATE programare SET id_client = ?, id_medic = ?, data = ?, motiv = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, prog.getIdClient());
                statement.setInt(2, prog.getIdMedic());
                statement.setTimestamp(3, new Timestamp(prog.getData().getTime()));
                statement.setString(4, prog.getMotiv());
                statement.setInt(5, prog.getId());
                statement.executeUpdate();
                AuditService.logAction("Actualizare programare");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProgramare(int id) {
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            String query = "DELETE FROM programare WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
                AuditService.logAction("Ștergere programare");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

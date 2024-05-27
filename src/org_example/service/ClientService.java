package org_example.service;

import org_example.domain.Client;
import org_example.database.DatabaseConfiguration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {

    public List<Client> clienti;

    public ClientService() {
        this.clienti = new ArrayList<>();
    }

    public void addCl(Client cl) {
        clienti.add(cl);
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            String query = "INSERT INTO client (id, nume, adresa, email, telefon) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, cl.getId());
                statement.setString(2, cl.getNume());
                statement.setString(3, cl.getAdresa());
                statement.setString(4, cl.getEmail());
                statement.setString(5, cl.getTelefon());
                statement.executeUpdate();
                AuditService.logAction("Adăugare client");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Client> getAllClients() {
        List<Client> clienti = new ArrayList<>();
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM client")) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nume = resultSet.getString("nume");
                String adresa = resultSet.getString("adresa");
                String email = resultSet.getString("email");
                String telefon = resultSet.getString("telefon");
                Client client = new Client(id, nume, adresa, email, telefon);
                clienti.add(client);
            }
            AuditService.logAction("Afișare clienți");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clienti;
    }
    public void deleteAllClients() {
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            String query = "DELETE FROM client";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.executeUpdate();
                AuditService.logAction("Ștergere toți clienții");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateClient(Client cl) {
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            String query = "UPDATE client SET nume = ?, adresa = ?, email = ?, telefon = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, cl.getNume());
                statement.setString(2, cl.getAdresa());
                statement.setString(3, cl.getEmail());
                statement.setString(4, cl.getTelefon());
                statement.setInt(5, cl.getId());
                statement.executeUpdate();
                AuditService.logAction("Actualizare client");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteClient(int id) {
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            String query = "DELETE FROM client WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
                AuditService.logAction("Ștergere client");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

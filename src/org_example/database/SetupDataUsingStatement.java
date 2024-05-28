package org_example.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SetupDataUsingStatement {

    public void createTables() {
        String createClientTableSql = """
            CREATE TABLE IF NOT EXISTS client (
                id int PRIMARY KEY AUTO_INCREMENT,
                nume varchar(100),
                adresa varchar(255),
                email varchar(100),
                telefon varchar(20)
            )
            """;

        String createMedicTableSql = """
            CREATE TABLE IF NOT EXISTS medic (
                id int PRIMARY KEY AUTO_INCREMENT,
                nume varchar(100),
                specialitate varchar(100),
                orar varchar(100)
            )
            """;

        String createAsistentTableSql = """
            CREATE TABLE IF NOT EXISTS asistent (
                id int PRIMARY KEY AUTO_INCREMENT,
                nume varchar(100),
                sectie varchar(100),
                schimb varchar(100)
            )
            """;

        String createProgramareTableSql = """
            CREATE TABLE IF NOT EXISTS programare (
                id int PRIMARY KEY AUTO_INCREMENT,
                id_client int,
                id_medic int,
                data datetime,
                motiv varchar(255),
                FOREIGN KEY (id_client) REFERENCES client(id),
                FOREIGN KEY (id_medic) REFERENCES medic(id)
            )
            """;

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();
            statement.execute(createClientTableSql);
            statement.execute(createMedicTableSql);
            statement.execute(createAsistentTableSql);
            statement.execute(createProgramareTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addClient(String nume, String adresa, String email, String telefon) {
        String insertClientSql = String.format("INSERT INTO client (nume, adresa, email, telefon) VALUES ('%s', '%s', '%s', '%s')", nume, adresa, email, telefon);
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();
            statement.execute(insertClientSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllClients() {
        String selectClientsSql = "SELECT * FROM client";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectClientsSql);

            while(resultSet.next()) {
                System.out.println("Id: " + resultSet.getInt("id"));
                System.out.println("Nume: " + resultSet.getString("nume"));
                System.out.println("Adresa: " + resultSet.getString("adresa"));
                System.out.println("Email: " + resultSet.getString("email"));
                System.out.println("Telefon: " + resultSet.getString("telefon"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addMedic(String nume, String specialitate, String orar) {
        String insertMedicSql = String.format("INSERT INTO medic (nume, specialitate, orar) VALUES ('%s', '%s', '%s')", nume, specialitate, orar);
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();
            statement.execute(insertMedicSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllMedici() {
        String selectMediciSql = "SELECT * FROM medic";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectMediciSql);

            while(resultSet.next()) {
                System.out.println("Id: " + resultSet.getInt("id"));
                System.out.println("Nume: " + resultSet.getString("nume"));
                System.out.println("Specialitate: " + resultSet.getString("specialitate"));
                System.out.println("Orar: " + resultSet.getString("orar"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addAsistent(String nume, String sectie, String schimb) {
        String insertAsistentSql = String.format("INSERT INTO asistent (nume, sectie, schimb) VALUES ('%s', '%s', '%s')", nume, sectie, schimb);
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();
            statement.execute(insertAsistentSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllAsistenti() {
        String selectAsistentiSql = "SELECT * FROM asistent";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectAsistentiSql);

            while(resultSet.next()) {
                System.out.println("Id: " + resultSet.getInt("id"));
                System.out.println("Nume: " + resultSet.getString("nume"));
                System.out.println("Sectie: " + resultSet.getString("sectie"));
                System.out.println("Schimb: " + resultSet.getString("schimb"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

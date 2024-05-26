package org_example;
import org_example.domain.Client;
import org_example.domain.Medic;
import org_example.domain.Programare;
import org_example.service.ClientService;
import org_example.service.DatabaseService;
import org_example.service.MedicService;
import org_example.service.ProgramareService;
import org_example.validator.ClientValidator;
import org_example.validator.MedicValidator;
import org_example.database.DatabaseConfiguration;
import org_example.database.SetupDataUsingStatement;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        SetupDataUsingStatement setupData = new SetupDataUsingStatement();
        setupData.createTables();

        DatabaseService<Client> clientService = DatabaseService.getInstance();
        clientService.create("INSERT INTO client (nume, adresa, email, telefon) VALUES ('Clientunu', 'Adresa1', 'client1@example.com', '0734587890')");
        clientService.create("INSERT INTO client (nume, adresa, email, telefon) VALUES ('Clientdoi', 'Adresa2', 'client2@example.com', '0787674321')");

        System.out.println("Clienți existenți:");
        List<Client> clienti = clientService.read("SELECT * FROM client", resultSet -> {
            int id = resultSet.getInt("id");
            String nume = resultSet.getString("nume");
            String adresa = resultSet.getString("adresa");
            String email = resultSet.getString("email");
            String telefon = resultSet.getString("telefon");
            return new Client(id, nume, adresa, email, telefon);
        });

        for (Client client : clienti) {
            System.out.println(client);
        }

        clientService.update("UPDATE client SET telefon = '0734567999' WHERE id = 1");

        System.out.println("\nClienți după actualizare:");
        List<Client> updatedClienti = clientService.read("SELECT * FROM client", resultSet -> {
            int id = resultSet.getInt("id");
            String nume = resultSet.getString("nume");
            String adresa = resultSet.getString("adresa");
            String email = resultSet.getString("email");
            String telefon = resultSet.getString("telefon");
            return new Client(id, nume, adresa, email, telefon);
        });
        for (Client client : updatedClienti) {
            System.out.println(client);
        }

        clientService.delete("DELETE FROM client WHERE id = 2");

        System.out.println("\nClienți după ștergere:");
        List<Client> remainingClienti = clientService.read("SELECT * FROM client", resultSet -> {
            int id = resultSet.getInt("id");
            String nume = resultSet.getString("nume");
            String adresa = resultSet.getString("adresa");
            String email = resultSet.getString("email");
            String telefon = resultSet.getString("telefon");
            return new Client(id, nume, adresa, email, telefon);
        });

        for (Client client : remainingClienti) {
            System.out.println(client);
        }

        DatabaseService<Medic> medicService = DatabaseService.getInstance();
        medicService.create("INSERT INTO medic (nume, specialitate, orar) VALUES ('Marin', 'Specializare1', '12:00-18:00')");
        medicService.create("INSERT INTO medic (nume, specialitate, orar) VALUES ('Ana', 'Specializare2', '8:00-16:00')");

        System.out.println("\nMedici existenți:");
        List<Medic> medici = medicService.read("SELECT * FROM medic", resultSet -> {
            int id = resultSet.getInt("id");
            String nume = resultSet.getString("nume");
            String specialitate = resultSet.getString("specialitate");
            String orar = resultSet.getString("orar");
            return new Medic(id, nume, specialitate, orar);
        });

        for (Medic medic : medici) {
            System.out.println(medic);
        }

        DatabaseService<Programare> programareService = DatabaseService.getInstance();
        programareService.create("INSERT INTO programare (id_client, id_medic, data, motiv) VALUES (1, 1, '2024-05-26 10:00:00', 'Verificare operatie glezna /30zile')");

        System.out.println("\nProgramări existente:");
        List<Programare> programari = programareService.read("SELECT * FROM programare", resultSet -> {
            int id = resultSet.getInt("id");
            int idClient = resultSet.getInt("id_client");
            int idMedic = resultSet.getInt("id_medic");
            Date data = resultSet.getDate("data");
            String motiv = resultSet.getString("motiv");
            return new Programare(id, idClient, idMedic, data, motiv);
        });

        for (Programare programare : programari) {
            System.out.println(programare);
        }
    }
}


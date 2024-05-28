package org_example;

import org_example.domain.Client;
import org_example.domain.Medic;
import org_example.domain.Asistent;
import org_example.domain.Programare;
import org_example.service.ClientService;
import org_example.service.MedicService;
import org_example.service.AsistentService;
import org_example.service.ProgramareService;
import org_example.database.SetupDataUsingStatement;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        SetupDataUsingStatement setupData = new SetupDataUsingStatement();
        setupData.createTables();

        ClientService clientService = new ClientService();
        MedicService medicService = new MedicService();
        AsistentService asistentService = new AsistentService();
        ProgramareService programareService = new ProgramareService(10);

        clearTables(clientService, medicService, asistentService, programareService);

        clientService.addCl(new Client(1, "Clientunu", "Adresa1", "client1@example.com", "0734587890"));
        clientService.addCl(new Client(2, "Clientdoi", "Adresa2", "client2@example.com", "0787674321"));

        System.out.println("Clienți existenți:");
        List<Client> clienti = clientService.getAllClients();
        for (Client client : clienti) {
            System.out.println(client);
        }

        Client updatedClient = new Client(1, "Clientunu", "Adresa1", "client1@example.com", "0734567999");
        clientService.updateClient(updatedClient);

        System.out.println("\nClienți după actualizare:");
        List<Client> updatedClienti = clientService.getAllClients();
        for (Client client : updatedClienti) {
            System.out.println(client);
        }

        clientService.deleteClient(2);

        System.out.println("\nClienți după ștergere:");
        List<Client> remainingClienti = clientService.getAllClients();
        for (Client client : remainingClienti) {
            System.out.println(client);
        }

        medicService.addMed(new Medic(1, "Marin", "Specializare1", "12:00-18:00"));
        medicService.addMed(new Medic(2, "Ana", "Specializare2", "8:00-16:00"));

        System.out.println("\nMedici existenți:");
        Set<Medic> medici = medicService.getAllMedici();
        for (Medic medic : medici) {
            System.out.println(medic);
        }

        Medic updatedMedic = new Medic(1, "Marin", "Specializare1", "14:00-20:00");
        medicService.updateMedic(updatedMedic);

        System.out.println("\nMedici după actualizare:");
        Set<Medic> updatedMedici = medicService.getAllMedici();
        for (Medic medic : updatedMedici) {
            System.out.println(medic);
        }

        medicService.deleteMedic(2);

        System.out.println("\nMedici după ștergere:");
        Set<Medic> remainingMedici = medicService.getAllMedici();
        for (Medic medic : remainingMedici) {
            System.out.println(medic);
        }

        asistentService.addAsistent(new Asistent(1, "Popescu", "Chirurgie", "Schimbul I"));
        asistentService.addAsistent(new Asistent(2, "Ionescu", "Pediatrie", "Schimbul II"));

        System.out.println("\nAsistenți existenți:");
        Set<Asistent> asistenti = asistentService.getAllAsistenti();
        for (Asistent asistent : asistenti) {
            System.out.println(asistent);
        }

        Asistent updatedAsistent = new Asistent(1, "Popescu", "Chirurgie", "Schimbul II");
        asistentService.updateAsistent(updatedAsistent);

        System.out.println("\nAsistenți după actualizare:");
        Set<Asistent> updatedAsistenti = asistentService.getAllAsistenti();
        for (Asistent asistent : updatedAsistenti) {
            System.out.println(asistent);
        }

        asistentService.deleteAsistent(2);

        System.out.println("\nAsistenți după ștergere:");
        Set<Asistent> remainingAsistenti = asistentService.getAllAsistenti();
        for (Asistent asistent : remainingAsistenti) {
            System.out.println(asistent);
        }

        programareService.addProg(new Programare(1, 1, 1, new Date(), "Verificare operatie glezna /30zile"));

        System.out.println("\nProgramări existente:");
        List<Programare> programari = programareService.getAllProgramari();
        for (Programare programare : programari) {
            System.out.println(programare);
        }

        Programare updatedProgramare = new Programare(1, 1, 1, new Date(), "Control post-operator");
        programareService.updateProgramare(updatedProgramare);

        System.out.println("\nProgramări după actualizare:");
        List<Programare> updatedProgramari = programareService.getAllProgramari();
        for (Programare programare : updatedProgramari) {
            System.out.println(programare);
        }

        programareService.deleteProgramare(1);

        System.out.println("\nProgramări după ștergere:");
        List<Programare> remainingProgramari = programareService.getAllProgramari();
        for (Programare programare : remainingProgramari) {
            System.out.println(programare);
        }
    }

    private static void clearTables(ClientService clientService, MedicService medicService, AsistentService asistentService, ProgramareService programareService) {
        clientService.deleteAllClients();
        medicService.deleteAllMedici();
        asistentService.deleteAllAsistenti();
        programareService.deleteAllProgramari();
    }
}

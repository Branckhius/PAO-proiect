package org_example;

import org_example.domain.Asistent;
import org_example.domain.Client;
import org_example.domain.Medic;
import org_example.domain.Programare;
import org_example.service.AsistentService;
import org_example.service.ClientService;
import org_example.service.MedicService;
import org_example.service.ProgramareService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        ClientService clientService = new ClientService();
        MedicService medicService = new MedicService();
        AsistentService asistentService = new AsistentService();
        ProgramareService programareService = new ProgramareService(10); // Specify the maximum number of appointments

        int option;
        do {
            System.out.println("Meniu:");
            System.out.println("1. Adaugare client");
            System.out.println("2. Afisare clienti");
            System.out.println("3. Stergere toti clientii");
            System.out.println("4. Adaugare medic");
            System.out.println("5. Afisare medici");
            System.out.println("6. Stergere toti medicii");
            System.out.println("7. Adaugare asistent");
            System.out.println("8. Afisare asistenti");
            System.out.println("9. Stergere toti asistentii");
            System.out.println("10. Adaugare programare");
            System.out.println("11. Afisare programari");
            System.out.println("12. Stergere toate programarile");
            System.out.println("13. Clear tables");
            System.out.println("0. Iesire");

            System.out.print("Alege optiunea: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    // Adaugare client
                    System.out.print("Nume: ");
                    String numeClient = scanner.nextLine();
                    System.out.print("Adresa: ");
                    String adresaClient = scanner.nextLine();
                    System.out.print("Email: ");
                    String emailClient = scanner.nextLine();
                    System.out.print("Telefon: ");
                    String telefonClient = scanner.nextLine();
                    clientService.addCl(new Client(0, numeClient, adresaClient, emailClient, telefonClient));
                    break;
                case 2:
                    // Afisare clienti
                    List<Client> clients = clientService.getAllClients();
                    for (Client client : clients) {
                        System.out.println(client);
                    }
                    break;
                case 3:
                    // Stergere toti clientii
                    clientService.deleteAllClients();
                    break;
                case 4:
                    // Adaugare medic
                    System.out.print("Nume: ");
                    String numeMedic = scanner.nextLine();
                    System.out.print("Specialitate: ");
                    String specialitateMedic = scanner.nextLine();
                    System.out.print("Orar: ");
                    String orarMedic = scanner.nextLine();
                    medicService.addMed(new Medic(0, numeMedic, specialitateMedic, orarMedic));
                    break;
                case 5:
                    // Afisare medici
                    Set<Medic> medici = medicService.getAllMedici();
                    for (Medic medic : medici) {
                        System.out.println(medic);
                    }
                    break;
                case 6:
                    // Stergere toti medicii
                    medicService.deleteAllMedici();
                    break;
                case 7:
                    // Adaugare asistent
                    System.out.print("Nume: ");
                    String numeAsistent = scanner.nextLine();
                    System.out.print("Sectie: ");
                    String sectieAsistent = scanner.nextLine();
                    System.out.print("Schimb: ");
                    String schimbAsistent = scanner.nextLine();
                    asistentService.addAsistent(new Asistent(0, numeAsistent, sectieAsistent, schimbAsistent));
                    break;
                case 8:
                    // Afisare asistenti
                    Set<Asistent> asistenti = asistentService.getAllAsistenti();
                    for (Asistent asistent : asistenti) {
                        System.out.println(asistent);
                    }
                    break;
                case 9:
                    // Stergere toti asistentii
                    asistentService.deleteAllAsistenti();
                    break;
                case 10:
                    // Adaugare programare
                    System.out.print("Id Client: ");
                    int idClientProg = scanner.nextInt();
                    System.out.print("Id Medic: ");
                    int idMedicProg = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Data (yyyy-MM-dd HH:mm:ss): ");
                    String dataProgString = scanner.nextLine();
                    System.out.print("Motiv: ");
                    String motivProg = scanner.nextLine();
                    Date dataProg = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dataProgString);
                    programareService.addProg(new Programare(0, idClientProg, idMedicProg, dataProg, motivProg));
                    break;
                case 11:
                    // Afisare programari
                    List<Programare> programari = programareService.getAllProgramari();
                    for (Programare programare : programari) {
                        System.out.println(programare);
                    }
                    break;
                case 12:
                    // Stergere toate programarile
                    programareService.deleteAllProgramari();
                    break;
                case 13:
                    // Clear tables
                    clearTables(clientService, medicService, programareService);
                    break;
                case 0:
                    // Iesire
                    System.out.println("La revedere!");
                    break;
                default:
                    System.out.println("Optiune invalida! Te rog sa introduci o optiune valida.");
            }
        } while (option != 0);

        scanner.close();
    }

    private static void clearTables(ClientService clientService, MedicService medicService, ProgramareService programareService) {
        clientService.deleteAllClients();
        medicService.deleteAllMedici();
        programareService.deleteAllProgramari();
    }
}

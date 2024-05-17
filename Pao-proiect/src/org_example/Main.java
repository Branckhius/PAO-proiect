package org_example;
import org_example.domain.Client;
import org_example.domain.Medic;
import org_example.domain.Programare;
import org_example.service.ClientService;
import org_example.service.MedicService;
import org_example.service.ProgramareService;
import org_example.validator.ClientValidator;
import org_example.validator.MedicValidator;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        ClientService ClientiFideli=new ClientService();
        ClientValidator validatorclient = new ClientValidator();
        MedicValidator validatormedic=new MedicValidator();


        Client client1=new Client(1, "Clientunu", "Adresa1", "client1@example.com", "0734587890");
        Client client2=new Client(2, "Clientdoi", "Adresa2", "client2@example", "0787674321");
        Client client3=new Client(3, "Clienttrei", "Adresa3", "client3@example.com", "0734557890");
        Client client4=new Client(4, "Clientpatru", "Adresa4", "client4@example.com", "0787664321");
        Client client5=new Client(5, "Clientcinci", "Adresa5", "client5example.com", "0734547890");
        Client client6=new Client(6, "Clientsase", "Adresa6", "client6@example.com", "0787634321");
        Client client7=new Client(7, "Clientsapte", "Adresa7", "client7@example.com", "0734527890");
        Client client8=new Client(8, "Clientopt", "Adresa8", "client8@example.com", "787614321");
        Client[] clientList = new Client[] {client1, client2, client3, client4, client5, client6, client7, client8};

        for(Client client: clientList){
            if(validatorclient.validate(client)){
                ClientiFideli.addCl(client);
            }
        }

        System.out.println("\nLista Clienti:");
        ClientiFideli.afisCl();
        System.out.println("--------------------------");
        System.out.println("\n");
        MedicService Medici=new MedicService();
        Medic medic1=new Medic(1, "Marin", "Specializare1", "12:00-18:00");
        Medic medic2=new Medic(2, "Ana", "Specializare2", "8:00-16:00");
        Medic medic3=new Medic(3, "Abelix", "Specializare3", "6:00-8:00");
        Medici.addMed(new Medic(1, "Marin", "Specializare1", "12:00-18:00"));
        Medici.addMed(new Medic(2, "Ana", "Specializare2", "8:00-16:00"));
        Medici.addMed(new Medic(3, "Anelis", "Specializare3", "6:00-8:00"));
        Medic[] medicList=new Medic[] {medic1,medic2,medic3};
        for(Medic medic: medicList){
            if(validatormedic.validate(medic)){
                Medici.addMed(medic);
            }
        }
        System.out.println("\nLista Medici:");
        Medici.afisMed();
        System.out.println("--------------------------");
        System.out.println("\n");

        ProgramareService ProgramariIanuarie = new ProgramareService(30);
        ProgramariIanuarie.addProg(new Programare(1, 1, 1, new Date(), "Verificare operatie glezna /30zile"));

        System.out.println("\nLista ProgramariIanuarie:");
        ProgramariIanuarie.afisProg();



    }
}
package org.example.service;

import java.util.*;
import org.example.domain.Client;

public class ClientService {

    public List<Client> clienti;

    public ClientService() {
        this.clienti = new ArrayList<>();
    }

    public void addCl(Client cl) {
        clienti.add(cl);
    }

    public void afisCl() {
        for (Client cl : clienti) {
            System.out.println(cl);
        }
    }
}

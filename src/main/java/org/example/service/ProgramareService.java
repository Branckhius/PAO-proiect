package org.example.service;

import java.util.ArrayList;
import java.util.List;
import org.example.domain.Programare;

public class ProgramareService {

    public List<Programare> programari;
    private int maximNoOfProgs;

    public ProgramareService(int maximNoOfProgs) {
        this.programari = new ArrayList<>();
        this.maximNoOfProgs = maximNoOfProgs;
    }

    public void addProg(Programare prog) {
        if (programari.size() < maximNoOfProgs) {
            programari.add(prog);
        } else {
            throw new RuntimeException("Too many progs were added");
        }
    }

    public void afisProg() {
        for (Programare prog : programari) {
            if(prog!=null)
                System.out.println(prog);
        }
    }
}

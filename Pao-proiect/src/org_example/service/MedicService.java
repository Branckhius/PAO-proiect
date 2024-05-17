package org_example.service;

import java.util.*;
import org_example.domain.Medic;

public class MedicService {

    public Set<Medic> medici;

    public MedicService() {
        this.medici = new TreeSet<>(Comparator.comparing(Medic::getNume));
    }

    public void addMed(Medic med) {
        medici.add(med);
    }

    public void afisMed() {
        for (Medic med : medici) {
            System.out.println(med);
        }
    }
}
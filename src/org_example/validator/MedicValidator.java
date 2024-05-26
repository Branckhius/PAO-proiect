package org_example.validator;

import org_example.domain.Medic;

public class MedicValidator {
    public boolean validate (Medic medic) {
        if (validateNameRegex(medic.getNume())) {
            System.out.println("Valid person");
            return true;
        } else {
            System.out.println("Invalid person");
            return false;
        }
    }
    private boolean validateNameRegex(String name) {
        String regex = "[A-Z][a-z]+";
        return name.matches(regex);
    }
}
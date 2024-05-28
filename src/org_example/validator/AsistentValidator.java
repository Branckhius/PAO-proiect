package org_example.validator;

import org_example.domain.Asistent;

public class AsistentValidator {

    public boolean validate(Asistent asistent) {
        return validateNume(asistent.getNume()) && validateSectie(asistent.getSectie()) && validateSchimb(asistent.getSchimb());
    }

    private boolean validateNume(String nume) {
        return nume != null && !nume.trim().isEmpty();
    }

    private boolean validateSectie(String sectie) {
        return sectie != null && !sectie.trim().isEmpty();
    }

    private boolean validateSchimb(String schimb) {
        return schimb != null && !schimb.trim().isEmpty();
    }
}

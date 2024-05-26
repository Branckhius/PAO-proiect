package org_example.validator;
import org_example.domain.Client;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientValidator {
    public boolean validate(Client client) {
        if (validateMobilePhoneNumber(client.getTelefon()) && validateEmail(client.getEmail()) && validateNameRegex(client.getNume())) {
            System.out.println("Valid person: " + client.getNume());
            return true;
        } else {
            System.out.println("Invalid person: " + client.getNume());
            return false;
        }
    }

    private boolean validateMobilePhoneNumber(String phoneNumber) {
        return phoneNumber.matches("(07)[0-9]{8}");
    }
    private boolean validateNameRegex(String name) {
        String regex = "[A-Z][a-z]+";
        return name.matches(regex);
    }
    public static boolean validateEmail(String email) {
        String patternString = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        Pattern pattern = Pattern.compile(patternString);

        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
}
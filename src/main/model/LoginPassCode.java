package model;

import exceptions.InvalidPassCodeException;
import persistence.Saveable;

import java.io.PrintWriter;

// Represents the pass code required prior accessing the password account list
public class LoginPassCode implements Saveable {

    public String passCode = "0000";

    // EFFECTS: changes pass code
    // MODIFIES: this
    public void changePassCode(String newPassCode) throws InvalidPassCodeException {
        if (!validPassCode(newPassCode)) {
            throw new InvalidPassCodeException("Input is not a valid pass code.");
        }
        passCode = newPassCode;
    }

    // EFFECTS: return true if pass code is valid - 4 digit integer
    public boolean validPassCode(String newPassCode) {
        return newPassCode.matches("[0-9]+") && newPassCode.length() == 4;
    }

    // EFFECTS: return current pass code
    public String getPassCode() {
        return passCode;
    }

    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(passCode);
    }
}
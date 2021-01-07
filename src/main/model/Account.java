package model;

import exceptions.InputTooLongException;
import persistence.Reader;
import persistence.Saveable;

import java.io.PrintWriter;

// Represents an account having an account name, user name, email, and password for an account
public class Account implements Saveable {
    public String accountName = "";
    public String userName = "n/a";
    public String email = "n/a";
    public String password = "n/a";

    // EFFECTS: get account name
    public String getAccountName() {
        return this.accountName;
    }

    // EFFECTS: get userName
    public String getUserName() {
        return this.userName;
    }

    // EFFECTS: get email
    public  String getEmail() {
        return this.email;
    }

    // EFFECTS: get password
    public String getPassword() {
        return this.password;
    }

    // EFFECTS: Set account name
    // MODIFIES: this
    public void addAccountName(String accountName) throws InputTooLongException {
        if (accountName.length() > 30) {
            throw new InputTooLongException("Account name is too long.");
        }
        this.accountName = accountName;
    }

    // EFFECTS: set account's userName
    // MODIFIES: this
    public void addUserName(String userName) {
        this.userName = userName;
    }

    // EFFECTS: set account's user email
    // MODIFIES: this
    public void addEmail(String email) {
        this.email = email;
    }

    // EFFECTS: set account's password
    // MODIFIES: this
    public void addPassword(String password) {
        this.password = password;
    }

    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(accountName);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(userName);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(email);
        printWriter.print(Reader.DELIMITER);
        printWriter.println(password);
    }

}

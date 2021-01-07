package persistence;

import exceptions.InputTooLongException;
import model.Account;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// A reader that can read account data or pass code from a file
public class Reader {
    public static final String DELIMITER = ",";

    // EFFECTS: returns a list of accounts parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static List<Account> readAccounts(File file) throws IOException, InputTooLongException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of accounts parsed from list of strings
    // where each string contains data for one account
    private static List<Account> parseContent(List<String> fileContent) throws InputTooLongException {
        List<Account> accounts = new ArrayList<>();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            accounts.add(parseAccount(lineComponents));
        }

        return accounts;
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REQUIRES: components has size 4 where element 0 represents the
    // account name of the next account to be constructed, element 1 represents
    // the username, elements 2 represents the email and element 3 represents
    // the password of the account to be constructed
    // EFFECTS: returns an account constructed from components
    private static Account parseAccount(List<String> components) throws InputTooLongException {
        Account account = new Account();
        account.addAccountName(components.get(0));
        account.addUserName(components.get(1));
        account.addEmail(components.get(2));
        account.addPassword(components.get(3));
        return account;
    }

    // EFFECTS: returns a list of accounts parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static String readPassCode(File file) throws IOException {
        return Files.readAllLines(file.toPath()).get(0);
    }
}

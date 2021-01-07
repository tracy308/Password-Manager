package ui;

import exceptions.InputTooLongException;
import exceptions.InvalidPassCodeException;
import model.Account;
import model.AccountList;
import model.LoginPassCode;
import persistence.Reader;
import persistence.Writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Scanner;

// Password Manager application
public class PasswordManagerAppUI {

    private LoginPassCode loginPassCode = new LoginPassCode();
    private Scanner input;
    private AccountList accountList = new AccountList();
    private static final String ACCOUNTS_FILE = "./data/accounts.txt";
    private static final String PASSCODE = "./data/passcode.txt";

    // EFFECTS: runs the Password Manager application
    public PasswordManagerAppUI() throws InputTooLongException, InvalidPassCodeException {
        runPasswordManager();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runPasswordManager() throws InputTooLongException, InvalidPassCodeException {
        boolean keepGoing = true;
        input = new Scanner(System.in);
        String command;

        setPassCode();

        while (keepGoing) {
            displayLogin();
            command = input.next();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processPassCode(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // EFFECTS: displays menu of options to user
    private void displayLogin() {
        System.out.println("\nEnter pass code:");
        System.out.println("\tFirst time users -> 0000");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: process user command
    private void processPassCode(String command) throws InputTooLongException, InvalidPassCodeException {
        if (command.equals(loginPassCode.getPassCode())) {
            loadAccounts();
            runLoggedIn();
        } else {
            System.out.println("Incorrect pass code. Please try again.");
        }
    }

    // MODIFIES: this
    // EFFECTS: set pass code from PASSCODE, if that file exists;
    // otherwise initializes passcode with default values
    private void setPassCode() {
        try {
            loginPassCode.changePassCode(Reader.readPassCode(new File(PASSCODE)));
        } catch (IOException | InvalidPassCodeException e) {
            loginPassCode = new LoginPassCode();
        }
    }

    // MODIFIES: this
    // EFFECTS: loads accounts from ACCOUNTS_FILE, if that file exists;
    // otherwise initializes accounts with default values
    private void loadAccounts() {
        try {
            List<Account> accounts = Reader.readAccounts(new File(ACCOUNTS_FILE));
            for (Account a : accounts) {
                accountList.addAccount(a);
            }
        } catch (IOException | InputTooLongException e) {
            init();
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes empty account list
    private void init() {
        accountList = new AccountList();
    }

    // EFFECTS: saves accounts to ACCOUNTS_FILE
    private void saveAccounts() {
        try {
            Writer writer = new Writer(new File(ACCOUNTS_FILE));
            for (int num = 1; num <= accountList.length(); num++) {
                writer.write(accountList.getAccountFromStrPos(String.valueOf(num)));
            }
            writer.close();
            System.out.println("Accounts saved to file " + ACCOUNTS_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save accounts to " + ACCOUNTS_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }

    // EFFECTS: save pass code to PASSCODE_FILE
    private void savePassCode() {
        try {
            Writer writer = new Writer(new File(PASSCODE));
            writer.write(loginPassCode);
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save pass code to " + PASSCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: run logged-in account page
    private void runLoggedIn() throws InputTooLongException, InvalidPassCodeException {
        boolean madeDecision = false;
        String command;
        input = new Scanner(System.in);

        while (!madeDecision) {
            doLogin();
            command = input.next();
            if (command.equals("b")) {
                madeDecision = true;
            } else {
                processCommand(command);
            }
        }
    }

    // EFFECTS: login to account page
    private void doLogin() {
        printAccountList();
        System.out.println("\tv -> choose account to view details");
        System.out.println("\ta -> add account");
        System.out.println("\td -> delete account");
        System.out.println("\tr -> change pass code");
        System.out.println("\tb -> return to log in");
    }

    // EFFECTS: prints out accounts.txt to the screen
    private void printAccountList() {
        printAccountsList(accountList);
        System.out.println("\nSelect from:");
    }

    // EFFECTS: prints all the account names in the order added
    private void printAccountsList(AccountList accountList) {
        List<String> nameList = accountList.accountNameWithPositionList();
        for (String s : nameList) {
            System.out.println(s);
        }
    }

    // EFFECTS: process user command
    private void processCommand(String command) throws InputTooLongException, InvalidPassCodeException {
        switch (command) {
            case "r":
                doResetPassCode();
                break;
            case "a":
                doAddAccount();
                break;
            case "d":
                doDeleteAccount();
                break;
            case "v":
                if (accountList.isEmpty()) {
                    System.out.println("Account list is empty.");
                } else {
                    doViewAccountDetails();
                }
                break;
            default:
                System.out.println("no such command...");
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: view account details of given account position in list
    private void doViewAccountDetails() {
        boolean choose = false;
        boolean doneViewing = false;
        String str;

        while (!choose) {
            printAccountsList(accountList);
            System.out.println("Please enter the account's position number in list to choose account:");
            str = input.next();

            if (numInList(str)) {
                printAccountDetails(accountList.getAccountFromStrPos(str));
                System.out.println("e -> exit back to account page");
                while (!doneViewing) {
                    str = input.next();
                    if (str.equals("e")) {
                        doneViewing = true;
                    }
                }
                choose = true;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: choose an account from account list
    private void printAccountDetails(Account account) {
        System.out.println("Account Name: " + account.getAccountName());
        if (!account.userName.equals("n/a")) {
            System.out.println("Username: " + account.getUserName());
        }
        if (!account.email.equals("n/a")) {
            System.out.println("E-mail: " + account.getEmail());
        }
        System.out.println("Password: " + account.getPassword());
    }

    // EFFECTS: add account to account list
    // MODIFIES: this
    private void doAddAccount() throws InputTooLongException {
        boolean allEntered = false;
        String entered;
        Account account = new Account();

        while (!allEntered) {
            System.out.println("Please enter account name:");
            entered = input.next();
            account.addAccountName(entered);
            System.out.println("Enter username:");
            System.out.println("If none used in creating account, enter n/a");
            entered = input.next();
            account.addUserName(entered);
            System.out.println("Enter e-mail:");
            System.out.println("If none used in creating account, enter n/a");
            entered = input.next();
            account.addEmail(entered);
            System.out.println("Enter password:");
            entered = input.next();
            account.addPassword(entered);

            accountList.addAccount(account);
            saveAccounts();
            allEntered = true;
        }
    }

    // MODIFIES: this
    // EFFECTS: delete account from list
    private void doDeleteAccount() {
        String str;
        boolean deleted = false;
        printAccountsList(accountList);

        while (!deleted) {
            System.out.println("e -> exit back to account page");
            System.out.println("Please enter corresponding number to delete account:");
            str = input.next();

            if (str.equals("e")) {
                deleted = true;
            } else if (numInList(str)) {
                accountList.deleteAccount(accountList.getAccountFromStrPos(str));
                saveAccounts();
                deleted = true;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    // EFFECTS: reset pass code
    // MODIFIES: this
    private void doResetPassCode() throws InvalidPassCodeException {
        boolean changedPassCode = false;
        String newPassCode;

        while (!changedPassCode) {
            System.out.println("Please enter new 4-digit pass code (integers only):");
            newPassCode = input.next();
            if (loginPassCode.validPassCode(newPassCode)) {
                System.out.println("You have entered a 4-digit pin");
                System.out.println("Pass Code changed successfully.");
                loginPassCode.changePassCode(newPassCode);
                savePassCode();
                changedPassCode = true;
            } else {
                System.out.println("You have not entered a 4-digit pin");
                System.out.println("Please try use only integers.");
            }
        }
    }

    // EFFECTS: return true if input is digits only
    private boolean digitOnly(String input) {
        return (input.matches("[0-9]+"));
    }

    // EFFECTS: return true if input is inbounds of account list
    private boolean numInList(String str) {
        if (digitOnly(str)) {
            int num;
            num = Integer.parseInt(str);
            return num > 0 && num <= accountList.length();
        } else {
            return false;
        }
    }
}
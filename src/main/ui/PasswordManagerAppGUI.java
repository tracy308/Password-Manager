package ui;

import exceptions.InputTooLongException;
import model.Account;
import model.AccountList;
import model.LoginPassCode;
import persistence.Reader;
import ui.functions.*;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class PasswordManagerAppGUI extends JFrame {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 300;
    private static final String ACCOUNTS_FILE = "./data/accounts.txt";

    private final LoginPassCode loginPassCode = new LoginPassCode();
    private AccountList accountList = new AccountList();

    public JFrame accountFrame;

    public PasswordManagerAppGUI() {
        loadAccounts();
        openAccountFrame();
    }

    // EFFECTS: load account files if possible, else create empty account list
    // MODIFIES: this
    private void loadAccounts() {
        try {
            List<Account> accounts = Reader.readAccounts(new File(ACCOUNTS_FILE));
            accountList = new AccountList();
            for (Account a : accounts) {
                accountList.addAccount(a);
            }
        } catch (IOException | InputTooLongException e) {
            accountList = new AccountList();
        }
    }

    // EFFECTS: open new frame for logged in page
    // MODIFIES: this
    public void openAccountFrame() {
        accountFrame = new JFrame("Password Manager");
        accountFrame.setSize(WIDTH, HEIGHT);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Accounts List", new ViewAccountTab(accountList));
        tabbedPane.addTab("Add Account", new AddAccountTab(this, accountList));
        tabbedPane.addTab("Delete Account", new DeleteAccountTab(this, accountList));
        tabbedPane.addTab("Change Passcode", new ChangePasscodeTab(loginPassCode));
        tabbedPane.addTab("Save", new SaveTab(accountList, loginPassCode));

        accountFrame.add(tabbedPane);
        accountFrame.setVisible(true);
    }
}
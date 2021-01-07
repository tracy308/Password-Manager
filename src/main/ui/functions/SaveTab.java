package ui.functions;

import model.AccountList;
import model.LoginPassCode;
import persistence.Writer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class SaveTab extends Tab implements ActionListener {

    protected AccountList accountList;
    protected LoginPassCode loginPassCode;

    private static final String ACCOUNTS_FILE = "./data/accounts.txt";
    private static final String PASSCODE = "./data/passcode.txt";

    private static final String SAVE_ACCOUNTS_TO_FILE = "save accounts to file";
    private static final String SAVE_PASSCODE_TO_FILE = "save passcode to file";

    public SaveTab(AccountList accountList, LoginPassCode loginPassCode) {
        JPanel panel = this;
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);

        this.accountList = accountList;
        this.loginPassCode = loginPassCode;

        JButton saveAccounts = new JButton("Save Accounts to File");
        saveAccounts.addActionListener(this);
        saveAccounts.setActionCommand(SAVE_ACCOUNTS_TO_FILE);
        panel.add(saveAccounts);

        JButton savePassCode = new JButton("Save Passcode to File");
        savePassCode.addActionListener(this);
        savePassCode.setActionCommand(SAVE_PASSCODE_TO_FILE);
        panel.add(savePassCode);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (SAVE_ACCOUNTS_TO_FILE.equals(cmd)) {
            doSaveAccounts();
        } else if (SAVE_PASSCODE_TO_FILE.equals(cmd)) {
            doSavePassCode();
        }
    }

    // EFFECTS: save accounts to ACCOUNTS_FILE
    // MODIFIES: this
    private void doSaveAccounts() {
        try {
            Writer writer = new Writer(new File(ACCOUNTS_FILE));
            for (int num = 1; num <= accountList.length(); num++) {
                writer.write(accountList.getAccountFromStrPos(String.valueOf(num)));
            }
            writer.close();
            JOptionPane.showMessageDialog(null, "Accounts saved to file " + ACCOUNTS_FILE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Unable to save accounts to " + ACCOUNTS_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }

    // EFFECTS: save passcode to PASSCODE_FILE
    // MODIFIES: this
    private void doSavePassCode() {
        try {
            Writer writer = new Writer(new File(PASSCODE));
            writer.write(loginPassCode);
            writer.close();
            JOptionPane.showMessageDialog(null, "Passcode saved to file " + PASSCODE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Unable to save passcode to " + PASSCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}

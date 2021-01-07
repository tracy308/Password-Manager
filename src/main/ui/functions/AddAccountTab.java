package ui.functions;

import exceptions.InputTooLongException;
import model.Account;
import model.AccountList;
import ui.PasswordManagerAppGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAccountTab extends Tab implements ActionListener {

    private static final String ADD = "add";

    private JTextField addAccountName;
    private JTextField addUserName;
    private JTextField addEmail;
    private JPasswordField addPassword;

    private final AccountList accountList;
    private final PasswordManagerAppGUI manager;

    public AddAccountTab(PasswordManagerAppGUI manager, AccountList accountList) {
        JPanel panel = this;
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.accountList = accountList;
        this.manager = manager;

        JPanel components = makeAddAccountComponents();
        panel.add(components);

        JButton add = new JButton("Add");
        add.addActionListener(this);
        add.setActionCommand(ADD);
        panel.add(add);
    }

    // EFFECTS: return the panel with the account components
    private JPanel makeAddAccountComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel l1 = new JLabel("If none used in creating account, enter n/a \n");
        panel.add(l1);

        JPanel accountPanel = makeAddAccountFieldPanel("Account Name: ");
        addAccountName = new JTextField(20);
        accountPanel.add(addAccountName);
        panel.add(accountPanel);

        JPanel userNamePanel = makeAddAccountFieldPanel("Username: ");
        addUserName = new JTextField(20);
        userNamePanel.add(addUserName);
        panel.add(userNamePanel);

        JPanel emailPanel = makeAddAccountFieldPanel("Email: ");
        addEmail = new JTextField(20);
        emailPanel.add(addEmail);
        panel.add(emailPanel);

        JPanel passwordPanel = makeAddAccountFieldPanel("Password: ");
        addPassword = new JPasswordField(20);
        passwordPanel.add(addPassword);
        panel.add(passwordPanel);

        return panel;
    }

    // EFFECTS: return panel with flow layout and string for text field
    private JPanel makeAddAccountFieldPanel(String fieldName) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel label = new JLabel(fieldName);
        panel.add(label);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (ADD.equals(cmd)) {
            doAddAccount();
        }
    }

    // EFFECTS: add account to account list
    // MODIFIES: this
    private void doAddAccount() {
        Account a = new Account();
        try {
            a.addAccountName(addAccountName.getText());
            a.addUserName(addUserName.getText());
            a.addEmail(addEmail.getText());
            a.addPassword(String.valueOf(addPassword.getPassword()));
            accountList.addAccount(a);
        } catch (InputTooLongException e) {
            JOptionPane.showMessageDialog(null,
                    "Account Name Input too long. Fail to create account.");
        }
        manager.accountFrame.dispose();
        manager.openAccountFrame();
    }
}

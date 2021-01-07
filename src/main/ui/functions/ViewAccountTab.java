package ui.functions;

import model.Account;
import model.AccountList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewAccountTab extends Tab implements ActionListener {

    private static final String VIEW_ACCOUNT = "view account";

    private final JTextField accountToCheck;
    private final AccountList accountList;

    public ViewAccountTab(AccountList accountList) {
        JPanel panel = this;
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);
        this.accountList = accountList;

        JTextArea l1 = new JTextArea();
        l1.setEditable(false);
        l1.setText(accountList.accountNameWithPositionList().stream().reduce("", (acc, val) -> acc + '\n' + val));
        panel.add(l1);

        JLabel l2 = new JLabel("Enter account's position number in list");
        panel.add(l2);
        accountToCheck = new JTextField();
        panel.add(accountToCheck);

        JButton view = new JButton("View Details");
        view.setAlignmentX(Component.CENTER_ALIGNMENT);
        view.addActionListener(this);
        view.setActionCommand(VIEW_ACCOUNT);
        panel.add(view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (VIEW_ACCOUNT.equals(cmd)) {
            doViewAccount();
        }
    }

    private void doViewAccount() {
        if (numInList(accountToCheck.getText(), accountList)) {
            Account account = accountList.getAccountFromStrPos(accountToCheck.getText());
            String details = "";
            details += "Account Name: " + account.getAccountName();
            if (!account.userName.equals("n/a")) {
                details += "\nUsername: " + account.getUserName();
            }
            if (!account.email.equals("n/a")) {
                details += "\nE-mail: " + account.getEmail();
            }
            details += "\nPassword: " + account.getPassword();
            JOptionPane.showMessageDialog(null, details);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid input. Please try again.");
        }
    }
}

package ui.functions;

import model.AccountList;
import ui.PasswordManagerAppGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteAccountTab extends Tab implements ActionListener {

    private static final String DELETE = "delete";
    private final JTextField accountToDel;

    private final AccountList accountList;
    private final PasswordManagerAppGUI manager;

    public DeleteAccountTab(PasswordManagerAppGUI manager, AccountList accountList) {
        JPanel panel = this;
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);
        this.accountList = accountList;
        this.manager = manager;

        JTextArea l1 = new JTextArea();
        l1.setEditable(false);
        l1.setText(accountList.accountNameWithPositionList().stream().reduce("", (acc, val) -> acc + '\n' + val));
        panel.add(l1);

        JLabel l2 = new JLabel("Enter corresponding number to delete account");
        panel.add(l2);

        accountToDel = new JTextField();
        panel.add(accountToDel);

        JButton view = new JButton("Delete");
        view.setAlignmentX(Component.CENTER_ALIGNMENT);
        view.addActionListener(this);
        view.setActionCommand(DELETE);
        panel.add(view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (DELETE.equals(cmd)) {
            doDeleteAccount();
        }
    }

    // EFFECTS: delete account from account list
    // MODIFIES: this
    private void doDeleteAccount() {
        if (numInList(accountToDel.getText(), accountList)) {
            accountList.deleteAccount(accountList.getAccountFromStrPos(accountToDel.getText()));
            manager.accountFrame.dispose();
            manager.openAccountFrame();
        } else {
            JOptionPane.showMessageDialog(null, "Invalid input. Please try again.");
        }
    }
}

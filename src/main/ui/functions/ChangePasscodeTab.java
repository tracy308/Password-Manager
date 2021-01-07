package ui.functions;

import exceptions.InvalidPassCodeException;
import model.LoginPassCode;

import javax.swing.*;

// EFFECTS: create the change passcode tab
public class ChangePasscodeTab extends Tab {

    public ChangePasscodeTab(LoginPassCode loginPassCode) {
        super();
        JPanel panel = this;
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);

        JLabel l1 = new JLabel("Please enter new 4-digit passcode (integers only)");
        panel.add(l1);
        JPasswordField newPasscode = new JPasswordField();
        panel.add(newPasscode);

        JButton change = new JButton("Change");
        change.addActionListener(e -> {
            try {
                loginPassCode.changePassCode(String.valueOf(newPasscode.getPassword()));
                JOptionPane.showMessageDialog(null, "Passcode changed successfully.");
            } catch (InvalidPassCodeException invalidPassCodeException) {
                JOptionPane.showMessageDialog(null,
                        "You have not entered a 4-digit pin. Please try use only integers.");
            }
        });
        panel.add(change);
    }
}

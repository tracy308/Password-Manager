package ui.functions;

import model.AccountList;

import javax.swing.*;

public abstract class Tab extends JPanel {

    public Tab() {
        JPanel panel = this;
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);
    }

    // EFFECTS: return true if input is digits only
    boolean digitOnly(String input) {
        return (input.matches("[0-9]+"));
    }

    // EFFECTS: return true if input is inbounds of account list
    boolean numInList(String str, AccountList accountList) {
        if (digitOnly(str)) {
            int num;
            num = Integer.parseInt(str);
            return num > 0 && num <= accountList.length();
        } else {
            return false;
        }
    }
}

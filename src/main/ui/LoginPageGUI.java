package ui;

import exceptions.InvalidPassCodeException;
import model.LoginPassCode;
import persistence.Reader;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class LoginPageGUI extends JFrame implements ActionListener {

    public static final int WIDTH = 350;
    public static final int HEIGHT = 125;

    private static final String OK = "ok";

    private LoginPassCode loginPassCode = new LoginPassCode();
    private static final String PASSCODE = "./data/passcode.txt";

    private static JPasswordField passCodeField;

    public LoginPageGUI() {
        super("Password Manager");

        JFrame loginFrame = new JFrame("Password Manager");
        loginFrame.setSize(WIDTH, HEIGHT);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setPassCode();

        JComponent panel = createLoginPanel();

        loginFrame.add(panel);
        loginFrame.setVisible(true);
    }

    // EFFECTS: create everything in login page
    protected JComponent createLoginPanel() {
        JPanel panel = new JPanel();

        panel.setLayout(null);

        JLabel passcodeLabel = new JLabel("Enter Passcode:");
        passcodeLabel.setBounds(20, 20, 100, 25);
        panel.add(passcodeLabel);

        passCodeField = new JPasswordField(10);
        passCodeField.setBounds(150, 20, 100, 25);
        passCodeField.setActionCommand(OK);
        passCodeField.addActionListener(this);
        panel.add(passCodeField);

        JButton okButton = new JButton("OK");
        okButton.setBounds(20, 50, 80, 25);
        okButton.setActionCommand(OK);
        okButton.addActionListener(this);
        panel.add(okButton);

        JLabel loginMessageLabel = new JLabel("");
        loginMessageLabel.setBounds(20, 90, 350, 25);
        panel.add(loginMessageLabel);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        setPassCode();
        if (OK.equals(cmd)) {
            processPassCode(String.valueOf(passCodeField.getPassword()));
            passCodeField.selectAll();
            passCodeField.replaceSelection("");
        }
    }

    // EFFECTS: set passcode from PASSCODE, if that file exists;
    // otherwise initializes passcode with default values
    // MODIFIES: this
    private void setPassCode() {
        try {
            loginPassCode.changePassCode(Reader.readPassCode(new File(PASSCODE)));
        } catch (IOException | InvalidPassCodeException e) {
            loginPassCode = new LoginPassCode();
        }
    }

    // EFFECTS: process user command
    private void processPassCode(String command) {
        if (command.equals(loginPassCode.getPassCode())) {
            new PasswordManagerAppGUI();
            playSound();
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect passcode. Please try again.");
        }
    }

    // EFFECTS: play success sound
    private void playSound() {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(
                    new File("./data/loginSuccessMusic.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            Thread.sleep(100);
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
}
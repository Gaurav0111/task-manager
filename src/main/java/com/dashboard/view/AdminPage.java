package main.java.com.dashboard.view;

import main.java.com.dashboard.model.User;
import main.java.com.dashboard.service.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox isAdminCheckBox;
    private JButton createButton;

    public AdminPage() {
        setTitle("Admin Page");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserService userService = new UserService();
                User user = new User();
                user.setUsername(usernameField.getText());
                user.setPassword(new String(passwordField.getPassword()));
                user.setIsAdmin(isAdminCheckBox.isSelected());
                userService.addUser(user);
                JOptionPane.showMessageDialog(null, "User created successfully!");
            }
        });
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(10, 20, 80, 25);
        panel.add(usernameLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(100, 20, 165, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 50, 165, 25);
        panel.add(passwordField);

        isAdminCheckBox = new JCheckBox("Is Admin");
        isAdminCheckBox.setBounds(10, 80, 80, 25);
        panel.add(isAdminCheckBox);

        createButton = new JButton("Create User");
        createButton.setBounds(10, 110, 150, 25);
        panel.add(createButton);
    }
}

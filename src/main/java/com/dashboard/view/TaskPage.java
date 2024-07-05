package main.java.com.dashboard.view;

import main.java.com.dashboard.model.Task;
import main.java.com.dashboard.service.TaskService;
import main.java.com.dashboard.service.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TaskPage extends JFrame {
    private JTextField titleField;
    private JTextField descriptionField;
    private JTextField statusField;
    private JComboBox<String> ownerComboBox;
    private JButton createButton;
    private JList<Task> taskList;

    public TaskPage() {
        setTitle("Task Page");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TaskService taskService = new TaskService();
                Task task = new Task();
                task.setTitle(titleField.getText());
                task.setDescription(descriptionField.getText());
                task.setStatus(statusField.getText());
                task.setOwner((String) ownerComboBox.getSelectedItem());
                taskService.addTask(task);
                JOptionPane.showMessageDialog(null, "Task created successfully!");
                refreshTaskList();
            }
        });

        refreshTaskList();
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setBounds(10, 20, 80, 25);
        panel.add(titleLabel);

        titleField = new JTextField(20);
        titleField.setBounds(100, 20, 165, 25);
        panel.add(titleField);

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(10, 50, 80, 25);
        panel.add(descriptionLabel);

        descriptionField = new JTextField(20);
        descriptionField.setBounds(100, 50, 165, 25);
        panel.add(descriptionField);

        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setBounds(10, 80, 80, 25);
        panel.add(statusLabel);

        statusField = new JTextField(20);
        statusField.setBounds(100, 80, 165, 25);
        panel.add(statusField);

        JLabel ownerLabel = new JLabel("Owner:");
        ownerLabel.setBounds(10, 110, 80, 25);
        panel.add(ownerLabel);

        ownerComboBox = new JComboBox<>();
        ownerComboBox.setBounds(100, 110, 165, 25);
        panel.add(ownerComboBox);

        createButton = new JButton("Create Task");
        createButton.setBounds(10, 140, 150, 25);
        panel.add(createButton);

        taskList = new JList<>();
        JScrollPane scrollPane = new JScrollPane(taskList);
        scrollPane.setBounds(10, 170, 355, 80);
        panel.add(scrollPane);
    }

    private void refreshTaskList() {
        TaskService taskService = new TaskService();
        List<Task> tasks = taskService.getAllTasks();
        DefaultListModel<Task> listModel = new DefaultListModel<>();
        for (Task task : tasks) {
            listModel.addElement(task);
        }
        taskList.setModel(listModel);

        UserService userService = new UserService();
        List<main.java.com.dashboard.model.User> users = userService.getAllUsers();
        ownerComboBox.removeAllItems();
        for (main.java.com.dashboard.model.User user : users) {
            ownerComboBox.addItem(user.getUsername());
        }
    }
}

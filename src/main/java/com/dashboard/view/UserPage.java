package main.java.com.dashboard.view;

import main.java.com.dashboard.model.Task;
import main.java.com.dashboard.model.User;
import main.java.com.dashboard.service.TaskService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserPage extends JFrame {
    private User user;
    private JList<Task> taskList;
    private JButton viewAllTasksButton;

    public UserPage(User user) {
        this.user = user;

        setTitle("User Page");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        viewAllTasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TaskPage taskPage = new TaskPage();
                taskPage.setVisible(true);
            }
        });

        refreshTaskList();
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        taskList = new JList<>();
        JScrollPane scrollPane = new JScrollPane(taskList);
        scrollPane.setBounds(10, 20, 355, 200);
        panel.add(scrollPane);

        viewAllTasksButton = new JButton("View All Tasks");
        viewAllTasksButton.setBounds(10, 230, 150, 25);
        panel.add(viewAllTasksButton);
    }

    private void refreshTaskList() {
        TaskService taskService = new TaskService();
        List<Task> tasks = taskService.getTasksByOwner(user.getUsername());
        DefaultListModel<Task> listModel = new DefaultListModel<>();
        for (Task task : tasks) {
            listModel.addElement(task);
        }
        taskList.setModel(listModel);
    }
}

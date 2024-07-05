package main.java.com.dashboard;

import main.java.com.dashboard.util.DatabaseInitializer;
import main.java.com.dashboard.view.LoginPage;

public class Main {
    public static void main(String[] args) {
        // Initialize the database
        DatabaseInitializer.initialize();

        // Initialize the login page
        LoginPage loginPage = new LoginPage();
        loginPage.setVisible(true);
    }
}

package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Program {

    static DbCommunicator dbCommunicator = new DbCommunicator();
    static String loggedInUser;

    public static String start() {
        System.out.println("""
                1. Sign in
                2. See overall Program
                3. Exit
                """);
        Scanner scanner = new Scanner(System.in);
        int userInput = (scanner.nextInt());

        switch (userInput) {
            case 1: logIn();
                break;
            case 2: //programOverview();
                break;
            case 3: System.exit(0);
                break;

            default:
                System.out.println("Enter either 1,2 or 3");
        }
        return null;

    }

    public static String printMainMenu() {
        System.out.println(
                """
                        A. Register for the event
                        B. See all participants
                        C. See participants from your program
                        D. Search for participant
                        E. See overall program
                        F. Exit
                        """);
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine().toUpperCase();
        switch (userInput) {
            case "A": EventRegister(loggedInUser);
                break;
            case "B": printAttendants();
                break;
            case "C":
                break;
            case "D": searchAttendant();
                break;
            case "E":
                break;
            case "F": System.out.println("You are now logged out");
                System.exit(0);
                break;
            default:
                System.out.println("Insert one of the options above");

        }
        return scanner.nextLine().toUpperCase();

    }

    public static String printNewMenu() {
        System.out.println(
                """
                        A. Edit registration for the event
                        B. See all participants
                        C. See participants from your program
                        D. Search for participant
                        E. See overall program
                        F. Exit
                        """);
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine().toUpperCase();
        switch (userInput) {
            case "A": regEdit();
                break;
            case "B": printAttendants();
                break;
            case "C":
                break;
            case "D": searchAttendant();
                break;
            case "E":
                break;
            case "F": System.out.println("You are now logged out");
                System.exit(0);
                break;
            default:
                System.out.println("Insert one of the options above");

        }
        return scanner.nextLine().toUpperCase();

    }

    public static void logIn() {

        String logInInput = "";
        while (true) {
            System.out.println("Enter your username to log in");
            Scanner logInScan = new Scanner(System.in);
            logInInput = logInScan.nextLine();
            boolean exists = dbCommunicator.userExists(logInInput);
            if (exists) {
                Program.loggedInUser = logInInput;
                break;
            } else {
                System.out.println("Wrong username");
            }

        }
        System.out.println("Welcome! You are now logged in with: " + logInInput);
        System.out.println("Here are your options:");
        dbCommunicator.findUserId(loggedInUser);
    }

    public static void EventRegister(String user) {
        dbCommunicator.checkUser(user);
        System.out.println("How many guests do you wish to invite?");
        Scanner registerScan = new Scanner(System.in);
        int regInput = registerScan.nextInt();
        if (regInput <5) {
        for (int i = 0; i < regInput; i++) {
            System.out.println("Enter name of guest");
            Scanner oneScan = new Scanner(System.in);
            String oneInput = oneScan.nextLine();

            try (Connection connection = DbConnector.getConnectionUni()) {
                String query = "SELECT * FROM student WHERE user = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, user);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    int studentID = resultSet.getInt("studentID");
                    dbCommunicator.registerGuest(oneInput, studentID);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }} else {
            System.out.println("You can not register more than 4 people");
            EventRegister(loggedInUser);
        }
        System.out.println("Number of registered guests " + regInput);
        printNewMenu();
    }

    public static void printAttendants() {
        List<Attendants> attendantsList = dbCommunicator.getAttendants();
        for (Attendants attendants : attendantsList) {
            System.out.println(attendants);
        }
    }

    public static void searchAttendant() {
        System.out.println("Enter name to search for participant:");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine().toUpperCase();
        dbCommunicator.attendantSearch(userInput);
    }

    public static void regEdit() {
        System.out.println("Enter name to delete person registrated");
        Scanner scanner = new Scanner(System.in);
        String deleteInput = scanner.nextLine().toUpperCase();
        dbCommunicator.removeAttending(deleteInput);
    }

}
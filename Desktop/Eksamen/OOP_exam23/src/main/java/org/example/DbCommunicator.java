package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbCommunicator {

    public boolean userExists(String username) {
        try (Connection connection = DbConnector.getConnectionUni()) {
            String query = "select * from student where user = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String user = rs.getString("user");
                if (username.equals(user)) {
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void checkUser(String user) {
        try (Connection connection = DbConnector.getConnectionUni()) {
            String query = "SELECT * FROM student WHERE user = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int studentID = resultSet.getInt("studentID");
                String studentName = resultSet.getString("studentName");
                registerStudent(studentID, studentName);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void registerStudent(int studentID, String studentName) {
        try (Connection con = DbConnector.getConnectionEvent()) {

            String query = "insert into attendants (studentID, studentName) values (?,?)";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, studentID);
            statement.setString(2, studentName);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void findUserId(String user) {
        try (Connection connection = DbConnector.getConnectionUni()) {
            String query = "SELECT * FROM student WHERE user = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int studentID = resultSet.getInt("studentID");
                ifRegistered(studentID);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        }

    public void ifRegistered(int studentID) {
        try (Connection connection = DbConnector.getConnectionEvent()) {
            String query = "select * from attendants where studentID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, studentID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
             Program.printNewMenu();
            } Program.printMainMenu();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void registerGuest(String guestName, int studentID) {
        try (Connection con = DbConnector.getConnectionEvent()) {

            String query = "insert into attendants (guestName, studentID) values (?,?)";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, guestName);
            statement.setInt(2, studentID);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Attendants> getAttendants() {
        try(Connection connection = DbConnector.getConnectionEvent()) {
            String query = "SELECT * FROM attendants";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<Attendants> attendants = new ArrayList<>();
            while (resultSet.next()) {
                int attendantID = resultSet.getInt("attendantID");
                int studentID = resultSet.getInt("studentID");
                int staffID = resultSet.getInt("staffID");
                String guestName = resultSet.getString("guestName");
                String studentName = resultSet.getString("studentName");
                String staffName = resultSet.getString("staffName");
                Attendants Attendant = new Attendants(attendantID, studentID, staffID, staffName,
                        studentName, guestName);

                attendants.add(Attendant);
            } return attendants;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void attendantSearch(String search) {
       try (Connection connection = DbConnector.getConnectionEvent()) {
           String query = "SELECT * FROM attendants WHERE studentName =? OR staffName =? OR guestName =?";
           PreparedStatement statement = connection.prepareStatement(query);
           statement.setString(1, search);
           statement.setString(2, search);
           statement.setString(3, search);
           ResultSet resultSet = statement.executeQuery();
           while (resultSet.next()) {
               int attendantID = resultSet.getInt("attendantID");
               int studentID = resultSet.getInt("studentID");
               int staffID = resultSet.getInt("staffID");
               String guestName = resultSet.getString("guestName");
               String studentName = resultSet.getString("studentName");
               String staffName = resultSet.getString("staffName");
               Attendants Attendant = new Attendants(attendantID, studentID, staffID, staffName,
                       studentName, guestName);
                   System.out.println(Attendant);
           }
       } catch (SQLException e) {
           throw new RuntimeException(e);
       } Program.printNewMenu();
    }

    public void removeAttending(String name) {
        try (Connection connection = DbConnector.getConnectionEvent()) {
            String query = "DELETE FROM attendants where guestName = ? OR studentName = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } Program.printNewMenu();
    }
}

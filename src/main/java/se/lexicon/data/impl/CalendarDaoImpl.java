package se.lexicon.data.impl;

import java.sql.Connection;
import se.lexicon.data.CalendarDao;
import se.lexicon.exceptions.MySQLException;
import se.lexicon.model.Calendar;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class CalendarDaoImpl implements CalendarDao {

    private Connection connection;

    public CalendarDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Calendar createCalendar(String username, String title) {
        String query = "INSERT INTO calendars(username, title) VALUES(?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            // Set parameters
            preparedStatement.setString(1, username); // Use index 1 for the first parameter
            preparedStatement.setString(2, title);    // Use index 2 for the second parameter
            // Execute query
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new MySQLException("Creating calendar failed, no rows affected.");
            }
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                return new Calendar(id, title, username);
            } else {
                throw new MySQLException("Creating calendar failed, no ID obtained.");
            }
        } catch (SQLException e) {
            throw new MySQLException("Error occurred while creating calendar: " + title, e);
        }
    }


    @Override
    public Optional<Calendar> findById(int id) {
        //Create a query
        String query = "SELECT * FROM calendars WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String username = resultSet.getString("username");
                    String title = resultSet.getString("title");
                    return Optional.of(new Calendar(id, title, username));
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new MySQLException("Error occurred while finding the calendar by id: " + id, e);
        }
    }

    @Override
    public Optional<Calendar> findByTitle(String title) {
        //Create a query
        String query = "SELECT * FROM calendars WHERE title = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, title);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String username = resultSet.getString("username");
                    return Optional.of(new Calendar(id, title, username));
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new MySQLException("Error occurred while finding the calendar by title: " + title, e);
        }
    }

    @Override
    public Collection<Calendar> findCalendarsByUsername(String username) {
        //Create a query
        String query = "SELECT * FROM calendars WHERE username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Calendar> calendars = new ArrayList<>();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    calendars.add(new Calendar(id, title, username));
                }
                return calendars;
            }
        } catch (SQLException e) {
            throw new MySQLException("Error occurred while finding the calendars by username: " + username, e);
        }
    }

    @Override
    public boolean deleteCalendar(int id) {
        //Create a query
        String query = "DELETE FROM calendars WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            //Set parameters
            preparedStatement.setInt(1, id);
            //Execute query
            int affectedRows = preparedStatement.executeUpdate();
            // Check if any rows were affected
            if (affectedRows == 0) {
                throw new MySQLException("Deleting calendar failed, no rows affected.");
            }

        } catch (SQLException e) {
            throw new MySQLException("Error occurred while deleting the calendar by id: " + id, e);
        }
        return false;
    }


    }


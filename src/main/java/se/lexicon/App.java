package se.lexicon;
import java.lang.System;


import se.lexicon.dao.CalendarDao;

import se.lexicon.dao.UserDao;
import se.lexicon.dao.db.MeetingCalendarDBConnection;
import se.lexicon.dao.impl.CalendarDaoImpl;
import se.lexicon.dao.impl.UserDaoImpl;
import se.lexicon.exceptions.AuthenticationFieldsException;
import se.lexicon.exceptions.UserExpiredException;
import se.lexicon.model.Calendar;
import se.lexicon.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

public class App
{
    public static void main( String[] args ) {

        // Establish database connection
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/meeting_calendar_db", "username", "password")) {
            // Create UserDao instance
            UserDao userDao = new UserDaoImpl(connection);

            // Test createUser method
            User newUser = userDao.createUser("TestUser");
            System.out.println("New user created: " + newUser.getUsername());

            // Test findByUsername method
            Optional<User> userOptional = userDao.findByUsername("TestUser");
            userOptional.ifPresent(user -> System.out.println("Found user by username: " + user.getUsername()));

            // Test authenticate method
            User user = new User("TestUser", "password");
            boolean isAuthenticated = userDao.authenticate(user);
            System.out.println("Authentication result: " + isAuthenticated);

        } catch (SQLException | UserExpiredException | AuthenticationFieldsException e) {
            e.printStackTrace();
        }
    }



    }









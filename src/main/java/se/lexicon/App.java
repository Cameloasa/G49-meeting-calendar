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
import java.util.Collection;
import java.util.Optional;

public class App
{
    public static void main( String[] args ) {
         /*
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
            e.printStackTrace();*/
        }
    // Test createCalendar method
     {try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/meeting_calendar_db", "username", "password")) {
    CalendarDao calendarDao = new CalendarDaoImpl(connection);

    // Test createCalendar method
    Calendar createdCalendar = calendarDao.createCalendar("testuser", "testcalendar");
    System.out.println("Created calendar: " + createdCalendar);

    // Test findById method
    Optional<Calendar> foundCalendar = calendarDao.findById(createdCalendar.getId());
    System.out.println("Found calendar by id: " + foundCalendar.orElse(null));

    // Test findByTitle method
    Optional<Calendar> foundByTitle = calendarDao.findByTitle("testcalendar");
    System.out.println("Found calendar by title: " + foundByTitle.orElse(null));

// Test findCalendarsByUsername method
    Collection<Calendar> calendarsByUsername = calendarDao.findCalendarsByUsername("testuser");
    System.out.println("Calendars found by username: " + calendarsByUsername);

    // Test deleteCalendar method
    boolean deleted = calendarDao.deleteCalendar(createdCalendar.getId());
    System.out.println("Calendar deleted: " + deleted);

} catch (SQLException e) {
    e.printStackTrace();
}
}

    }









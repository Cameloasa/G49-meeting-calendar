package se.lexicon;
import java.lang.System;


import se.lexicon.data.CalendarDao;

import se.lexicon.dao.db.MeetingCalendarDBConnection;
import se.lexicon.data.impl.CalendarDaoImpl;
import se.lexicon.dao.impl.UserDaoImpl;
import se.lexicon.model.Calendar;
import se.lexicon.model.User;

import java.util.Optional;

public class App
{
    public static void main( String[] args ) {

        se.lexicon.dao.UserDao userDao = new UserDaoImpl(MeetingCalendarDBConnection.getConnection());
        //User createdUser = userDao.createUser("testuser");
        //User createdUser2 = userDao.createUser("testuser2");
        //System.out.println("userInfo = " + createdUser2.userInfo());

        Optional<User> userOptional = userDao.findByUsername("testuser");
        if(userOptional.isPresent()){
            System.out.println(userOptional.get().userInfo());}
    }
    //Create a new calendar
    Calendar calendar = new Calendar( "testcalendar", "testuser");

    //Create CalendarDao
    CalendarDao calendarDao = new CalendarDaoImpl(MeetingCalendarDBConnection.getConnection());

    // Call the createCalendar method and store the returned value in createdCalendar
    Calendar createdCalendar = calendarDao.createCalendar("username", "title");

// Check if createdCalendar is not null before printing
    //if (createdCalendar != null) {
    // Print the createdCalendar object
    //System.out.println("createdCalendar = " + createdCalendar);
//}   else {
    //System.out.println("Failed to create calendar."); // Print a message if createdCalendar is null

    //Find calendar by id
    //Optional<Calendar> calendarOptional = calendarDao.findById(createdCalendar.getId());


}







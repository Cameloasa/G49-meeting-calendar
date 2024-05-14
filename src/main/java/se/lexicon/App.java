package se.lexicon;


import se.lexicon.data.CalendarDao;
import se.lexicon.data.UserDao;
import se.lexicon.data.db.MeetingCalendarDBConnection;
import se.lexicon.data.impl.CalendarDaoImpl;
import se.lexicon.data.impl.UserDaoImpl;
import se.lexicon.exceptions.CalendarExceptionHandler;
import se.lexicon.model.Calendar;
import se.lexicon.model.User;

import java.util.Optional;

public class App
{
    public static void main( String[] args ) {

        UserDao userDao = new UserDaoImpl(MeetingCalendarDBConnection.getConnection());
        //User createdUser = userDao.createUser("testuser");
        User createdUser2 = userDao.createUser("testuser2");
        System.out.println("userInfo = " + createdUser2.userInfo());

        Optional<User> userOptional = userDao.findByUsername("testuser");
        if(userOptional.isPresent()){
            System.out.println(userOptional.get().userInfo());}
    }

    //Create CalendarDao
    CalendarDao calendarDao = new CalendarDaoImpl(MeetingCalendarDBConnection.getConnection());

    //Create a new calendar
    Calendar calendar = new Calendar(1, "testcalendar", "testuser");

    //add calendar to database
    Calendar createdCalendar = calendarDao.createCalendar("testuser", "testcalendar");
    System.out.println("createdCalendar = " + createdCalendar);




    //Find calendar by id
    //Optional<Calendar> calendarOptional = calendarDao.findById(createdCalendar.getId());

}

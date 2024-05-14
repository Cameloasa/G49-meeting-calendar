package se.lexicon;


import se.lexicon.data.UserDao;
import se.lexicon.data.db.MeetingCalendarDBConnection;
import se.lexicon.data.impl.UserDaoImpl;
import se.lexicon.exceptions.CalendarExceptionHandler;
import se.lexicon.model.User;

public class App
{
    public static void main( String[] args ) {

        UserDao userDao = new UserDaoImpl(MeetingCalendarDBConnection.getConnection());
        User createdUser = userDao.createUser("admin");
        User user = userDao.findByUserName("admin").get();
        System.out.println(user);
    }
}

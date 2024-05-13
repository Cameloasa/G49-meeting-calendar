package se.lexicon.data;

import se.lexicon.model.Calendar;

import java.util.Collection;
import java.util.Optional;

public interface CalendarDao {

    Calendar createCalendar(String title, String username);

    Optional<Calendar> findById(int id);

    Optional<Calendar> findByTitle(String title);

    Collection<Calendar> findCalendarsByUsername(String username);

    boolean deleteCalendar(int id);


}

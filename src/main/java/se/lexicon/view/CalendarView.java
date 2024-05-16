package se.lexicon.view;

import se.lexicon.model.Calendar;
import se.lexicon.model.Meeting;
import se.lexicon.model.User;
import se.lexicon.util.ConsoleColors;

import java.util.List;

public interface CalendarView {
    //method for displaying menu
    default void displayMenu() {
        System.out.println("Calendar Options");
        System.out.println("0. Resister");
        System.out.println("1. Login");
        System.out.println("2. Add Calendar");
        System.out.println("3. Add Meeting");
        System.out.println("4. Delete Calendar");
        System.out.println("5. Display Calendar Meeting");
        System.out.println("6. Logout");
        System.out.println("7. Exit");
        System.out.println("Enter your choice");
    }

    //method for displaying error message
    default void displayErrorMessage(String message) {
        System.out.println(ConsoleColors.RED + message + ConsoleColors.RESET);

    }
    //method for displaying success message
    default void displaySuccessMessage(String message) {
        System.out.println(ConsoleColors.GREEN + message + ConsoleColors.RESET);
    }

    //method for displaying warning message
    default void displayWarningMessage(String message) {
        System.out.println(ConsoleColors.YELLOW + message + ConsoleColors.RESET);
    }

    //method for displaying info message
    default void displayMessage(String message) {
        System.out.println(ConsoleColors.BLUE + message + ConsoleColors.RESET);
    }

    void displayCalendar(Calendar calendar);
    void displayMeeting(List<Meeting> meetings);
    void displayUser(User user);

    //
    User promoteUserForm();
    String promoteString();

    Meeting promoteMeetingForm();
    String promoteCalendarForm();


}

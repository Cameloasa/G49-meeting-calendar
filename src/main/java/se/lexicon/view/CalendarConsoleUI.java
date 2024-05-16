package se.lexicon.view;

import se.lexicon.model.Calendar;
import se.lexicon.model.Meeting;
import se.lexicon.model.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class CalendarConsoleUI implements CalendarView {

    @Override
    public void displayCalendar(Calendar calendar) {
        System.out.println("Calendar: " + calendar.calendarInfo());
        System.out.println("----------------------------------------");
    }

    @Override
    public void displayMeeting(List<Meeting> meetings) {
        //Check if there are any meetings
        if (meetings.isEmpty()) {
            System.out.println("No meetings found");

        }else{
            for (Meeting meeting : meetings) {
                System.out.println("Meeting: " + meeting.meetingInfo());
                System.out.println("----------------------------------------");
            }
        }

    }

    @Override
    public void displayUser(User user) {
        System.out.println("User: " + user.userInfo());
        System.out.println("----------------------------------------");
    }

    @Override
    public User promoteUserForm() {
        //Take data from user to register to  the database
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        return new User(username, password);
    }

    @Override
    public String promoteString() {
        //Take data from user in form of a string
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    @Override
    public Meeting promoteMeetingForm() {
        //Take data from user to register a meeting to the database
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter title: ");
        String title = scanner.nextLine();
        System.out.println("Enter description: ");
        String description = scanner.nextLine();
        System.out.println("Enter start time (yyyy-MM-dd HH:mm):");
        String startTime = scanner.nextLine();
        System.out.println("Enter end time (yyyy-MM-dd HH:mm):");
        String endTime = scanner.nextLine();
        //Convert string to LocalDateTime
        LocalDateTime startDateTime = LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime endDateTime = LocalDateTime.parse(endTime , DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        //Check if start time is before end time
        if (startDateTime.isAfter(endDateTime)) {
            System.out.println("Start time cannot be after end time");
            System.out.println("Enter start time (yyyy-MM-dd HH:mm):");
            startTime = scanner.nextLine();
            System.out.println("Enter end time (yyyy-MM-dd HH:mm):");
            endTime = scanner.nextLine();
            startDateTime = LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            endDateTime = LocalDateTime.parse(endTime , DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }

        return new Meeting(title, startDateTime, endDateTime, description);



    }

    @Override
    public String promoteCalendarForm() {
        //Take data from user to register a calendar to the database
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a Title: ");
        return scanner.nextLine();
    }
}

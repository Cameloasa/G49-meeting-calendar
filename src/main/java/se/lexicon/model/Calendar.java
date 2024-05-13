package se.lexicon.model;

import java.util.ArrayList;
import java.util.List;

public class Calendar {
    private int id;
    private String title;
    private String username;
    private List<Meeting> meetings;

    public Calendar(String title , String username) {
        this(title);
        this.username = username;

    }
    public Calendar(int id, String title, String username, List<Meeting> meetings) {
        this(title,username);
        this.id = id;
        this.meetings = meetings;
    }


    public Calendar(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUsername() {
        return username;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    //method for adding meetings
    public void addMeeting(Meeting meeting) {
        if (meetings == null) {
            meetings = new ArrayList<>();
        }
        this.meetings.add(meeting);
    }

    //method for removing meetings
    public void removeMeeting(Meeting meeting) {
        if (meetings == null) {
            throw new IllegalArgumentException("Meetings list is null");
        }
        if (meeting == null) {
            throw new IllegalArgumentException("Meeting data is null");
        }
        this.meetings.remove(meeting);
    }
    //Method for Calendar info using StringBuilder
    public String calendarInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Calendar info: ").append("\n");
        stringBuilder.append("id: ").append(id).append("\n");
        stringBuilder.append("Title: ").append(title).append("\n");
        stringBuilder.append("Username: ").append(username).append("\n");
        return stringBuilder.toString();
    }
}

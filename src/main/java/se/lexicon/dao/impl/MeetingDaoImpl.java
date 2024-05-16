package se.lexicon.dao.impl;

import se.lexicon.dao.MeetingDao;
import se.lexicon.exceptions.MySQLException;
import se.lexicon.model.Calendar;
import se.lexicon.model.Meeting;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class MeetingDaoImpl implements MeetingDao {

    // Connection
    private final Connection connection;
    // Constructor
    public MeetingDaoImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public Meeting createMeeting(Meeting meeting) {
        //Create a query
        String query = "INSERT INTO meetings ( title,start_time, end_time, _description, calendar_id) VALUES (?, ?, ?, ?, ?)";

        try {
            //Create a prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

            //Set parameters
            preparedStatement.setString(1, meeting.getTitle());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(meeting.getStartTime()));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(meeting.getEndTime()));
            preparedStatement.setString(4, meeting.getDescription());
            preparedStatement.setInt(5, meeting.getCalendar().getId());

            //Execute query
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new MySQLException("Creating meeting failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    //Create the meeting
                    meeting = new Meeting(id, meeting.getTitle(), meeting.getStartTime(), meeting.getEndTime(), meeting.getDescription(), meeting.getCalendar());
                    //Return the meeting
                    return meeting;
                } else {
                    throw new MySQLException("Creating meeting failed, no ID obtained.");
                }
            }

        }catch (SQLException e) {
            throw new MySQLException("Error occurred while creating the meeting: " + meeting, e);
        }

    }

    @Override
    public Optional<Meeting> findById(int id) {
        //Create a query
        String query = "SELECT * FROM meetings WHERE id = ?";
        try {
            //Create a prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            //Set parameters
            preparedStatement.setInt(1, id);
            //Execute query
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                //Meeting - id, title, start, end time, description
                int meetingId = resultSet.getInt("id");
                String title = resultSet.getString("title");
                Timestamp startTime = resultSet.getTimestamp("start_time");
                Timestamp endTime = resultSet.getTimestamp("end_time");
                String description = resultSet.getString("description");


                return Optional.of(new Meeting(meetingId, title, startTime.toLocalDateTime(), endTime.toLocalDateTime(), description, null));
            }
        }
        catch (SQLException e) {
            throw new MySQLException("Error occurred while finding the meeting by id: " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public Collection<Meeting> findAllMeetingsByCalendarId (int calendarId) {
        //Create a list of meetings
        List<Meeting> meetings = new ArrayList<>();
        //Create a query
        String query = "SELECT * FROM meetings WHERE calendar_id = ?";
        //Create a prepared statement
        try (

                PreparedStatement preparedStatement = connection.prepareStatement(query)

        ) {
            //Set parameters
            preparedStatement.setInt(1, calendarId);
            //Execute query
            ResultSet resultSet = preparedStatement.executeQuery();
            //Loop through the result set
            while (resultSet.next()) {
                int meetingId = resultSet.getInt("id");
                String title = resultSet.getString("title");
                LocalDateTime startTime = resultSet.getTimestamp("start_time").toLocalDateTime();
                LocalDateTime endTime = resultSet.getTimestamp("end_time").toLocalDateTime();
                String description = resultSet.getString("description");

                //Calendar - id, calendarUsername, CalendarTitle
                 calendarId = resultSet.getInt("calendar_id");
                String calendarUsername = resultSet.getString("username");
                String calendarTitle = resultSet.getString("calendarTitle");



                meetings.add(new Meeting(meetingId, title, startTime, endTime, description, new Calendar(calendarId,calendarUsername,calendarTitle)));
            }

        }catch (SQLException e) {
            throw new MySQLException("Error occurred while finding the meetings by calendar id: " + calendarId, e);
        }
        if (meetings.isEmpty()) {
            return null;
        } else {
                return meetings;
        }

    }

    @Override
    public boolean deleteMeeting(int meetingId) {
        //Create a query
        String query = "DELETE FROM meetings WHERE id = ?";
        try {
            //Create a prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            //Set parameters
            preparedStatement.setInt(1, meetingId);
            //Execute query
            int affectedRows = preparedStatement.executeUpdate();
            //Check if any rows were affected
            return affectedRows > 0;
        }
        catch (SQLException e) {
            throw new MySQLException("Error occurred while deleting the meeting by id: " + meetingId, e);
        }
    }
}

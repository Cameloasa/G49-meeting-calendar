package se.lexicon.data.impl;

import se.lexicon.data.UserDao;
import se.lexicon.data.db.MeetingCalendarDBConnection;
import se.lexicon.exceptions.AuthenticationFieldException;
import se.lexicon.exceptions.DBConnectionException;
import se.lexicon.exceptions.UserExpiredException;
import se.lexicon.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private Connection connection;

    private User extractUserFromResultSet(ResultSet resultSet) throws SQLException, DBConnectionException {
        //get data from result set
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        boolean expired = resultSet.getBoolean("expired");


        return new User(username, password, expired);

    }


    public UserDaoImpl() {
        this.connection = MeetingCalendarDBConnection.getConnection();
    }

    @Override
    public User createUser(String username) {
        return null;
    }

    @Override
    public Optional<User> findByUserName(String username) {
        return Optional.empty();
    }

    @Override
    public boolean authenticate(User user) throws UserExpiredException, AuthenticationFieldException {
        return false;
    }
}

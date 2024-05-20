package se.lexicon.dao;

import se.lexicon.exceptions.AuthenticationFailedException;
import se.lexicon.exceptions.AuthenticationFieldsException;
import se.lexicon.exceptions.UserExpiredException;
import se.lexicon.model.User;

import java.util.Optional;

public interface UserDao {

     User createUser(String username);
     Optional<User> findByUsername(String username);
     boolean authenticate(User user) throws UserExpiredException, AuthenticationFailedException;

     //Add more methods
}

package se.lexicon.data;

import se.lexicon.exceptions.AuthenticationFieldsException;
import se.lexicon.exceptions.UserExpiredException;
import se.lexicon.model.User;

import java.util.Optional;

public interface UserDao {

     User createUser(String username);
     Optional<User> findByUserName(String username);
     boolean authenticate(User user) throws UserExpiredException, AuthenticationFieldsException;

     //Add more methods
}

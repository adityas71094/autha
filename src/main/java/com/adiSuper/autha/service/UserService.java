package com.adiSuper.autha.service;

import com.adiSuper.autha.model.User;
import com.adiSuper.autha.repository.UserRepository;
import com.adiSuper.generated.core.Tables;
import com.adiSuper.generated.core.tables.records.UsersRecord;
import org.jooq.DSLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DSLContext db;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getUsers()
    {
        List<User> users = userRepository.findAll();
        return users;
    }

    public User getUser(UUID id)
    {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent())
        {
            throw new EntityNotFoundException("User with ID: " + id + " not found");
        }
        return user.get();
    }

    public int addUser(User user)
    {
        if (isDBConstraintsSatisfied(user))
        {
            String password = user.getPassword();
            String passwordHash = passwordEncoder.encode(password);
            user.setPasswordHash(passwordHash);
            User returnUser = userRepository.save(user);
        }
        else
        {
            throw new EntityExistsException("User with given username/email-ID already exists");
        }
        return 1;
    }

    public int updateUser(User user, UUID id)
    {
        if (isDBConstraintsSatisfied(user))
        {
            Optional<User> oldUser = userRepository.findById(id);
            if(oldUser.isPresent()) {
                user.setId(id);
                user.setPasswordHash(null);
                UsersRecord userRecord = db.newRecord(Tables.USERS, user);
                userRecord.update();
                return 1;
            }
        }
        else
        {
            throw new EntityExistsException("The given username/email-ID is already taken");
        }
        return 0;
    }

    public int removeUser(UUID id)
    {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
        else
             throw new EntityNotFoundException("User with ID: " + id + " not found");
        return 1;
    }

    private boolean isDBConstraintsSatisfied(User user)
    {

        if (isUsernameExists(user) || isEmailIDExists(user))
        {
            return false;
        }
        return true;
    }

    private boolean isUsernameExists(User user)
    {
        Optional<User> existingUser = userRepository.findOneByUsername(user.getUsername());
        if (existingUser.isPresent())
        {
            return true;
        }
        return false;
    }

    private boolean isEmailIDExists(User user)
    {
        Optional<User> existingUser = userRepository.findOneByEmailId(user.getEmailId());
        if (existingUser.isPresent())
        {
            return true;
        }
        return false;
    }
}

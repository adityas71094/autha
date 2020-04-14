package com.adiSuper.autha.controller;

import com.adiSuper.autha.model.User;
import com.adiSuper.autha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {


    @Autowired
    private UserService userService;
    
    @GetMapping("/users")
    public List<User> getUsers()
    {
        List<User> users = userService.getUsers();
        if (users.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return users;
    }

    @GetMapping ("/users/{id}")
    public User getUser(@PathVariable Integer id)
    {
        try
        {
              return userService.getUser(id);
        }
        catch(EntityNotFoundException e)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PostMapping("/users")
    public int addUser(@Valid @RequestBody User user)
    {
        try
        {
            userService.addUser(user);
        }
        catch(EntityExistsException e)
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }

        return 1;
    }


    @PatchMapping("/users/{id}")
    public int patchUser(@RequestBody User user, @PathVariable Integer id)
    {
        try
        {
            return userService.updateUser(user, id);
        }
        catch (EntityExistsException e)
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }

    }

    @DeleteMapping("/users/{id}")
    public int removeUser(@PathVariable Integer id)
    {
        try
        {
            userService.removeUser(id);
            return 1;
        }
        catch(EntityNotFoundException e)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }


}

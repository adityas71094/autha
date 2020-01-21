package com.adiSuper.autha.user;


import com.adiSuper.generated.core.Tables;
import com.adiSuper.generated.core.tables.pojos.Users;
import com.adiSuper.generated.core.tables.records.UsersRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DSLContext db;

    
    @GetMapping("/users")
    public List<Users> getUsers()
    {
        List<Users> users = userRepository.findAll();
        return users;
    }

    @GetMapping ("/users/{id}")
    public Users getUser(@PathVariable Integer id)
    {
        Users user = userRepository.getOne(id);
        return user;
    }

    @PostMapping("/users")
    public int addUser(@Valid @RequestBody Users user)
    {
        userRepository.save(user);
        return 1;
    }

    @PutMapping("/users/{id}")
    public int editUser(@RequestBody Users user, @PathVariable Integer id)
    {
        Optional<Users> oldUser = userRepository.findById(id);
        user.setId(1);
        UsersRecord userRecord = db.newRecord(Tables.USERS, user);
        userRecord.update();
        return 1;
    }

    @DeleteMapping("/users/{id}")
    public int removeUser(@PathVariable Integer id)
    {
        userRepository.deleteById(id);
        return 1;
    }
}

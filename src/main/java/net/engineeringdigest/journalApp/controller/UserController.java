package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v2/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> usersList = userService.retrieveUsers();
        return ResponseEntity.ok(usersList);
    }


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.saveEntry(user);
        return ResponseEntity.ok(savedUser);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?>updateUser(@RequestBody User user, @PathVariable String userName) {
        User foundUser = userService.findByUserName(userName);
        if(foundUser != null) {
            foundUser.setUserName(user.getUserName());
            foundUser.setPassword(user.getPassword());
            userService.saveEntry(foundUser);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @DeleteMapping
//    public ResponseEntity<?> deleteUser(@RequestBody User user) {
//
//    }

}

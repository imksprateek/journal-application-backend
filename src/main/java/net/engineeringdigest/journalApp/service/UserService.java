package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveEntry(User userEntry) {
        return userRepository.save(userEntry);
    }

    public List<User> retrieveUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId userId) {
        return userRepository.findById(userId);
    }

    public boolean deleteById(ObjectId userId) {
        userRepository.deleteById(userId);
        return true;
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public User updateById(ObjectId userId, User myEntry) {
        User userEntry = userRepository.findById(userId).orElse(null);
        if (userEntry != null) {
            myEntry.setId(userId);
            return userRepository.save(myEntry);
        }
        else return null;
    }
}

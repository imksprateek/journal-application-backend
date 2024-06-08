package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;


    public void saveEntry(JournalEntry journalEntry, String userName) {
        User foundUser = userService.findByUserName(userName);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry savedJournal = journalEntryRepository.save(journalEntry);
        foundUser.getJournalEntryList().add(savedJournal);
        userService.saveEntry(foundUser);
    }

    public void saveEntry(JournalEntry journalEntry) {
        journalEntry.setDate(LocalDateTime.now());
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> retrieveJournalEntries() {
        return journalEntryRepository.findAll();
    }

    public JournalEntry findById(ObjectId journalId) {
        return journalEntryRepository.findById(journalId).orElse(null);
    }

    public boolean deleteById(String userName, ObjectId journalId) {
        User user = userRepository.findByUserName(userName);
        user.getJournalEntryList().removeIf(x -> x.getId().equals(journalId));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(journalId);
        return true;
    }

    public JournalEntry updateById(ObjectId journalId, JournalEntry myEntry) {
        JournalEntry journalEntry = journalEntryRepository.findById(journalId).orElse(null);
        if (journalEntry != null) {
            myEntry.setId(journalId);
            return journalEntryRepository.save(myEntry);
        }
        else return null;
    }
}

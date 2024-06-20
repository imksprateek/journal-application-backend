package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
//    Use @Transactional annotation to carry out the method steps as a single unit (single operation). If any step fails inside the method, the changes are reverted. The changes are only committed to the DB when all steps of the method are successful. This is used for doing any save/delete/update operation on the database. You also need to add @EnableTransactionManagement annotation to the main Application class. When there are 2 concurrent requests, it creates 2 different containers in Transaction context and executes separately for each request (Transaction context is like session)
//    PlatformTransactionManager.class annotation and it's implementation MongoTransactionManager.class carry out transactions and rollback when any of the steps fail
    /*Steps to integrate DB Transactions into the code:
        - Annotate Service method with @Transactional
        - annotate application class with @EnableTransactionManagement
        - Provide an implementation of PlatformTransactionManager as a Bean
     */

//    NOTE: @Transactional requires replicas of DB in order to manage multiple requests. So if you're running your DB on localhost, you might require replicas. But if you use MongoDB Atlas, a managed DB in Cloud it is managed by the cloud provider. So there's no problem if you use MongoDB Atlas instance

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

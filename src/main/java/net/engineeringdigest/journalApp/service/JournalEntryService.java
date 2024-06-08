package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> retrieveJournalEntries() {
        return journalEntryRepository.findAll();
    }

    public JournalEntry findById(ObjectId journalId) {
        return journalEntryRepository.findById(journalId).orElse(null);
    }

    public boolean deleteById(ObjectId journalId) {
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

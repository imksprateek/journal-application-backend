package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("v2/journal")
public class JournalEntryControllerV2 {
    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping("")
    public ResponseEntity<List<JournalEntry>> getAll() {
        List<JournalEntry> journalsList = journalEntryService.retrieveJournalEntries();
        if(!journalsList.isEmpty() && journalsList != null) {
            return new ResponseEntity<>(journalsList, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry) {
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
    }

    @GetMapping("{journalId}")
    public ResponseEntity<JournalEntry> getEntryById(@PathVariable ObjectId journalId) {
        JournalEntry journal = journalEntryService.findById(journalId);
        try{
            return new ResponseEntity<>(journal, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(journal, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{journalId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId journalId) {
        journalEntryService.deleteById(journalId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{journalId}")
    public ResponseEntity<JournalEntry> updateEntry(@PathVariable ObjectId journalId, @RequestBody JournalEntry myEntry) {
        myEntry.setDate(LocalDateTime.now());
        JournalEntry updatedJournal = journalEntryService.updateById(journalId, myEntry);

        if (updatedJournal == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(updatedJournal, HttpStatus.OK);
    }
}


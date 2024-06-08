package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;
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
    @Autowired
    private UserService userService;

    @GetMapping("/{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName) {
        User foundUser = userService.findByUserName(userName);
        List<JournalEntry> journalsList = foundUser.getJournalEntryList();
        if(!journalsList.isEmpty() && journalsList != null) {
            return new ResponseEntity<>(journalsList, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String userName) {
        try {
            journalEntryService.saveEntry(myEntry, userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/find/{journalId}")
    public ResponseEntity<JournalEntry> getEntryById(@PathVariable ObjectId journalId) {
        JournalEntry journal = journalEntryService.findById(journalId);
        try{
            return new ResponseEntity<>(journal, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(journal, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/id/{userName}/{journalId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId journalId, @PathVariable String userName) {
        journalEntryService.deleteById(userName, journalId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{userName}/{journalId}")
    public ResponseEntity<?> updateEntry(@PathVariable ObjectId journalId, @PathVariable String userName, @RequestBody JournalEntry myEntry) {
        JournalEntry old = journalEntryService.findById(journalId);

        if(old != null) {
            old.setTitle(myEntry.getTitle() != null && !myEntry.getTitle().equals("") ? myEntry.getTitle() : old.getTitle());
            old.setContent(myEntry.getContent() != null && !myEntry.getContent().equals("") ? myEntry.getContent() : old.getContent());
            journalEntryService.saveEntry(old);

            return new ResponseEntity<>(old, HttpStatus.OK);

        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}


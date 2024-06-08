package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("v1/journal")
public class JournalEntryControllerV1 {

    private Map<ObjectId, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping("")
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping("")
    public boolean createEntry(@RequestBody JournalEntry myEntry){
        journalEntries.put(myEntry.getId(), myEntry);
        return true;
    }

    @GetMapping("{journalId}")
    public JournalEntry getEntryById(@PathVariable ObjectId journalId){
        return journalEntries.get(journalId);
    }

    @DeleteMapping("{journalId}")
    public JournalEntry deleteEntryById(@PathVariable ObjectId journalId){
        return journalEntries.remove(journalId);
    }

    @PutMapping("{journalId}")
    public JournalEntry updateEntry(@PathVariable ObjectId journalId, @RequestBody JournalEntry myEntry){
        return journalEntries.put(journalId, myEntry);
    }
}

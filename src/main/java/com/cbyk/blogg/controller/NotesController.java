package com.cbyk.blogg.controller;

import com.cbyk.blogg.model.Note;
import com.cbyk.blogg.model.NoteDTO;
import com.cbyk.blogg.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NotesController {

    @Autowired
    NoteService noteService;

    @GetMapping("/fetchNotesByTitle")
    public List<Note> fetchNotesByTitle(@RequestParam String title) {
        return noteService.fetchAllNotesByTitle(title);
    }

    @GetMapping("/notetest")
    public Note notetest() {
        return new Note("Test");
    }

    @PostMapping("/createNote")
    public NoteDTO createNote(@RequestBody NoteDTO noteDTO) {
        noteService.createNote(noteDTO);
        return noteDTO;

    }
}

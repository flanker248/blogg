package com.cbyk.blogg.controller;

import com.cbyk.blogg.interceptor.RequestInterceptor;
import com.cbyk.blogg.model.Note;
import com.cbyk.blogg.model.NoteDTO;
import com.cbyk.blogg.service.NoteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.json.JsonObject;
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

    @PostMapping("/createNote")
    public NoteDTO createNote(@RequestBody NoteDTO noteDTO) {
        noteService.createNote(noteDTO);
        return noteDTO;

    }

    @GetMapping("hotspot")
    public Object hotspot() throws JsonProcessingException {
        String json = new ObjectMapper().writeValueAsString(RequestInterceptor.reqIp);
        return json;
    }
}

package com.cbyk.blogg.service;


import com.cbyk.blogg.model.BlogPost;
import com.cbyk.blogg.model.Note;
import com.cbyk.blogg.model.NoteDTO;
import com.cbyk.blogg.repo.BlogPostsRepository;
import com.cbyk.blogg.repo.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    NotesRepository repository;

    public boolean saveNote(Note note) {
        repository.save(note);
        return true;
    }

    public List<Note> fetchAllNotes() {
        return repository.findAll();
    }

    public List<Note> fetchAllNotesByTitle(String title) {
        return repository.findAllByTitleLikeIgnoreCase(title);
    }

    public void createNote(NoteDTO noteDTO) {
        repository.save(new Note(noteDTO));
    }

    public Note getNoteById(String id) {
        return repository.findById(id).orElse(null);
    }



}

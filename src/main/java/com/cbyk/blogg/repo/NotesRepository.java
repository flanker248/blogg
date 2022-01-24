package com.cbyk.blogg.repo;

import com.cbyk.blogg.model.BlogPost;
import com.cbyk.blogg.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotesRepository extends MongoRepository<Note, String> {

    List<Note> findAllByTagsContaining(String title);
    List<Note> findAllByTitleLikeIgnoreCase(String title);

}

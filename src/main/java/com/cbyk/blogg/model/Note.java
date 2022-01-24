package com.cbyk.blogg.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "notes")
public class Note {

    @Id
    public String id;
    public String title;
    public String body;
    public List<String> tags;


    public Note(NoteDTO n){
        this.body=n.body;
        this.title=n.title;
        this.tags=n.tags;
    }

    public Note(){}

    public Note(String n){
        this.title=n;
        this.body=n;
    }

}

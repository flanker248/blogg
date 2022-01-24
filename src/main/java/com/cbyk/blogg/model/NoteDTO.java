package com.cbyk.blogg.model;

import java.util.List;

public class NoteDTO {

    public String id;
    public String title;
    public String body;
    public List<String> tags;

    public NoteDTO(){}

    public NoteDTO(Note n){
        this.body=n.body;
        this.title=n.title;
        this.tags=n.tags;
    }
}
